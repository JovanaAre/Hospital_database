drop schema if exists bolnica;
create schema bolnica default character set utf8 default collate utf8_unicode_ci;
use bolnica;

/*
  Za mapiranje veza specijalizacije koristeno je Pravilo
  vertikalnog mapitanja (3A) za superklasu ZAPOSLENI
  i potklase DOKTOR i MEDICINSKA_SESTRA
*/

-- Mapiranje superklase ZAPOSLENI
create table zaposleni
(
	IdZaposlenog int,
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    Email varchar(30) not null,
    DatumRodjenja date not null,
    Adresa varchar(50) not null,
    DatumZaposlenja date not null,
    Plata decimal(6,2) not null,
    Pol varchar(6) not null,
    primary key(IdZaposlenog)
);

-- Mapiranje potklase DOKTOR
create table doktor
(
	IdZaposlenog int,
    DoktorskoZvanje varchar(20) not null,
	primary key (IdZaposlenog),
    constraint FK_doktor_zaposleni
    foreign key (IdZaposlenog)
    references zaposleni (IdZaposlenog)
);

-- Mapiranje potklase MEDICINSKA_SESTRA
create table medicinska_sestra
(
	IdZaposlenog int,
    BrojPacijenata int not null,
	primary key (IdZaposlenog),
    constraint FK_medicinska_sestra_zaposleni
    foreign key (IdZaposlenog)
    references zaposleni (IdZaposlenog)
);

/*
  Mapiranje jakog entitetskog tipa ODJEL prema Pravilu 1
  i veznog tipa IMA_SEFA_ODJELA (1:1 tip veze) prema Pravilu 7
*/
create table odjel
(
	IdOdjela int,
    NazivOdjela varchar(20) not null,
    IdSefaOdjela int not null,
    primary key (IdOdjela),
	constraint FK_odjel_doktor
    foreign key (IdSefaOdjela)
    references doktor (IdZaposlenog)
);

/*
  Dodavanje stranog kljuca IdOdjela u tabelu zaposleni
  za mapiranje veznog tipa RADI_NA (1:M tip veze) prema Pravilu 6A
*/
alter table zaposleni add column IdOdjela int;
alter table zaposleni add constraint FK_zaposleni_odjel
foreign key (IdOdjela)
references odjel (IdOdjela);

/*
  Mapiranje viseznacnog atributa Telefon entitetskog tipa ZAPOSLENI
  (TELEFON_ZAPOSLENOG) prema Pravilu 10
*/
create table telefon_zaposlenog
(
  Telefon varchar(20),
  IdZaposlenog int,
  primary key (Telefon, IdZaposlenog),
  constraint FK_telefon_zaposlenog_zaposleni
  foreign key (IdZaposlenog)
  references zaposleni (IdZaposlenog)
);

/*
  Mapiranje superklase PACIJENT prema Pravilu 3A i mapiranje
  veznog tipa DODIJELJEN (1:M tip veze) prema Pravilu 6A
*/
create table pacijent
(
	IdPacijenta int,
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    DatumRodjenja date not null,
    Email varchar(30) not null,
	Pol varchar(6) not null,
    Adresa varchar(50) not null,
    KrvnaGrupa varchar(3) not null,
	IdDoktora int,
    primary key(IdPacijenta),
	constraint FK_pacijent_doktor
    foreign key (IdDoktora)
    references doktor (IdZaposlenog)
);

/*
  Mapiranje viseznacnog atributa Telefon entitetskog tipa PACIJENT
  (TELEFON_PACIJENTA) prema Pravilu 10
*/
create table telefon_pacijenta
(
  Telefon varchar(20),
  IdPacijenta int,
  primary key (Telefon, IdPacijenta),
  constraint FK_telefon_pacijenta_pacijent
  foreign key (IdPacijenta)
  references pacijent (IdPacijenta)
);

-- Mapiranje jakog entitetskog tipa SOBA prema Pravilu 1
create table soba
(
	IdSobe int,
    CijenaSobe decimal(6,2) not null,
    TipSobe varchar(20),
    primary key (IdSobe)
);

