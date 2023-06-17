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

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2002972126116', 'Maja', 'Marić', 'maja.maric@gmail.com', '1972-02-20', 'Ulica 1', 2300.00, 'ženski', '+387 (0) 65 216 320'); -- endokrinologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('3010981104134', 'Nikola', 'Popović', 'nikola.popovic@gmail.com', '1981-10-30', 'Ulica 33', 2600.00, 'muški', '+387 (0) 66 335 710');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2907969120462', 'Marko', 'Ilić', 'marko.ilic@gmail.com', '1969-07-29', 'Ulica 17', 1400.00, 'muški', '+387 (0) 65 751 003');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1912974107101', 'Sara', 'Miljević', 'sara.miljevic@gmail.com', '1974-12-19', 'Ulica 89', 1700.00, 'ženski', '+387 (0) 65 120 963');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('3003989103106', 'Dejan', 'Rakić', 'dejan.rakic@gmail.com', '1989-03-30', 'Ulica 11', 2300.00, 'muški', '+387 (0) 66 917 144'); -- kardiologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1610963129179', 'Ana', 'Filipović', 'ana.filipovic@gmail.com', '1963-10-16', 'Ulica 91', 2600.00, 'ženski', '+387 (0) 66 324 878');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2109982101124', 'Dragan', 'Desnica', 'dragan.desnica@gmail.com', '1982-09-21', 'Ulica 45', 1500.00,'muški', '+387 (0) 66 169 375'); -- viša
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1711977100179', 'Saša', 'Lekić', 'sasa.lekic@gmail.com', '1977-11-17', 'Ulica 60', 1700.00, 'muški', '+387 (0) 65 515 016');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1408987118138', 'Helena', 'Petrović', 'helena.petrovic@gmail.com', '1987-08-14', 'Ulica 14', 2300.00, 'ženski', '+387 (0) 65 498 642'); -- urologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2206976113194', 'Miroslav', 'Danilović', 'miroslav.danilovic@gmail.com', '1976-06-22', 'Ulica 158', 2700.00, 'muški', '+387 (0) 66 731 238'); -- GOTOVO
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2103972126116', 'Sofija', 'Gajić', 'sofija.gajic@gmail.com', '1972-03-21', 'Ulica 214', 1400.00, 'ženski', '+387 (0) 65 612 023');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1811981104134', 'Siniša', 'Popović', 'sinisa.popovic@gmail.com', '1981-11-18', 'Ulica 52', 1700.00, 'muški', '+387 (0) 66 533 017'); 

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1404969129462', 'Dajana', 'Manojlović', 'dajana.manojlovic@gmail.com', '1969-04-14', 'Ulica 101', 2400.00, 'ženski', '+387 (0) 65 157 300'); -- stomatologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0506974107101', 'Ilijana', 'Kostić', 'ilijana.kostic@gmail.com', '1974-06-05', 'Ulica 99', 2700.00, 'ženski', '+387 (0) 65 012 369');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0207989106106', 'Sandra', 'Dević', 'sandra.devic@gmail.com', '1989-07-02', 'Ulica 98', 1300.00, 'ženski', '+387 (0) 66 719 441');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('3110963127179', 'Amarela', 'Stanić', 'amarela.stanic@gmail.com', '1963-10-31', 'Ulica 81', 1700.00, 'ženski', '+387 (0) 66 423 871');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2609982101124', 'Damir', 'Kosić', 'damir.kosic@gmail.com', '1982-09-26', 'Ulica 65', 2400.00, 'muški', '+387 (0) 66 961 573'); --  psihijatrija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0608977108179', 'Lazarela', 'Lakić', 'lazarela.lakic@gmail.com', '1977-08-06', 'Ulica 70', 2700.00, 'ženski', '+387 (0) 65 519 610');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2905987118138', 'Mira', 'Ožegović', 'mira.ozegovic@gmail.com', '1987-05-29', 'Ulica 34', 1500.00, 'ženski', '+387 (0) 65 894 246'); -- viša
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0801976113194', 'Luka', 'Radić', 'luka.radic@gmail.com', '1976-01-08', 'Ulica 82', 1700.00, 'muški', '+387 (0) 66 137 832');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2004972124116', 'Jovan', 'Kostadinović', 'jovan.kostadinovic@gmail.com', '1972-04-20', 'Ulica 10', 2300.00, 'muški', '+387 (0) 65 126 203'); -- ortopedija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('3112981104134', 'Andrej', 'Perić', 'andrej.peric@gmail.com', '1981-12-31', 'Ulica 35', 2600.00, 'muški', '+387 (0) 66 353 701');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2608969120462', 'Matej', 'Ilić', 'matej.ilic@gmail.com', '1969-08-26', 'Ulica 97', 1400.00, 'muški', '+387 (0) 65 715 030');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0304974107101', 'Marija', 'Stanojević', 'marija.stanojevic@gmail.com', '1974-04-03', 'Ulica 69', 1700.00,'ženski', '+387 (0) 65 102 639');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0803989103106', 'Nemanja', 'Galić', 'nemanja.galic@gmail.com', '1989-03-08', 'Ulica 111', 2200.00, 'muški', '+387 (0) 66 210 414'); -- djecije bolesti
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1911963129179', 'Nevena', 'Rudić', 'nevena.rudic@gmail.com', '1963-11-19', 'Ulica 51', 2500.00, 'ženski', '+387 (0) 66 785 348');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1409982101124', 'Sven', 'Misimović', 'sven.misimovic@gmail.com', '1982-09-14', 'Ulica 44', 1300.00, 'muški', '+387 (0) 66 759 157');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2512977100179', 'Dalibor', 'Došen', 'dalibor.dosen@gmail.com', '1977-12-25', 'Ulica 66', 1600.00, 'muški', '+387 (0) 65 296 359 ');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1508987112138', 'Rade', 'Sladojević', 'rade.sladojevic@gmail.com', '1987-08-15', 'Ulica 114', 2400.00, 'muški', '+387 (0) 65 418 883'); -- onkologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2806976118194', 'Gorica', 'Đurić', 'gorica.djuric@gmail.com', '1976-06-28', 'Ulica 77', 2600.00, 'ženski', '+387 (0) 66 225 781');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2007972124116', 'Zoran', 'Marić', 'zoran.maric@gmail.com', '1972-07-20', 'Ulica 88', 1400.00, 'muški', '+387 (0) 65 065 660');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2310981106134', 'Gorana', 'Popović', 'gorana.popovic@gmail.com', '1981-10-23', 'Ulica 59', 1700.00, 'ženski', '+387 (0) 66 280 017');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1312969120462', 'Kosta', 'Šestić', 'kosta.sestic@gmail.com', '1969-12-13', 'Ulica 74', 2300.00, 'muški', '+387 (0) 65 850 123'); -- radiologija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0501974101101', 'Đorđe', 'Samardžić', 'djordje.samardzic@gmail.com', '1974-01-05', 'Ulica 149', 2600.00, 'muški', '+387 (0) 65 456 963');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1603989103106', 'Srđan', 'Ristić', 'srdjan.ristic@gmail.com', '1989-03-16', 'Ulica 201', 1400.00, 'muški', '+387 (0) 66 741 359');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2406963129179', 'Ana', 'Stanić', 'ana.stanic@gmail.com', '1963-06-24', 'Ulica 191', 1700.00, 'ženski', '+387 (0) 66 951 753');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2909982101124', 'Dragan', 'Matić', 'dragan.matic@gmail.com', '1982-09-29', 'Ulica 145', 2300.00, 'muški', '+387 (0) 66 160 428'); -- infektivne bolesti
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1702977107179', 'Dijana', 'Dodik', 'dijana.dodik@gmail.com', '1977-02-17', 'Ulica 160', 2700.00, 'ženski', '+387 (0) 65 943 002');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2101987118138', 'Helena', 'Romić', 'helena.romic@gmail.com', '1987-01-21', 'Ulica 142', 1500.00, 'ženski', '+387 (0) 65 246 135'); -- viša 
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('2410976113194', 'Aleksandar', 'Stojić', 'aleksandar.stojic@gmail.com', '1976-10-24', 'Ulica 222', 1800.00, 'muški', '+387 (0) 66 351 642');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0509982101124', 'Milan', 'Kondić', 'milan.kondic@gmail.com', '1982-09-05', 'Ulica 177', 2200.00, 'muški', '+387 (0) 66 907 300'); -- transfuzija
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1112977106179', 'Marica', 'Bojić', 'marica.bojic@gmail.com', '1977-12-11', 'Ulica 166', 2500.00, 'ženski', '+387 (0) 65 106 912');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('0806987113138', 'Milutin', 'Bosnić', 'milutin.bosnic@gmail.com', '1987-06-08', 'Ulica 155', 1300.00, 'muški', '+387 (0) 65 922 194');
insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon) values ('1704976119194', 'Milica', 'Ilić', 'milica.ilicic@gmail.com', '1976-04-17', 'Ulica 206', 1600.00, 'ženski', '+387 (0) 66 122 923');
select * from zaposleni;
 
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

