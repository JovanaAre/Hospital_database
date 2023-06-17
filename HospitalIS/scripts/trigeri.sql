drop trigger if exists postavi_pol_zaposleni;
create trigger postavi_pol_zaposleni before insert
on zaposleni
for each row
set new.Pol = if(substr(new.JMB,10,1) <= '4', 'muški', 'ženski');

drop trigger if exists postavi_pol_pacijent;
create trigger postavi_pol_pacijent before insert
on pacijent
for each row
set new.Pol = if(substr(new.JMBPacijenta,10,1) <= '4', 'muški', 'ženski');

drop trigger if exists postavi_podrazumijevani_datum_otpustanja;
create trigger postavi_podrazumijevani_datum_otpustanja before insert
on pacijent_zadrzan
for each row
set new.DatumOtpustanja = '1000-01-01'; -- podrazumijevani datum otpustanja
select * from pacijent_nije_zadrzan;

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

drop trigger if exists dodaj_lijek_na_racun;
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

drop trigger if exists dodaj_pregled_na_racun;
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
 
 drop trigger if exists dodaj_sobu_na_racun;
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
 
drop trigger if exists azuriraj_cijenu_lijekova;
delimiter $$
create trigger azuriraj_cijenu_lijekova before update 
on prepisivanje
for each row
begin
  declare vCijenaLijeka, vStaraKolicinaLijeka, vNovaKolicinaLijeka decimal(6,2) default 0;

  select l.CijenaLijeka into vCijenaLijeka 
  from lijek l 
  inner join prepisivanje p on p.IdLijeka=l.IdLijeka where l.IdLijeka=new.IdLijeka limit 1;
  
    update racun
    set CijenaLijekova = vCijenaLijeka*new.KolicinaLijeka
    where DatumRacuna=new.DatumPrepisivanja and JMBPacijenta = new.JMBPacijenta;
end$$
delimiter ;