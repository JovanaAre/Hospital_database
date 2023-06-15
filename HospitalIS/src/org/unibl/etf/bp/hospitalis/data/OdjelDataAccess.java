package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Odjel;

public interface OdjelDataAccess {
	
	 List<Odjel> sviOdjeli();
	 Odjel odjel(int idOdjela);
	 List<Odjel> odjeli(String nazivOdjela);
	 boolean dodajOdjel(Odjel odjel);
	 boolean azurirajOdjel(Odjel odjel);
	 boolean obrisiOdjel(int idOdjela);	
}

