drop schema if exists bolnica;
create schema bolnica default character set utf8 default collate utf8_unicode_ci;
use bolnica;

/*
  Za mapiranje veza specijalizacije koristeno je Pravilo
  vertikalnog mapitanja (3A) za superklasu ZAPOSLENI
  i potklase DOKTOR i MED_SESTRA_TEHNICAR
*/

-- Mapiranje superklase ZAPOSLENI
create table zaposleni
(
	JMB char(13),
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    Email varchar(40) not null,
    DatumRodjenja date not null,
    Adresa varchar(50) not null,
    DatumZaposlenja date not null,
    Plata decimal(6,2) not null,
    Pol varchar(6) not null,
    Telefon varchar(20) not null,
    primary key(JMB)
);

-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('2002972126116', 'Maja', 'Marić', 'maja.maric@gmail.com', '1972-02-20', 'Ulica 1','2009-10-03', 2300.00,'ženski', '+387 (0) 65 216 320');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('3010981104134', 'Nikola', 'Popović', 'nikola.popovic@gmail.com', '1981-10-30', 'Ulica 33','2011-03-17', 2600.00,'muški', '+387 (0) 66 335 710');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('2907969120462', 'Marko', 'Ilić', 'marko.ilic@gmail.com', '1969-07-29', 'Ulica 17','1995-12-07', 2300.00,'muški', '+387 (0) 65 751 003');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('1912974107101', 'Sara', 'Miljević', 'sara.miljevic@gmail.com', '1974-12-19', 'Ulica 89','2001-07-14', 1500.00,'ženski', '+387 (0) 65 120 963');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('3003989103106', 'Dejan', 'Rakić', 'dejan.rakic@gmail.com', '1989-03-30', 'Ulica 11','2016-01-05', 2300.00,'muškii', '+387 (0) 66 917 144');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('1610963129179', 'Ana', 'Filipović', 'ana.filipovic@gmail.com', '1963-10-16', 'Ulica 91','1992-09-30', 1400.00,'ženski', '+387 (0) 66 324 878');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('2109982101124', 'Dragan', 'Desnica', 'dragan.desnica@gmail.com', '1982-09-21', 'Ulica 45','2008-11-27', 2600.00,'muški', '+387 (0) 66 169 375');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('1711977100179', 'Saša', 'Lekić', 'sasa.lekic@gmail.com', '1977-11-17', 'Ulica 60','2016-06-13', 2700.00,'muški', '+387 (0) 65 515 016');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('1408987118138', 'Helena', 'Petrović', 'helena.petrovic@gmail.com', '1987-08-14', 'Ulica 14','2019-12-26', 1700.00,'ženski', '+387 (0) 65 498 642');
-- insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, DatumZaposlenja, Plata, Pol, Telefon) values ('2206976113194', 'Miroslav', 'Danilović', 'miroslav.danilovic@gmail.com', '1976-06-22', 'Ulica 22','1999-05-15', 2300.00,'muški', '+387 (0) 66 731 238');
--  select * from zaposleni;
 
-- Mapiranje potklase DOKTOR
create table doktor
(
	JMB char(13),
    Specijalizacija varchar(20) not null,
	primary key (JMB),
    constraint FK_doktor_zaposleni
    foreign key (JMB)
    references zaposleni (JMB)
);

-- insert into doktor (JMB, Specijalizacija) values ('2002972126116', 'Psihijatar');
-- insert into doktor (JMB, Specijalizacija) values ('3010981104134', 'Kardiolog');
-- insert into doktor (JMB, Specijalizacija) values ('2907969120462', 'Anesteziolog');
-- insert into doktor (JMB, Specijalizacija) values ('3003989103106', 'Pedijatar');
-- insert into doktor (JMB, Specijalizacija) values ('2109982101124', 'Urolog');
-- insert into doktor (JMB, Specijalizacija) values ('1711977100179', 'Onkolog');
-- insert into doktor (JMB, Specijalizacija) values ('2206976113194', 'Ortoped');
-- select * from doktor;

-- Mapiranje potklase MED_SESTRA_TEHNICAR
create table med_sestra_tehnicar
(
	JMB char(13),
    StrucnaSprema varchar(50) not null,
	primary key (JMB),
    constraint FK_med_sestra_tehnicar_zaposleni
    foreign key (JMB)
    references zaposleni (JMB)
);

-- insert into medicinska_sestra (JMB, BrojPacijenata) values ('1912974107101', 'VŠS- viša medicinska sestra/tehničar');
-- insert into medicinska_sestra (JMB, BrojPacijenata) values ('1610963129179', 'SSS - medicinska sestra/tehničar');
-- insert into medicinska_sestra (JMB, BrojPacijenata) values ('1408987118138', 'VSS - diplomirana medicinska sestra/tehničar');
-- select * from med_sestra_tehnicar;