insert into doktor (JMB, Specijalizacija) values ('2002972126116', 'Endokrinolog');
insert into doktor (JMB, Specijalizacija) values ('3010981104134', 'Endokrinolog');
insert into doktor (JMB, Specijalizacija) values ('3003989103106', 'Kardiolog');
insert into doktor (JMB, Specijalizacija) values ('1610963129179', 'Kardiolog');
insert into doktor (JMB, Specijalizacija) values ('1408987118138', 'Urolog');
insert into doktor (JMB, Specijalizacija) values ('2206976113194', 'Urolog');
insert into doktor (JMB, Specijalizacija) values ('1404969129462', 'Stomatolog');
insert into doktor (JMB, Specijalizacija) values ('0506974107101', 'Stomatolog');
insert into doktor (JMB, Specijalizacija) values ('2609982101124', 'Psihijatar');
insert into doktor (JMB, Specijalizacija) values ('0608977108179', 'Psihijatar');
insert into doktor (JMB, Specijalizacija) values ('2004972124116', 'Ortoped');
insert into doktor (JMB, Specijalizacija) values ('3112981104134', 'Ortoped');
insert into doktor (JMB, Specijalizacija) values ('0803989103106', 'Pedijatar');
insert into doktor (JMB, Specijalizacija) values ('1911963129179', 'Pedijatar');
insert into doktor (JMB, Specijalizacija) values ('1508987112138', 'Onkolog');
insert into doktor (JMB, Specijalizacija) values ('2806976118194', 'Onkolog');
insert into doktor (JMB, Specijalizacija) values ('1312969120462', 'Radiolog');
insert into doktor (JMB, Specijalizacija) values ('0501974101101', 'Radiolog');
insert into doktor (JMB, Specijalizacija) values ('2909982101124', 'Infektolog');
insert into doktor (JMB, Specijalizacija) values ('1702977107179', 'Infektolog');
insert into doktor (JMB, Specijalizacija) values ('0509982101124', 'Transfuziolog');
insert into doktor (JMB, Specijalizacija) values ('1112977106179', 'Transfuziolog');
select * from doktor;

/*
create table zvanje
(
	IdZvanja int auto_increment,
    Naziv varchar(20) not null,
    primary key(IdZvanja)
);
insert into zvanje(Naziv)
select distinct Specijalizacija
from doktor;
select * from zvanje;

alter table doktor add column IdZvanja int;
alter table doktor add constraint FK_doktor_zvanje
foreign key (IdZvanja)
references zvanje (IdZvanja);

SET SQL_SAFE_UPDATES = 0;
update doktor set
IdZvanja=(select IdZvanja from zvanje 
where zvanje.naziv=doktor.Specijalizacija);

alter table doktor drop column Specijalizacija;

select * from zvanje;
select * from doktor;
SET SQL_SAFE_UPDATES = 1;
*/
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

insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2907969120462', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1912974107101', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2109982101124', 'viša medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1711977100179', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2103972126116', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1811981104134', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('0207989106106', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('3110963127179', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2905987118138', 'viša medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('0801976113194', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2608969120462', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('0304974107101', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1409982101124', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2512977100179', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2007972124116', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2310981106134', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1603989103106', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2406963129179', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2101987118138', 'viša medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('2410976113194', 'diplomirana medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('0806987113138', 'medicinska sestra/tehničar');
insert into med_sestra_tehnicar (JMB, StrucnaSprema) values ('1704976119194', 'diplomirana medicinska sestra/tehničar');
select * from med_sestra_tehnicar;

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

insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (10000, 'Endokrinologija', '12 Beba bb, Banja Luka', '3010981104134', '1912974107101');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (11000, 'Kardiologija', '12 Beba bb, Banja Luka', '1610963129179', '1711977100179');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (12000, 'Urologija', '12 Beba bb, Banja Luka', '2206976113194', '1811981104134');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (13000, 'Stomatologija', 'Zdrave Korde 4, Banja Luka', '0506974107101', '3110963127179');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (14000, 'Psihijatrija', 'Ulica Mačvanska 17, Banja Luka', '0608977108179', '0801976113194');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (15000, 'Ortopedija', '12 Beba bb, Banja Luka', '3112981104134', '0304974107101');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (16000, 'Pedijatrija', '12 Beba bb, Banja Luka', '1911963129179', '2512977100179');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (17000, 'Onkologija', '12 Beba bb, Banja Luka', '2806976118194', '2310981106134');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (18000, 'Radiologija', '12 Beba bb, Banja Luka', '0501974101101', '2406963129179');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (19000, 'Infektologija', '12 Beba bb, Banja Luka', '1702977107179', '2410976113194');
insert into odjel (IdOdjela, NazivOdjela, Adresa, JMBSefaOdjela, JMBGlavneMedSestreTehnicara) values (11100, 'Transfuziologija', 'Zdrave Korde 4, Banja Luka', '1112977106179', '1704976119194');
select * from odjel;

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

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2009-11-04', '2002972126116', 10000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2011-05-16', '3010981104134', 10000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1995-10-08', '2907969120462', 10000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2001-09-24', '1912974107101', 10000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-03-25', '3003989103106', 11000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1992-10-31', '1610963129179', 11000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2008-12-28', '2109982101124', 11000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-09-23', '1711977100179', 11000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2019-02-25', '1408987118138', 12000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1999-04-17', '2206976113194', 12000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2009-01-03', '2103972126116', 12000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2011-10-29', '1811981104134', 12000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1995-09-11', '1404969129462', 13000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2001-10-17', '0506974107101', 13000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-05-07', '0207989106106', 13000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1992-08-31', '3110963127179', 13000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2008-04-12', '2609982101124', 14000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-06-26', '0608977108179', 14000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2019-02-13', '2905987118138', 14000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1999-08-16', '0801976113194', 14000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2009-11-03', '2004972124116', 15000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2011-05-21', '3112981104134', 15000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1995-08-07', '2608969120462', 15000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2001-11-14', '0304974107101', 15000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-08-05', '0803989103106', 16000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1992-12-30', '1911963129179', 16000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2008-10-23', '1409982101124', 16000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-01-13', '2512977100179', 16000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2019-02-02', '1508987112138', 17000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1999-04-23', '2806976118194', 17000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2009-10-03', '2007972124116', 17000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2011-03-17', '2310981106134', 17000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1995-12-07', '1312969120462', 18000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2001-07-14', '0501974101101', 18000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-01-05', '1603989103106', 18000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1992-09-30', '2406963129179', 18000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2008-11-27', '2909982101124', 19000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-06-13', '1702977107179', 19000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2019-12-26', '2101987118138', 19000);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1999-05-15', '2410976113194', 19000);

insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2008-02-27', '0509982101124', 11100);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2016-12-13', '1112977106179', 11100);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('2019-10-26', '0806987113138', 11100);
insert into radi_na (DatumZaposlenja, JMB, IdOdjela) values ('1999-05-29', '1704976119194', 11100);

select * from radi_na;

-- alter table zaposleni add column NazivOdjela varchar(20) not null;
-- update zaposleni
-- join odjel on zaposleni.IdOdjela = odjel.IdOdjela
-- set zaposleni.NazivOdjela = odjel.NazivOdjela;
-- alter table zaposleni drop foreign key FK_zaposleni_odjel;
-- alter table zaposleni drop column IdOdjela;
-- drop table odjel;
 
 

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

insert into telefon_odjela (IdOdjela, Telefon) values (10000, '+387 (0) 51 342 505');
insert into telefon_odjela (IdOdjela, Telefon) values (10000, '+387 (0) 51 342 563');
insert into telefon_odjela (IdOdjela, Telefon) values (11000, '+387 (0) 51 342 517');
insert into telefon_odjela (IdOdjela, Telefon) values (12000, '+387 (0) 51 343 343');
insert into telefon_odjela (IdOdjela, Telefon) values (13000, '+387 (0) 51 232 440');
insert into telefon_odjela (IdOdjela, Telefon) values (13000, '+387 (0) 51 232 442');
insert into telefon_odjela (IdOdjela, Telefon) values (14000, '+387 (0) 51 434 280');
insert into telefon_odjela (IdOdjela, Telefon) values (15000, '+387 (0) 51 343 368');
insert into telefon_odjela (IdOdjela, Telefon) values (16000, '+387 (0) 51 342 405');
insert into telefon_odjela (IdOdjela, Telefon) values (16000, '+387 (0) 51 342 413');
insert into telefon_odjela (IdOdjela, Telefon) values (17000, '+387 (0) 51 342 547');
insert into telefon_odjela (IdOdjela, Telefon) values (18000, '+387 (0) 51 342 249');
insert into telefon_odjela (IdOdjela, Telefon) values (19000, '+387 (0) 51 342 473');
insert into telefon_odjela (IdOdjela, Telefon) values (11100, '+387 (0) 51 233 859');
select * from telefon_odjela;

