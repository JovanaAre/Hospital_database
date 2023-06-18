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
    Plata decimal(6,2) not null,
    Pol varchar(6) not null,
    Telefon varchar(20) not null,
    primary key(JMB)
);

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

/*
  Mapiranje veznog tipa RADI_NA (M:M tip veze)
  prema Pravilu 5
*/

create table radi_na
(
	DatumZaposlenja date not null,
    JMB char(13),
    IdOdjela int,
    primary key (DatumZaposlenja, JMB, IdOdjela),
	constraint FK_radi_na_zaposleni
    foreign key (JMB)
    references zaposleni (JMB),
	constraint FK_radi_na_odjel
    foreign key (IdOdjela)
    references odjel (IdOdjela)
);

/*
  Mapiranje viseznacnog atributa Telefon entitetskog tipa ODJEL
  (TELEFON_ODJELA) prema Pravilu 10
*/
create table telefon_odjela
(
  IdOdjela int,
  Telefon varchar(20),
  primary key (IdOdjela, Telefon),
  constraint FK_telefon_odjela_odjel
  foreign key (IdOdjela)
  references odjel (IdOdjela)
);

-- Mapiranje jakog entitetskog tipa ZDRAVSTVENO_OSIGURANJE prema Pravilu 1
create table zdravstveno_osiguranje
(
    DavalacOsiguranja varchar(50),
    Adresa varchar(50) not null,
    Telefon varchar(20) not null,
    primary key (DavalacOsiguranja)
);

/*
  Mapiranje superklase PACIJENT prema Pravilu 3A i mapiranje
  veznog tipa IMA (ZDRAVSTVENO_OSIGURANJE) (1:M tip veze) prema Pravilu 6A
*/
create table pacijent
(
	JMBPacijenta char(13),
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    DatumRodjenja date not null,
	Adresa varchar(50) not null,
    Telefon varchar(20),
	Pol varchar(6) not null,
    KrvnaGrupa varchar(3) not null,
	DavalacOsiguranja varchar(50) not null,
    primary key(JMBPacijenta),
	constraint FK_pacijent_zdravstveno_osiguranje
    foreign key (DavalacOsiguranja)
    references zdravstveno_osiguranje (DavalacOsiguranja)
);

-- Mapiranje jakog entitetskog tipa SOBA prema Pravilu 1

create table soba
(
	BrojSobe int,
    CijenaSobe decimal(6,2) not null,
    BrojKreveta int not null,
    primary key (BrojSobe)
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
    DatumPrijema date not null,
    DatumOtpustanja date,
    BrojSobe int not null,
    JMBKontaktOsobe char(13) not null,
    primary key (JMBPacijenta, DatumPrijema),
	constraint FK_pacijent_zadrzan_pacijent
    foreign key (JMBPacijenta)
    references pacijent (JMBPacijenta),
	constraint FK_pacijent_zadrzan_soba
    foreign key (BrojSobe)
    references soba (BrojSobe),
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
    DatumDolaska date,
    primary key (JMBPacijenta, DatumDolaska),
	constraint FK_pacijent_nije_zadrzan_pacijent
    foreign key (JMBPacijenta)
    references pacijent (JMBPacijenta)
);

/* Mapiranje slabog entitetskog tipa RACUN prema Pravilu 2
   (automatski i identifikujuceg veznog tipa PLACA)
*/
create table racun
(
	DatumRacuna date,
    JMBPacijenta char(13),
    CijenaPregleda decimal(6,2) default 0 not null,
    CijenaLijekova decimal(6,2) default 0 not null,
    CijenaSobe decimal(6,2) default 0 not null,
    primary key (DatumRacuna, JMBPacijenta),
	constraint FK_racun_pacijent
    foreign key (JMBPacijenta)
    references pacijent (JMBPacijenta)
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

-- Mapiranje jakog entitetskog tipa DIJAGNOSTICKI_PREGLED prema Pravilu 1

create table dijagnosticki_pregled
(
	IdPregleda int,
    NazivPregleda varchar(30) not null,
    CijenaPregleda decimal(6,2) not null,
    primary key (IdPregleda)
);

-- Mapiranje n-arnog veznog tipa PREGLEDANJE prema Pravilu 8
create table pregledanje
(
	DatumPregleda date,
    JMBDoktora char(13),
    JMBPacijenta char(13),
    IdPregleda int,
    Dijagnoza varchar(50) not null,
    Misljenje varchar(100),
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