/*
  Mapiranje jakog entitetskog tipa ODJEL prema Pravilu 1
  i veznih tipova IMA_SEFA_ODJELA i IMA_GLAVNU_MED_SESTRU_TEHNICARA
  (1:1 tipovi veze) prema Pravilu 7
*/
create table odjel
(
	IdOdjela int,
    NazivOdjela varchar(20) not null,
    Adresa varchar(50) not null,
    JMBSefaOdjela char(13) not null,
    JMBGlavneMedSestreTehnicara char(13) not null,
    primary key (IdOdjela),
	constraint FK_odjel_doktor
    foreign key (JMBSefaOdjela)
    references doktor (JMB),
	constraint FK_odjel_med_sestra_tehnicar
    foreign key (JMBGlavneMedSestreTehnicara)
    references med_sestra_tehnicar (JMB)
);

-- insert into odjel (IdOdjela, NazivOdjela, JMBSefaOdjela ) values (1, 'Kardiologija', '3010981104134');
-- insert into odjel (IdOdjela, NazivOdjela, JMBSefaOdjela ) values (2, 'Hirurgija', '2109982101124');
-- insert into odjel (IdOdjela, NazivOdjela, JMBSefaOdjela ) values (3, 'Onkologija', '1711977100179');
-- select * from odjel;

/*
  Mapiranje viseznacnog atributa Telefon entitetskog tipa ODJEL
  (TELEFON_ODJELA) prema Pravilu 10
*/
create table telefon_odjela
(
  Telefon varchar(20),
  IdOdjela int,
  primary key (Telefon, IdOdjela),
  constraint FK_telefon_odjela_odjel
  foreign key (IdOdjela)
  references odjel (IdOdjela)
);

/*
  Dodavanje stranog kljuca IdOdjela u tabelu zaposleni
  za mapiranje veznog tipa RADI_NA (1:M tip veze) prema Pravilu 6A
*/
alter table zaposleni add column IdOdjela int;
alter table zaposleni add constraint FK_zaposleni_odjel
foreign key (IdOdjela)
references odjel (IdOdjela);

-- update zaposleni set IdOdjela = 2 where JMB = '2109982101124';
-- select * from zaposleni;

-- Mapiranje jakog entitetskog tipa ZDRAVSTVENO_OSIGURANJE prema Pravilu 1
create table zdravstveno_osiguranje
(
	IdOsiguranja int,
    DavalacOsiguranja varchar(50) not null,
    Adresa varchar(50) not null,
    Telefon varchar(20) not null,
    primary key (IdOsiguranja)
);

/*
  Mapiranje superklase PACIJENT prema Pravilu 3A i mapiranje
  veznog tipa IMA (ZDRAVSTVENO_OSIGURANJE) (1:M tip veze) prema Pravilu 6A
*/
create table pacijent
(
	JMBPacijenta char(13),
    ImaOsiguranje varchar(4),
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    DatumRodjenja date not null,
	Adresa varchar(50) not null,
    Telefon varchar(20) not null,
	Pol varchar(6) not null,
    KrvnaGrupa varchar(3) not null,
	IdOsiguranja int,
    primary key(JMBPacijenta, ImaOsiguranje),
	constraint FK_pacijent_zdravstveno_osiguranje
    foreign key (IdOsiguranja)
    references zdravstveno_osiguranje (IdOsiguranja)
);

/* Mapiranje jakog entitetskog tipa SOBA prema Pravilu 1
   i veznog tipa SMJESTENA_NA (1:M tip veze)
*/
create table soba
(
	IdSobe int,
    CijenaSobe decimal(6,2) not null,
    IdOdjela int not null,
    primary key (IdSobe),
	constraint FK_soba_odjel
    foreign key (IdOdjela)
    references odjel (IdOdjela)
);

-- Mapiranje veznog tipa ZADUZEN_ZA (M:M tip veze) prema Pravilu 5
create table zaduzen_za
(
	JMBMedSestreTehnicara char(13),
    IdSobe int,
    primary key (JMBMedSestreTehnicara, IdSobe),
	constraint FK_zaduzen_za_med_sestra_tehnicar
    foreign key (JMBMedSestreTehnicara)
    references med_sestra_tehnicar (JMB),
    constraint FK_zaduzen_za_soba
    foreign key (IdSobe)
    references soba (IdSobe)
);

