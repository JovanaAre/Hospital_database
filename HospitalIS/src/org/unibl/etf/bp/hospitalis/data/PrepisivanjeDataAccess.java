package org.unibl.etf.bp.hospitalis.data;

import java.util.List;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.entity.Prepisivanje;

public interface PrepisivanjeDataAccess {
	
	 List<Prepisivanje> svaPrepisivanja();
	 // List<Prepisivanje> prepisivanja(String);
	 boolean dodajPrepisivanje(Prepisivanje prepisivanje);
	 boolean azurirajPrepisivanje(Prepisivanje prepisivanje);
	 boolean obrisiPrepisivanje(Date datumPrepisivanja, String jmbDoktora, String jmbPacijenta, int idLijeka);	
}
