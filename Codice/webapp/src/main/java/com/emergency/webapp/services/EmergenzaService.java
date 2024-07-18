package com.emergency.webapp.services;

import com.emergency.webapp.dtos.*;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Emergenza;
import com.emergency.webapp.repositories.EmergenzaRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergenzaService {

    @Autowired
    private EmergenzaRepository emergenzaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmergenzaDTO getEmergenzaById(Integer id) {

        Emergenza emergenza = emergenzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emergenza not found"));

        return modelMapper.map(emergenza, EmergenzaDTO.class);
    }

    public List<EmergenzaDTO> getAllEmergenze() {

        List<Emergenza> emergenze = emergenzaRepository.findAll();

        return emergenze.stream()
                .map(emergenza -> modelMapper.map(emergenza, EmergenzaDTO.class))
                .collect(Collectors.toList());
    }

    public EmergenzaDTO createEmergenza(EmergenzaDTO emergenzaDTO) {

        Emergenza emergenza = modelMapper.map(emergenzaDTO, Emergenza.class);

        emergenzaRepository.save(emergenza);

        return modelMapper.map(emergenza, EmergenzaDTO.class);
    }

    public EmergenzaDTO updateEmergenza(Integer id, EmergenzaDTO emergenzaDTO) {

        Emergenza existingEmergenza = emergenzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emergenza not found"));
        modelMapper.map(emergenzaDTO, existingEmergenza);

        Emergenza updatedEmergenza = emergenzaRepository.save(existingEmergenza);

        return modelMapper.map(updatedEmergenza, EmergenzaDTO.class);
    }

    public byte[] generatePdfReport(int id) {
        EmergenzaDTO emergenzaDTO = getEmergenzaById(id);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // Titolo del documento
            Paragraph title = new Paragraph("Report Dettagliato dell'Emergenza", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Aggiungi sezioni al documento
            addSection(document, "Dettagli Emergenza", sectionFont, normalFont, new String[][] {
                    {"ID", String.valueOf(emergenzaDTO.getId())},
                    {"Latitudine", String.valueOf(emergenzaDTO.getLatitudineEmergenza())},
                    {"Longitudine", String.valueOf(emergenzaDTO.getLongitudineEmergenza())},
                    {"Data", String.valueOf(emergenzaDTO.getDataEmergenza())},
                    {"Orario", String.valueOf(emergenzaDTO.getOrarioEmergenza())},
                    {"Motivo", emergenzaDTO.getMotivoEmergenza()},
                    {"Codice Gravità", String.valueOf(emergenzaDTO.getCodgravitaEmergenza())}
            });

            addSection(document, "Ospedale", sectionFont, normalFont, new String[][] {
                    {"Nome", emergenzaDTO.getOspedale().getNomeOspedale()}
            });

            addSection(document, "Operatore", sectionFont, normalFont, new String[][] {
                    {"Nome", emergenzaDTO.getOperatore118().getNomeOperatore118()},
                    {"Cognome", emergenzaDTO.getOperatore118().getCognomeOperatore118()}
            });

            // Aggiungi sezione volontari
            document.add(new Paragraph("Volontari", sectionFont));
            PdfPTable table = new PdfPTable(2); // 2 colonne per nome e cognome
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell cell1 = new PdfPCell(new Phrase("Nome", normalFont));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            PdfPCell cell2 = new PdfPCell(new Phrase("Cognome", normalFont));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);

            for (VolontarioDTO volontario : emergenzaDTO.getSquadra().getVolontarios()) {
                table.addCell(new PdfPCell(new Phrase(volontario.getNomeVolontario(), normalFont)));
                table.addCell(new PdfPCell(new Phrase(volontario.getCognomeVolontario(), normalFont)));
            }

            document.add(table);

            document.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }

        return baos.toByteArray();
    }

    private void addSection(Document document, String sectionTitle, Font sectionFont, Font normalFont, String[][] content) throws DocumentException {
        Paragraph sectionTitleParagraph = new Paragraph(sectionTitle, sectionFont);
        sectionTitleParagraph.setSpacingBefore(15);
        sectionTitleParagraph.setSpacingAfter(10);
        document.add(sectionTitleParagraph);

        for (String[] entry : content) {
            Paragraph paragraph = new Paragraph(entry[0] + ": " + entry[1], normalFont);
            paragraph.setSpacingAfter(5);
            document.add(paragraph);
        }
    }

    public List<EmergenzaDTO> getEmergenzeDisponibili(VolontarioDTO volontario) {
        List<Emergenza> emergenze = emergenzaRepository.findAll();
        return emergenze.stream()
                .filter(e -> e.getSquadra() == null &&
                        volontario.getSquadra().getDisponibilitaSquadra() == 1 &&
                        calculateDistance(
                                e.getLatitudineEmergenza(), e.getLongitudineEmergenza(),
                                volontario.getSquadra().getAat().getLatitudineAat(), volontario.getSquadra().getAat().getLongitudineAat()
                        ) < 40) // Squadra non assegnata, volontario (e quindi squadra) disponibile, e distanza inferiore a 35
                .sorted(Comparator.comparingDouble(e -> calculateDistance(
                        e.getLatitudineEmergenza(), e.getLongitudineEmergenza(),
                        volontario.getSquadra().getAat().getLatitudineAat(), volontario.getSquadra().getAat().getLongitudineAat()
                ))) // Ordina per distanza
                .map(e -> modelMapper.map(e, EmergenzaDTO.class))
                .collect(Collectors.toList()); // Colleziona le emergenze disponibili in una lista
    }

    // Metodo per calcolare la distanza utilizzando la formula di Haversine
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Raggio della terra in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public void setSquadraEmergenza(EmergenzaDTO emergenza, SquadraDTO squadra) {
        emergenza.setSquadra(squadra);
    }
}

