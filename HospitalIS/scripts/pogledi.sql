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

-- select * from odjel_doktor_med_tehnicar;

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
order by o.IdOdjela asc;

-- select * from telefoni_odjela;

drop view if exists pacijenti;
create view pacijenti (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranja, 
AdresaZO, TelefonZO) as
select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, 
zo.Adresa, zo.Telefon
from pacijent p
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
order by p.Prezime asc;

-- select * from pacijenti;

drop view if exists nezadrzani_pacijenti;
create view nezadrzani_pacijenti (JMBPacijenta, Ime, Prezime, DatumRodjenja, Adresa, Telefon, Pol, KrvnaGrupa, DavalacOsiguranjaZO, 
AdresaZO, TelefonZO, DatumDolaska) as
select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, 
zo.Adresa, zo.Telefon, pnz.DatumDolaska
from pacijent p
inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja
inner join pacijent_nije_zadrzan pnz on pnz.JMBPacijenta=p.JMBPacijenta
order by p.Prezime asc;

-- select * from nezadrzani_pacijenti;

drop view if exists zaposleni_odjeli;
create view zaposleni_odjeli (DatumZaposlenja, JMBZ, ImeZ, PrezimeZ, EmailZ, DatumRodjenjaZ, AdresaZ, PlataZ, PolZ, TelefonZ,
IdOdjela, NazivOdjela, AdresaO, JMBZA, ImeZA, PrezimeZA, EmailZA, DatumRodjenjaZA, AdresaZA, PlataZA, 
PolZA, TelefonZA, Specijalizacija, JMBZAP, ImeZAP, PrezimeZAP, EmailZAP, DatumRodjenjaZAP, AdresaZAP, PlataZAP,
PolZAP, TelefonZAP, StrucnaSprema) as
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
order by z.Prezime asc;

-- select * from zaposleni_odjeli;

drop view if exists prepisivanja_lijekova;
create view prepisivanja_lijekova (DatumPrepisivanja, JMBZ, ImeZ, PrezimeZ, EmailZ, DatumRodjenjaZ, AdresaZ, PlataZ, PolZ, TelefonZ,
Specijalizacija, JMBPacijenta, ImeP, PrezimeP, DatumRodjenjaP, AdresaP, TelefonP, PolP, KrvnaGrupaP, DavalacOsiguranja,
AdresaZO, TelefonZO, IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka, KolicinaLijeka) as
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

-- select * from prepisivanja_lijekova;

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

-- select * from zadrzani_pacijenti;

drop view if exists pregledanja_pacijenata;
create view pregledanja_pacijenata (DatumPregleda, JMBZ, ImeZ, PrezimeZ, EmailZ, DatumRodjenjaZ, AdresaZ, PlataZ, PolZ, TelefonZ,
Specijalizacija, JMBPacijenta, ImeP, PrezimeP, DatumRodjenjaP, AdresaP, TelefonP, PolP, KrvnaGrupaP, DavalacOsiguranja,
AdresaZO, TelefonZO, IdPregleda, NazivPregleda, CijenaPregleda, Dijagnoza, Misljenje) as
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

-- select * from pregledanja_pacijenata;

drop view if exists ukupan_iznos_racuna_2022;
create view ukupan_iznos_racuna_2022 as 
select sum(CijenaPregleda + CijenaLijekova + CijenaSobe) as UkupanIznos
from racun
where year(DatumRacuna)='2022';
select * from ukupan_iznos_racuna_2022;

-- select * from ukupan_iznos_racuna_2022;

drop view if exists ukupan_iznos_racuna_tekuca_godina;
create view ukupan_iznos_racuna_tekuca_godina as 
select sum(CijenaPregleda + CijenaLijekova + CijenaSobe) as UkupanIznos
from racun
where year(DatumRacuna)=year(curdate());
select * from ukupan_iznos_racuna_tekuca_godina;

-- select * from ukupan_iznos_racuna_tekuca_godina;

drop view if exists ukupno_evidentiranih_pacijenata_tekuca_godina;
create view ukupno_evidentiranih_pacijenata_tekuca_godina as 
select count(*) as Ukupno
from (select DatumDolaska from pacijent_nije_zadrzan
union all
select DatumPrijema from pacijent_zadrzan) as pacijenti
where year(DatumDolaska)=year(curdate()) or year(DatumDolaska)=year(curdate());

-- select * from ukupno_evidentiranih_pacijenata_tekuca_godina;