/*
  Dodavanje stranog kljuca IdOdjela u tabelu zaposleni
  za mapiranje veznog tipa RADI_NA (1:M tip veze) prema Pravilu 6A
*/


-- Mapiranje jakog entitetskog tipa ZDRAVSTVENO_OSIGURANJE prema Pravilu 1
create table zdravstveno_osiguranje
(
    DavalacOsiguranja varchar(50),
    Adresa varchar(50) not null,
    Telefon varchar(20) not null,
    primary key (DavalacOsiguranja)
);

insert into zdravstveno_osiguranje (DavalacOsiguranja, Adresa, Telefon) values ('FZO RS','Zdrave Korde 8, Banja Luka', '+387 (0) 51 249 100');
insert into zdravstveno_osiguranje (DavalacOsiguranja, Adresa, Telefon) values ('Uniqa osiguranje','Jevrejska 99, Banja Luka', '+387 (0) 51 223 770');
insert into zdravstveno_osiguranje (DavalacOsiguranja, Adresa, Telefon) values ('Vienna osiguranje','Pave Radana 12, Banja Luka', '+387 (0) 51 232 360');
insert into zdravstveno_osiguranje (DavalacOsiguranja, Adresa, Telefon) values ('Dunav osiguranje','Veselina Masleše 28, Banja Luka', '+387 (0) 80 050 600');
insert into zdravstveno_osiguranje (DavalacOsiguranja, Adresa, Telefon) values ('Wiener osiguranje','Kninska 1a, Banja Luka', '+387 (0) 51 931 100');
select * from zdravstveno_osiguranje;
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

insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1502973101187', 'Danilo', 'Dragić','1973-02-15', 'Ulica 104','+387 (0) 66 321 147', 'muški', 'AB', 'FZO RS');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1108969107983', 'Milica', 'Momčilović', '1969-08-11', 'Ulica 12','+387 (0) 65 132 450', 'ženski', 'A', 'Wiener osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1907971101492', 'Miroslav', 'Stanojević','1971-07-19', 'Ulica 98','+387 (0) 65 917 052', 'muški', '0', 'Wiener osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('0702998129315', 'Viktorija', 'Radišić', '1998-02-07', 'Ulica 36','+387 (0) 66 215 653', 'ženski', '0','Uniqa osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1211969134934', 'Dražen', 'Ilić', '1969-11-12', 'Ulica 24','+387 (0) 65 718 524', 'muški', 'A', 'FZO RS');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1803016112381', 'Luka', 'Jovanović', '2016-03-18', 'Ulica 147', NULL, 'muški', 'B', 'Vienna osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('2201984108175', 'Ana', 'Živanović', '1984-01-22', 'Ulica 93','+387 (0) 66 630 037', 'ženski', 'AB', 'Dunav osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('2304988101562', 'Marko', 'Polić', '1988-04-23', 'Ulica 27','+387 (0) 66 445 884', 'muški', '0', 'FZO RS');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('2711019139654', 'Anastasija', 'Marinković', '2019-11-27', 'Ulica 38', '+387 (0) 66 520 051', 'ženski', 'AB', 'Uniqa osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1509967103517', 'Nikola', 'Vesić', '1967-09-15', 'Ulica 65','+387 (0) 65 182 326', 'muški', 'B', 'FZO RS');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('2605981101865', 'Momčilo', 'Lekić', '1981-05-26', 'Ulica 41','+387 (0) 65 456 123', 'muški', '0', 'Wiener osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('1402975106903', 'Dejana', 'Marjanović', '1975-02-14', 'Ulica 2','+387 (0) 66 759 486', 'ženski', '0', 'Uniqa osiguranje');
insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja) values ('0111983109487', 'Jovana', 'Rapić', '1983-11-01', 'Ulica 72','+387 (0) 66 153 759', 'ženski', 'AB', 'FZO RS');
select * from pacijent;

/* Mapiranje jakog entitetskog tipa SOBA prema Pravilu 1
   i veznog tipa SMJESTENA_NA (1:M tip veze)
*/
create table soba
(
	BrojSobe int,
    CijenaSobe decimal(6,2) not null,
    BrojKreveta int,
    primary key (BrojSobe)
);

insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (10001, 25.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (10002, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (11001, 30.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (11002, 30.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (11003, 30.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (12001, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (12002, 25.00, 4);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (12003, 25.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (14001, 30.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (14002, 30.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (14003, 30.00, 4);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (14004, 30.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (14005, 30.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (15001, 30.00, 4);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (15002, 30.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (15003, 30.00, 4);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (16001, 25.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (16002, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (16003, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (16004, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (17001, 25.00, 3);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (17002, 25.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (17003, 25.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (19001, 30.00, 2);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (19002, 30.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (19003, 30.00, 1);
insert into soba (BrojSobe, CijenaSobe, BrojKreveta ) values (19004, 30.00, 2);
select * from soba;

-- Mapiranje veznog tipa ZADUZEN_ZA (M:M tip veze) prema Pravilu 5
/* create table zaduzen_za
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
);*/

-- Mapiranje jakog entitetskog tipa KONTAKT_OSOBA prema Pravilu 1
create table kontakt_osoba
(
	JMBKontaktOsobe char(13),
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    Telefon varchar(20) not null,
    primary key (JMBKontaktOsobe)
);

insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('0103972106154', 'Marija', 'Dragić', '+387 (0) 65 482 320'); -- zena
insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('2112989103189', 'Ivan', 'Momčilović', '+387 (0) 66 197 878'); -- sin 
insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('1405996101735', 'Jelena', 'Stanojević', '+387 (0) 65 914 112'); -- kcerka
insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('0406972109892', 'Mirjana', 'Radišić', '+387 (0) 66 789 346'); -- majka
select * from kontakt_osoba;
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('1211969134934', 'Dražen', 'Ilić', '+387 (0) 66 652 998');insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('1803993112381', 'Dragan', 'Jovanović', '+387 (0) 65 364 220');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('2201984108175', 'Ana', 'Živanović', '+387 (0) 65 512 145');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('2304988101562', 'Marko', 'Polić', '+387 (0) 66 704 251');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('2711991139654', 'Danijela', 'Marinković', '+387 (0) 65 445 821');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('1509967103517', 'Nikola', 'Vesić', '+387 (0) 66 339 171');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('2605981101865', 'Momčilo', 'Lekić', '+387 (0) 66 004 617');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('1402975106903', 'Dejana', 'Marjanović', '+387 (0) 66 241 320');
-- insert into kontakt_osoba (JMBKontaktOsobe, Ime, Prezime, Telefon) values ('0111983109487', 'Jovana', 'Rapić', '+387 (0) 65 608 254');

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
drop trigger if exists azuriraj_broj_kreveta;
delimiter $$
create trigger azuriraj_broj_kreveta after insert on pacijent_zadrzan
for each row
begin
    declare vBrojKreveta int;
	select BrojKreveta into vBrojKreveta
    from pacijent_zadrzan pz
    inner join soba s on s.BrojSobe=pz.BrojSobe
    where JMBPacijenta=new.JMBPacijenta
    limit 1;
    
    if vBrojKreveta>=1 then
		set vBrojKreveta = vBrojKreveta-1;
	else
		set vBrojKreveta = 0;
	end if;
	update soba set BrojKreveta = vBrojKreveta where BrojSobe=new.BrojSobe;	
end$$
delimiter ;

insert into pacijent_zadrzan (JMBPacijenta, DatumPrijema, DatumOtpustanja, BrojSobe, JMBKontaktOsobe) values ('1502973101187', '2022-10-15', NULL, 17002, '0103972106154'); -- infekcija pluca ++ 
insert into pacijent_zadrzan (JMBPacijenta, DatumPrijema, DatumOtpustanja, BrojSobe, JMBKontaktOsobe) values ('1108969107983', '2020-08-31', NULL, 19001, '2112989103189'); -- covid-19 infekcija ++
insert into pacijent_zadrzan (JMBPacijenta, DatumPrijema, DatumOtpustanja, BrojSobe, JMBKontaktOsobe) values ('1907971101492','2023-02-17', NULL, 14003, '1405996101735'); -- shizofrenija ++
insert into pacijent_zadrzan (JMBPacijenta, DatumPrijema, DatumOtpustanja, BrojSobe, JMBKontaktOsobe) values ('0702998129315','2022-05-24', NULL, 19003, '0406972109892'); -- malign obolj ++
select * from pacijent_zadrzan;

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

insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('1211969134934', '2022-07-16'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('1803016112381', '2022-11-20'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('2201984108175', '2023-01-15'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('2304988101562', '2022-12-07'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('2711019139654', '2022-10-31'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('1509967103517', '2023-03-23'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('2605981101865', '2020-06-11'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('1402975106903', '2021-08-21'); -- ++
insert into pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) values ('0111983109487', '2020-02-09'); -- ++
select * from pacijent_nije_zadrzan;

/* Mapiranje slabog entitetskog tipa RACUN prema Pravilu 2
   (automatski i identifikujuceg veznog tipa PLACA_PACIJENT)
   i veznog tipa PLACA_OSIGURANJE (1:M tip veze) prema Pravilu 6A
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

insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (1, 'Septolete', 'Pastile', 7.00); -- Akutni streptokokni tonzilitis ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (2, 'Hemomycin', 'Film tableta', 21.30); -- covid -19, infekcija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (3, 'Panklav', 'Sirup', 12.50); -- akutnog bakterijski sinuzitisa ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (4, 'Uvin H', 'Čaj', 3.60); -- urologija, urinarna infekcija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (5, 'Voltaren', 'Gel', 19.70); -- ortopedija, ozlijeda tetiva i ligamenata ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (6, 'Ampril', 'Tablete', 8.10); -- hipertenzija, kardiologija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (7, 'Gliclada', 'Tablete', 15.90); -- snizava secer u krvi, endokrinologija, hiperglikemija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (8, 'Apaurin', 'Film tablete', 10.20); -- anksioznost ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (9, 'Haldol', 'Otopina za injekciju', 10.60); -- shizofrenija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (10, 'Lexoderm', 'Sprej,krema', 21.50); -- vodene ospice (male boginje) ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (11, 'Naturoplex', 'Film tablete', 18.90); -- urologija, urinarna infekcija ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (12, 'Amoksicilin', 'Pastile', 5.20); -- Akutni streptokokni tonzilitis ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (13, 'Duoclav', 'Film tablete', 11.40); -- bakterijska infekcija zuba ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (14, 'Azibiot', 'Film tablete', 3.30); -- infekcija pluca ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (15, 'Panatus', 'Film tablete', 8.70); -- kasalj ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (16, 'Lupocet junior', 'Sirup', 7.60); -- snizava temperaturu + panklav ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (17, 'Neofen', 'Tablete', 8.10); -- protiv bolova -- ++
insert into lijek (IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka) values (18, 'Ecansya', 'Film tablete', 59.90); -- protiv malign obolj crijeva ++
select * from lijek;

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

delimiter $$
create trigger dodaj_lijek_na_racun after insert
on prepisivanje
for each row
begin
  declare vCijenaLijeka, vCijenaPregleda, vCijenaSobe decimal(6,2) default 0;
  declare vDatumPostoji bool default false;
  
  select count(*)>0 into vDatumPostoji
  from racun
  where DatumRacuna=new.DatumPrepisivanja and JMBPacijenta = new.JMBPacijenta;
  
  select l.CijenaLijeka into vCijenaLijeka 
  from lijek l 
  inner join prepisivanje p on p.IdLijeka=l.IdLijeka where l.IdLijeka=new.IdLijeka limit 1;
  
  if vDatumPostoji then
    update racun
    set CijenaLijekova = CijenaLijekova + vCijenaLijeka*new.KolicinaLijeka
    where DatumRacuna=new.DatumPrepisivanja and JMBPacijenta = new.JMBPacijenta;
  else
    insert into racun values (new.DatumPrepisivanja, new.JMBPacijenta, 0, vCijenaLijeka*new.KolicinaLijeka, 0);
  end if;
end$$
delimiter ;

insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-07-16', '1408987118138', '1211969134934', 11, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-07-16', '1408987118138', '1211969134934', 4, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-10-31', '1911963129179', '2711019139654', 3, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-10-31', '1911963129179', '2711019139654', 16, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2023-01-15', '2609982101124', '2201984108175', 8, 2);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-10-15', '2909982101124', '1502973101187', 14, 2);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-10-15', '2909982101124', '1502973101187', 15, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-12-07', '3003989103106', '2304988101562', 6, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2020-08-31', '1702977107179', '1108969107983', 2, 2);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2020-08-31', '1702977107179', '1108969107983', 17, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-11-20', '0803989103106', '1803016112381', 10, 2);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2023-02-17', '0608977108179', '1907971101492', 9, 6);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2023-03-23', '2004972124116', '1509967103517', 5, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2022-05-24', '1508987112138', '0702998129315', 18, 2);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2020-06-11', '2002972126116', '2605981101865', 7, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2021-08-21', '1404969129462', '1402975106903', 13, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2020-02-09', '1702977107179', '0111983109487', 12, 1);
insert into prepisivanje (DatumPrepisivanja, JMBDoktora, JMBPacijenta, IdLijeka, KolicinaLijeka) values ('2020-02-09', '1702977107179', '0111983109487', 1, 1);
select * from prepisivanje;

-- select p.JMBPacijenta, pnz.Ime, count(IdLijeka) as BrojLijekovaPacijenta
-- from pacijent p 
-- inner join pacijent_nije_zadrzan pnz on pnz.JMBPacijenta=p.JMBPacijenta
-- left outer join prepisivanje pr on pr.DatumPrepisivanja=pnz.DatumDolaska
-- group by JMBPacijenta
-- order by BrojLijekovaPacijenta desc;

/* Mapiranje jakog entitetskog tipa DIJAGNOSTICKI_PREGLED prema Pravilu 1
   i veznog tipa NUDI (1:M tip veze) prema Pravilu 6A
*/
create table dijagnosticki_pregled
(
	IdPregleda int,
    NazivPregleda varchar(30) not null,
    CijenaPregleda decimal(6,2) not null,
    primary key (IdPregleda)
);
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (10101, 'Spec. pregled endokrinologa', 45.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (11101, 'Spec pregled kardiologa', 50.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (12201, 'Spec pregled urologa', 45.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (13301, 'Spec pregled stomatologa', 45.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (14401, 'Spec. pregled psihijatra', 55.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (15501, 'Spec. pregled ortopeda', 45.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (16601, 'Spec. pregled pedijatra', 40.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (17701, 'Spec. pregled onkologa', 50.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (19901, 'Spec. pregled infektologa', 50.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (13302, 'Dentalni RTG', 30.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (14402, 'Psihijatrijska anamneza', 60.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (17702, 'Ultrazvuk abdomena', 70.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (18801, 'CT glave', 140.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (18802, 'RTG pluća', 40.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (11102, 'EKG srca', 20.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (18803, 'RTG koljena', 35.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (111101, 'Krvna slika', 10.00); -- ++
insert into dijagnosticki_pregled (IdPregleda, NazivPregleda, CijenaPregleda) values (19902, 'COVID-19 test', 10.00); -- ++
select * from dijagnosticki_pregled;

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

 delimiter $$
 create trigger dodaj_pregled_na_racun after insert
 on pregledanje
 for each row
 begin
  declare vCijenaLijeka, vCijenaPregleda, vCijenaSobe decimal(6,2) default 0;
  declare vDatumPostoji bool default false;
  
  select count(*)>0 into vDatumPostoji
  from racun
  where DatumRacuna=new.DatumPregleda and JMBPacijenta = new.JMBPacijenta;
  
  select dp.CijenaPregleda into vCijenaPregleda
  from dijagnosticki_pregled dp 
  inner join pregledanje p on p.IdPregleda=dp.IdPregleda where dp.IdPregleda=new.IdPregleda limit 1;
  
  if vDatumPostoji then
    update racun
    set CijenaPregleda = CijenaPregleda + vCijenaPregleda
    where DatumRacuna=new.DatumPregleda and JMBPacijenta = new.JMBPacijenta;
  else
    insert into racun values (new.DatumPregleda, new.JMBPacijenta, vCijenaPregleda, 0, 0);
  end if;
 end$$
 delimiter ;

insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-07-16', '1408987118138', '1211969134934', 12201, 'Urinarna infekcija', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-10-31', '1911963129179', '2711019139654', 16601, 'Akutni bakterijski sinuzitis', 'Upala sinusa i povišena temperatura.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-01-15', '2609982101124', '2201984108175', 14401, 'Anksioznost', 'Početna faza anksioznosti.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-10-15', '2909982101124', '1502973101187', 19901, 'Invazivni kašalj', 'Potreban RTG pluća.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-10-15', '0501974101101', '1502973101187', 18802, 'Infekcija pluća', 'Hitna hospitalizacija.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-12-07', '3003989103106', '2304988101562', 11101, 'Povišen krvni pritisak', 'Potreban EKG srca.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-12-07', '3003989103106', '2304988101562', 11102, 'Hipertenzija', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2020-08-31', '1702977107179', '1108969107983', 19902, 'COVID-19 pozitivan', 'Hospitalizacija.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-11-20', '0803989103106', '1803016112381', 16601, 'Male boginje', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-02-17', '1312969120462', '1907971101492', 18801, 'Sumnja na shizofreniju', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-02-17', '0608977108179', '1907971101492', 14402, 'Shizofrenija', 'Hitna hospitalizacija.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-03-23', '2004972124116', '1509967103517', 15501, 'Ozljeda tetiva', 'Potreban RTG koljena.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2023-03-23', '1312969120462', '1509967103517', 18803, 'Ozljeda tetiva i ligamenata', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-05-24', '0509982101124', '0702998129315', 111101, 'Povišen nivo leukocita', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-05-24', '1508987112138', '0702998129315', 17702, 'Maligno uvećanje crijeva', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2022-05-24', '1508987112138', '0702998129315', 17701, 'Maligno oboljenje crijeva', 'Hospitalizacija.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2020-06-11', '2002972126116', '2605981101865', 10101, 'Hiperglikemija', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2021-08-21', '1404969129462', '1402975106903', 13301, 'Zapaljenje zuba i desni', 'Potreban dentalni RTG.');
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2021-08-21', '1404969129462', '1402975106903', 13302, 'Bakterijska infekcija korijena zuba', NULL);
insert into pregledanje (DatumPregleda, JMBDoktora, JMBPacijenta, IdPregleda, Dijagnoza, Misljenje) values ('2020-02-09', '1702977107179', '0111983109487', 19901, 'Akutni streptokokni tonzilitis', NULL);
select * from pregledanje;

select * from racun;

create trigger postavi_pol_zaposleni before insert
on zaposleni
for each row
set new.Pol = if(substr(new.JMB,10,1) <= '4', 'muški', 'ženski');

insert into zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Telefon) values ('1212970110482', 'Slavko', 'Šarić', 'slavko.saric@gmail.com', '1970-12-12', 'Ulica 304', 2300.00, '+387 (0) 66 851 888');
select * from zaposleni;

create trigger postavi_pol_pacijent before insert
on pacijent
for each row
set new.Pol = if(substr(new.JMBPacijenta,10,1) <= '4', 'muški', 'ženski');

insert into pacijent (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, KrvnaGrupa, DavalacOsiguranja) values ('1608983109107', 'Vera', 'Drašković','1983-08-16', 'Ulica 404','+387 (0) 65 332 257', 'AB', 'FZO RS');
select * from pacijent;

 delimiter $$
 create trigger dodaj_sobu_na_racun after update 
 on pacijent_zadrzan
 for each row
 begin
  declare vCijenaLijeka, vCijenaPregleda, vCijenaSobe, vCijenaSobeUkupno decimal(6,2) default 0;
  declare vDatumPostoji bool default false;
  
  select count(*)>0 into vDatumPostoji
  from racun
  where DatumRacuna=new.DatumOtpustanja and JMBPacijenta = new.JMBPacijenta;
  
  select s.CijenaSobe into vCijenaSobe
  from soba s 
  inner join pacijent_zadrzan pz on pz.BrojSobe=s.BrojSobe where s.BrojSobe=new.BrojSobe limit 1;
  
  set vCijenaSobeUkupno = datediff(new.DatumOtpustanja, new.DatumPrijema) * vCijenaSobe;
  
  if vDatumPostoji then
    update racun
    set CijenaSobe = CijenaSobe + vCijenaSobeUkupno
    where DatumRacuna=new.DatumOtpustanja and JMBPacijenta = new.JMBPacijenta;
  else
    insert into racun values (new.DatumOtpustanja, new.JMBPacijenta, 0, 0, vCijenaSobeUkupno);
  end if;
 end$$
 delimiter ;
 
update pacijent_zadrzan set DatumOtpustanja = '2022-10-18' where JMBPacijenta = '1502973101187';
update pacijent_zadrzan set DatumOtpustanja = '2020-09-06' where JMBPacijenta = '1108969107983';
update pacijent_zadrzan set DatumOtpustanja = '2023-02-22' where JMBPacijenta = '1907971101492';
update pacijent_zadrzan set DatumOtpustanja = '2022-05-30' where JMBPacijenta = '0702998129315';
select * from pacijent_zadrzan;
select * from racun;

select * from lijek;
select * from zdravstveno_osiguranje;
select * from kontakt_osoba;
select * from zaposleni;
select * from doktor;

drop view if exists odjel_doktor_med_tehnicar;
create view odjel_doktor_med_tehnicar (IdOdjela, NazivOdjela, Adresa, JMBSO, ImeSO, PrezimeSO, EmailSO, DatumRodjenjaSO, AdresaSO, PlataSO, PolSO, TelefonSO, 
Specijalizacija, JMBGMS, ImeGMS, PrezimeGMS, EmailGMS, DatumRodjenjaGMS, AdresaGMS, PlataGMS, PolGMS, TelefonGMS, StrucnaSprema) as
select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, 
za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema
from odjel o
inner join doktor d on d.JMB=o.JMBSefaOdjela
inner join zaposleni z on z.JMB=d.JMB
inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara
inner join zaposleni za on za.JMB=mst.JMB
order by o.IdOdjela asc;

select * from odjel_doktor_med_tehnicar;
select * from odjel;

-- Ne koristim
drop view if exists telefoni_odjela;
create view telefoni_odjela (IdOdjela, NazivOdjela, Adresa, JMBSO, ImeSO, PrezimeSO, EmailSO, DatumRodjenjaSO, AdresaSO, PlataSO, PolSO, TelefonSO, 
Specijalizacija, JMBGMS, ImeGMS, PrezimeGMS, EmailGMS, DatumRodjenjaGMS, AdresaGMS, PlataGMS, PolGMS, TelefonGMS, StrucnaSprema, Telefon) as
select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, 
za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema, t.Telefon
from odjel o
inner join doktor d on d.JMB=o.JMBSefaOdjela
inner join zaposleni z on z.JMB=d.JMB
inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara
inner join zaposleni za on za.JMB=mst.JMB
inner join telefon_odjela t on t.IdOdjela=o.IdOdjela
group by o.IdOdjela
order by o.IdOdjela asc;

select * from telefoni_odjela;

select * from telefon_odjela;

delimiter $$
create procedure azuriraj_telefon_odjela(in pIdOdjela int, in pTelefon varchar(20), in ppTelefon varchar(20))
begin
    update telefon_odjela
    set Telefon=pTelefon
    where IdOdjela=pIdOdjela and Telefon=ppTelefon;
end$$
delimiter ;


select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, 
zo.Adresa, zo.Telefon
from pacijent p
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
order by p.Prezime asc;

select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, 
zo.Adresa, zo.Telefon, pnz.DatumDolaska
from pacijent p
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
inner join pacijent_nije_zadrzan pnz on pnz.JMBPacijenta=p.JMBPacijenta
order by p.Prezime asc;

 -- drop trigger postavi_datum_dolaska;
-- create trigger postavi_datum_dolaska before insert
-- on pacijent_nije_zadrzan
-- for each row
-- set new.DatumDolaska = curDate();

-- insert into pacijent_nije_zadrzan (JMBPacijenta) values ('1608983109107');
select * from pacijent_nije_zadrzan;

select rn.DatumZaposlenja, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon,
o.IdOdjela, o.NazivOdjela, o.Adresa, za.JMB, za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, 
za.Pol, za.Telefon, d.Specijalizacija, zap.JMB, zap.Ime, zap.Prezime, zap.Email, zap.DatumRodjenja, zap.Adresa, zap.Plata,
zap.Pol, zap.Telefon, mst.StrucnaSprema
from radi_na rn
inner join zaposleni z on z.JMB=rn.JMB
inner join odjel o on o.IdOdjela=rn.IdOdjela
inner join doktor d on d.JMB=o.JMBSefaOdjela
inner join zaposleni za on za.JMB=d.JMB
inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara
inner join zaposleni zap on zap.JMB=mst.JMB
where rn.IdOdjela = 16000
order by z.Prezime asc;

-- DELETE FROM radi_na where DatumZaposlenja='2016-01-13' and JMB='1608983109107' AND IdOdjela=16000;
select * from radi_na where IdOdjela=16000;
select * from odjel;
select * from radi_na;

select pr.DatumPrepisivanja, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon,
d.Specijalizacija, p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja,
zo.Adresa, zo.Telefon, l.IdLijeka, l.NazivLijeka, l.TipLijeka, l.CijenaLijeka, pr.KolicinaLijeka
from prepisivanje pr
inner join doktor d on d.JMB=pr.JMBDoktora
inner join zaposleni z on z.JMB=d.JMB
inner join pacijent p on p.JMBPacijenta=pr.JMBPacijenta
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
inner join lijek l on l.IdLijeka=pr.IdLijeka
order by pr.DatumPrepisivanja asc;

select * from prepisivanje;
select * from racun;
select * from soba;
select * from dijagnosticki_pregled;

drop view if exists zadrzani_pacijenti;
create view zadrzani_pacijenti (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranjaZO, 
AdresaZO, TelefonZO, DatumPrijema, DatumOtpustanja, BrojSobe, CijenaSobe, BrojKreveta,
JMBKontaktOsobe, ImeKO, PrezimeKO, TelefonKO) as
select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, 
zo.Adresa, zo.Telefon, pz.DatumPrijema, pz.DatumOtpustanja, s.BrojSobe, s.CijenaSobe, s.BrojKreveta,
ko.JMBKontaktOsobe, ko.Ime, ko.Prezime, ko.Telefon
from pacijent p
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
inner join pacijent_zadrzan pz on pz.JMBPacijenta=p.JMBPacijenta
inner join soba s on s.BrojSobe=pz.BrojSobe
inner join kontakt_osoba ko on ko.JMBKontaktOsobe=pz.JMBKontaktOsobe
order by p.Prezime asc;

select * from pacijent_zadrzan;
select * from racun;
select * from soba;

create trigger postavi_podrazumijevani_datum_otpustanja before insert
on pacijent_zadrzan
for each row
set new.DatumOtpustanja = '1000-01-01';
select * from pacijent_nije_zadrzan;

select pr.DatumPregleda, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon,
d.Specijalizacija, p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja,
zo.Adresa, zo.Telefon, preg.IdPregleda, preg.NazivPregleda, preg.CijenaPregleda, pr.Dijagnoza, pr.Misljenje
from pregledanje pr
inner join doktor d on d.JMB=pr.JMBDoktora
inner join zaposleni z on z.JMB=d.JMB
inner join pacijent p on p.JMBPacijenta=pr.JMBPacijenta
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
inner join dijagnosticki_pregled preg on preg.IdPregleda=pr.IdPregleda
order by pr.DatumPregleda asc;

select * from pregledanje;
