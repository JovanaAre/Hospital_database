-- korisnicki nalog
drop user 'referent'@'localhost';
create user 'referent'@'localhost' identified by 'referent';
grant select, insert, update, delete on uniis_prosireno.* to 'referent'@'localhost';
grant select, insert, update, delete on bolnica.* to 'referent'@'localhost';
flush privileges;