/* 
  Mapiranje potklase PACIJENT_U_BOLNICI (cija je superklasa PACIJENT)
  prema Pravilu 3A i mapiranje veza SMJESTEN i DODIJELJENA
  (veze tipa 1:M) prema Pravilu 6A
 */
create table pacijent_u_bolnici
(
	IdPacijenta int,
    DatumDolaska date not null,
    Bolest varchar(40) not null,
    DatumOtpustanja date,
    IdSobe int,
    IdMedicinskeSestre int,
    primary key (IdPacijenta),
	constraint FK_pacijent_u_bolnici_pacijent
    foreign key (IdPacijenta)
    references pacijent (IdPacijenta),
	constraint FK_pacijent_u_bolnici_soba
    foreign key (IdSobe)
    references soba (IdSobe),
	constraint FK_pacijent_u_bolnici_medicinska_sestra
    foreign key (IdMedicinskeSestre)
    references medicinska_sestra (IdZaposlenog)
);

-- Mapiranje slabog entitetskog tipa SRODNIK prema Pravilu 2
create table srodnik
(
	ImeSrodnika varchar(30),
    IdPacijenta int,
    BrojTelefona varchar(20) not null,
    VrstaSrodstva varchar(20) not null,
    primary key (ImeSrodnika, IdPacijenta),
	constraint FK_srodnik_pacijent_u_bolnici
    foreign key (IdPacijenta)
    references pacijent_u_bolnici (IdPacijenta)
);

-- Mapiranje potklase PACIJENT_VAN_BOLNICE (cija je superklasa PACIJENT)
create table pacijent_van_bolnice
(
	IdPacijenta int,
    DatumDolaska date not null,
    Bolest varchar(40) not null,
	primary key (IdPacijenta),
	constraint FK_pacijent_van_bolnice_pacijent
    foreign key (IdPacijenta)
    references pacijent (IdPacijenta)
);

-- Mapiranje slabog entitetskog tipa RACUN prema Pravilu 2
create table racun
(
	DatumRacuna date,
    IdPacijenta int,
    CijenaPregleda decimal(6,2) not null,
    CijenaLijekova decimal(6,2),
    CijenaSobe decimal(6,2),
    DrugeNaknade decimal(6,2),
    primary key (DatumRacuna, IdPacijenta),
	constraint FK_racun_pacijent
    foreign key (IdPacijenta)
    references pacijent (IdPacijenta)
);

-- Mapiranje jakog entitetskog tipa LIJEK prema Pravilu 1
create table lijek
(
	IdLijeka int,
    NazivLijeka varchar(30) not null,
    CijenaLijeka decimal(6,2) not null,
    primary key (IdLijeka)
);

-- Mapiranje veznog tipa UZIMA (M:M tip veze) prema Pravilu 5
create table uzima
(
	IdPacijenta int,
    IdLijeka int,
    KolicinaLijeka int not null,
    DatumPrepisivanja date not null,
    primary key (IdPacijenta, IdLijeka),
	constraint FK_uzima_pacijent
    foreign key (IdPacijenta)
    references pacijent (IdPacijenta),
    constraint FK_uzima_lijek
    foreign key (IdLijeka)
    references lijek (IdLijeka)
);

-- Mapiranje jakog entitetskog tipa PREGLED prema Pravilu 1
create table pregled
(
	IdPregleda int,
    NazivPregleda varchar(30) not null,
    CijenaPregleda decimal(6,2) not null,
    primary key (IdPregleda)
);

-- Mapiranje veznog tipa RADIO (M:M tip veze) prema Pravilu 5
create table radio
(
	IdPacijenta int,
    IdPregleda int,
    DatumPregleda date not null,
    primary key (IdPacijenta, IdPregleda),
	constraint FK_radio_pacijent
    foreign key (IdPacijenta)
    references pacijent (IdPacijenta),
    constraint FK_radio_pregled
    foreign key (IdPregleda)
    references pregled (IdPregleda)
);

