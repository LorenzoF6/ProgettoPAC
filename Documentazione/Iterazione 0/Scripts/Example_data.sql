INSERT INTO aat (id_aat,nome_aat, latitudine_aat, longitudine_aat) VALUES
(1,'BERGAMO',45.68575,9.63548),
(2,'BRESCIA',45.55725,10.23114),
(3,'SONDRIO',46.17100,9.88000),
(4,'CREMONA',45.12967,10.05240),
(5,'MONZA BRIANZA',45.60075,9.26030);

INSERT INTO operatore118 (user_operatore118, pwd_operatore118, nome_operatore118, cognome_operatore118) VALUES
(11801,'potHos','MARCO','ESPOSITO'),
(11802,'albuca','LUCA','LEONE'),
(11803,'macodes','ANNA','MONTI'),
(11804,'orchidea','LAURA','POLI'),
(11805,'acero','AURORA','TORRE'),
(11806,'bacillo','VALENTINA','FUMAGALLI');

INSERT INTO ospedale (id_ospedale, nome_ospedale, latitudine_ospedale, longitudine_ospedale, disponibilita_ospedale) VALUES
(1,'ROMANO DI LOMBARDIA',45.51936,9.75138,0),
(2,'BERGAMO OVEST TREVIGLIO',45.50981,9.61150,1),
(3,'SPEDALI CIVILI BRESCIA',45.55725,10.23114,1),
(4,'CREMA',45.35430,9.68065,0),
(5,'S. GIOVANNI BIANCO',45.87190,9.65426,1),
(6,'SONDRIO',46.17109,9.88030,1),
(7,'VIMERCATE',45.60748,9.35623,1),
(8,'FRANCIACORTA',45.54241,9.95455,0),
(9,'MANERBIO',45.35669,10.14586,1),
(10,'BERGAMO PAPA GIOVANNI',45.68575,9.63548,1);


INSERT INTO mezzo (id_mezzo, targa_mezzo, tipo_mezzo, stato_mezzo, id_aatm) VALUES
(1,'CRI910AF','AMBULANZA','L',1),
(2,'DY843XR','AMBULANZA','L',1),
(3,'CRI12202','AMBULANZA','O',2),
(4,'BR749HE','AUTO','L',1),
(5,'FD456KK','AUTO','M',2),
(6,'CRI923TD','AMBULANZA','L',2),
(7,'CRI56789','AMBULANZA','L',1),
(8,'CD104EF','AMBULANZA','M',2),
(9,'ED453LL','AMBULANZA','O',3),
(10,'CRI12222','AMBULANZA','O',4),
(11,'GA654JS','AMBULANZA','O',4),
(12,'BR234TF','AMBULANZA','L',5);


INSERT INTO squadra (id_squadra, stato_squadra, disponibilita_squadra, id_mezzos, id_aats) VALUES
(1,'IN SEDE',0,1,1),
(2,'IN SEDE',0,2,1),
(3,'SUL POSTO',1,3,2),
(4,'LIBERO OSPEDALE',1,6,2),
(5,'LIBERO OSPEDALE',0,7,1),
(6,'NON DISPONIBILE',1,8,2),
(7,'CONFERMA',0,9,3),
(8,'ARRIVO',1,10,4),
(9,'PARTITO',1,11,4),
(10,'RIENTRO IN SEDE',0,12,5);


INSERT INTO volontario (id_volontario, nome_volontario, cognome_volontario, user_volontario, pwd_volontario, id_squadrav) VALUES
(1,'MARIO','RUSSO','V001','gelso',7),
(2,'ROBERTO','BIANCHI','V002','prezzemolo',1),
(3,'MASSIMO','RICCI','V003','vischio',6),
(4,'ERICA','COLOMBO','V004','lavanda',8),
(5,'GIULIA','GRECO','V005','pero',4),
(6,'VALERIO','RIZZO','V006','papavero',9),
(7,'EGIDIO','MANCINI','V007','tarassaco',5),
(8,'ELISA','FERRARA','V008','margherita',9),
(9,'AGOSTINO','LOMBARDI','V009','stellaalpina',8),
(10,'MARCO','MORETTI','V010','narciso',2),
(11,'GIULIA','CONTE','V011','begonia',6),
(12,'MARCO','GALLI','V012','felce',9),
(13,'ISABELLA','PELLEGRINI','V013','olea',10),
(14,'ANTONIO','BIANCHI','V014','incenso',2),
(15,'MICHELE','RIVA','V015','girasole',3),
(16,'NICOLA','LONGO','V016','rododendro',1),
(17,'ANTONELLA','COSTA','V017','peonia',4),
(18,'RINO','MANZONI','V018','rosa',7),
(19,'CATERINA','ROMANO','V019','petunia',2),
(20,'GRETA','ROTA','V020','giglio',8),
(21,'GREGORIO','PIACENTINI','V021','ciclamino',5),
(22,'RICCARDO','GUSMINI','V022','gazania',3),
(23,'SARA','FASCINA','V023','gerbera',10),
(24,'MATTEO','VILLA','V024','dalia',3),
(25,'GABRIELE','DE SANTIS','V025','calla',5);

INSERT INTO emergenza (id_emergenza, latitudine_emergenza, longitudine_emergenza, data_emergenza, orario_emergenza, motivo_emergenza, codGravita_emergenza, id_operatore118e, id_ospedalee, id_squadrae) VALUES
(1,45.54720,9.64294,'2023-12-16','23:35','INCIDENTE STRADALE','G',11803,2,5),
(2,45.41631,9.73958,'2023-11-29','10:50','DOMESTICO','V',11805,4,8);


INSERT INTO paziente (id_paziente,nome_paziente,cognome_paziente,eta_paziente,sesso_paziente,cosciente_paziente,respira_paziente,patologie_paziente,id_emergenzap) VALUES
(1,'GIANCARLO','FAVRET',26,'M',1,1,'NO',1),
(2,'GIACOMO','BONIZZI',46,'M',0,1,'NO',1),
(3,'ELVIRA','GATTI',76,'F',1,1,'DIABETE',2);
