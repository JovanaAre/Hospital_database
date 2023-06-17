drop procedure if exists prikazi_racune;
delimiter $$
create procedure prikazi_racune()
begin
    select r.DatumRacuna, r.JMBPacijenta, concat(p.Prezime, ', ', p.Ime) as PrezimeIIme,
           r.CijenaPregleda, r.CijenaLijekova, r.CijenaSobe,
           (r.CijenaPregleda + r.CijenaLijekova + r.CijenaSobe) as UkupanIznosRacuna
    from racun r
    inner join pacijent p on r.JMBPacijenta = p.JMBPacijenta;
end$$
delimiter ;

-- call prikazi_racune();

drop procedure if exists prosjecne_plate_doktora_odjeli;
delimiter $$
create procedure prosjecne_plate_doktora_odjeli()
begin
    select o.NazivOdjela, round(avg(z.Plata), 2) as ProsjecnaPlata
    from radi_na rn
    inner join odjel o on o.IdOdjela=rn.IdOdjela
    inner join zaposleni z on z.JMB=rn.JMB
    inner join doktor d on d.JMB=z.JMB
    group by rn.IdOdjela
    order by ProsjecnaPlata desc;
end$$
delimiter ;

-- call prosjecne_plate_doktora_odjeli();

drop procedure if exists prosjecne_plate_med_tehnicara_odjeli;
delimiter $$
create procedure prosjecne_plate_med_tehnicara_odjeli()
begin
    select o.NazivOdjela, round(avg(z.Plata), 2) as ProsjecnaPlata
    from radi_na rn
    inner join odjel o on o.IdOdjela=rn.IdOdjela
    inner join zaposleni z on z.JMB=rn.JMB
    inner join med_sestra_tehnicar mst on mst.JMB=z.JMB
    group by rn.IdOdjela
    order by ProsjecnaPlata desc;
end$$
delimiter ;

-- call prosjecne_plate_med_tehnicara_odjeli();

drop procedure if exists ukupan_broj_zaposlenih_odjeli;
delimiter $$
create procedure ukupan_broj_zaposlenih_odjeli()
begin
    select o.NazivOdjela, count(rn.DatumZaposlenja) as BrojZaposlenih
    from radi_na rn
    inner join odjel o on o.IdOdjela=rn.IdOdjela
    inner join zaposleni z on z.JMB=rn.JMB
    group by rn.IdOdjela
    order by BrojZaposlenih desc;
end$$
delimiter ;

-- call ukupan_broj_zaposlenih_odjeli();