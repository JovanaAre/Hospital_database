-- korisnicki nalog
drop user 'referent'@'localhost';
create user 'referent'@'localhost' identified by 'referent';
grant select, insert, update, delete on bolnica.* to 'referent'@'localhost';
grant execute on procedure bolnica.prikazi_racune to 'referent'@'localhost';
grant execute on procedure bolnica.prosjecne_plate_doktora_odjeli to 'referent'@'localhost';
grant execute on procedure bolnica.prosjecne_plate_med_tehnicara_odjeli to 'referent'@'localhost';
grant execute on procedure bolnica.ukupan_broj_zaposlenih_odjeli to 'referent'@'localhost';
flush privileges;