-- Mapiranje jakog entitetskog tipa KONTAKT_OSOBA prema Pravilu 1
create table kontakt_osoba
(
	JMBKontaktOsobe char(13),
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    Telefon varchar(20) not null,
    primary key (JMBKontaktOsobe)
);
/* 
  Mapiranje potklase PACIJENT_ZADRZAN (cija je superklasa PACIJENT)
  prema Pravilu 3A i mapiranje veza SMJESTEN_U (veze tipa 1:M) 
  prema Pravilu 6A i IMA (KONTAKT_OSOBA) (veze tipa 1:1) prema Pravilu 7
 */
create table pacijent_zadrzan
(
	JMBPacijenta char(13),
    ImaOsiguranje varchar(4),
    DatumPrijema date not null,
    DatumOtpustanja date,
    IdSobe int not null,
    JMBKontaktOsobe char(13) not null,
    primary key (JMBPacijenta, ImaOsiguranje),
	constraint FK_pacijent_zadrzan_pacijent
    foreign key (JMBPacijenta, ImaOsiguranje)
    references pacijent (JMBPacijenta, ImaOsiguranje),
	constraint FK_pacijent_zadrzan_soba
    foreign key (IdSobe)
    references soba (IdSobe),
	constraint FK_pacijent_zadrzan_kontakt_osoba
    foreign key (JMBKontaktOsobe)
    references kontakt_osoba (JMBKontaktOsobe)
);

/* Mapiranje potklase PACIJENT_NIJE_ZADRZAN (cija je superklasa PACIJENT)
   prema Pravilu 3A
*/
create table pacijent_nije_zadrzan
(
	JMBPacijenta char(13),
    ImaOsiguranje varchar(4),
    DatumDolaska date not null,
    primary key (JMBPacijenta, ImaOsiguranje),
	constraint FK_pacijent_nije_zadrzan_pacijent
    foreign key (JMBPacijenta, ImaOsiguranje)
    references pacijent (JMBPacijenta, ImaOsiguranje)
);

/* Mapiranje slabog entitetskog tipa RACUN prema Pravilu 2,
   a automatski i identifikujuceg veznog tipa PLACA
*/
create table racun
(
	DatumRacuna date,
    JMBPacijenta char(13),
    ImaOsiguranje varchar(4),
    CijenaPregleda decimal(6,2) not null,
    CijenaLijekova decimal(6,2) not null,
    CijenaSobe decimal(6,2) not null,
    RacunPlaca varchar(10) not null,
    primary key (DatumRacuna, JMBPacijenta, ImaOsiguranje),
	constraint FK_racun_pacijent
    foreign key (JMBPacijenta, ImaOsiguranje)
    references pacijent (JMBPacijenta, ImaOsiguranje)
);

-- Mapiranje jakog entitetskog tipa LIJEK prema Pravilu 1
create table lijek
(
	IdLijeka int,
    NazivLijeka varchar(30) not null,
    TipLijeka varchar(20) not null,
    CijenaLijeka decimal(6,2) not null,
    primary key (IdLijeka)
);

-- Mapiranje n-arnog veznog tipa PREPISIVANJE prema Pravilu 8
create table prepisivanje
(
	DatumPrepisivanja date,
    JMBDoktora char(13),
    JMBPacijenta char(13),
    IdLijeka int,
    KolicinaLijeka int not null,
    primary key (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka),
	constraint FK_prepisivanje_doktor
    foreign key (JMBDoktora)
    references doktor (JMB),
	constraint FK_prepisivanje_pacijent
    foreign key (JMBPacijenta)
    references pacijent (JMBPacijenta),
    constraint FK_prepisivanje_lijek
    foreign key (IdLijeka)
    references lijek (IdLijeka)
);

/* Mapiranje jakog entitetskog tipa DIJAGNOSTICKI_PREGLED prema Pravilu 1
   i veznog tipa NUDI (1:M tip veze) prema Pravilu 6A
*/
create table dijagnosticki_pregled
(
	IdPregleda int,
    NazivPregleda varchar(30) not null,
    CijenaPregleda decimal(6,2) not null,
    IdOdjela int not null,
    primary key (IdPregleda),
	constraint FK_dijagnosticki_pregled_odjel
    foreign key (IdOdjela)
    references odjel (IdOdjela)
);

-- Mapiranje n-arnog veznog tipa PREGLEDANJE prema Pravilu 8
create table pregledanje
(
	DatumPregleda date,
    JMBDoktora char(13),
    JMBPacijenta char(13),
    IdPregleda int,
    Dijagnoza varchar(50) not null,
    Misljenje varchar(100) not null,
    primary key (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda),
	constraint FK_pregledanje_doktor
    foreign key (JMBDoktora)
    references doktor (JMB),
	constraint FK_pregledanje_pacijent
    foreign key (JMBPacijenta)
    references pacijent (JMBPacijenta),
    constraint FK_pregledanje_dijagnosticki_pregled
    foreign key (IdPregleda)
    references dijagnosticki_pregled (IdPregleda)
);

