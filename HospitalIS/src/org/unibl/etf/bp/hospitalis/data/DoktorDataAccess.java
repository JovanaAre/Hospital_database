package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Doktor;

public interface DoktorDataAccess {
	
	List<Doktor> sviDoktori();
	List<Doktor> doktori(String jmbZaposlenog);
	boolean dodajDoktora(Doktor doktor);
	boolean azurirajDoktora(Doktor doktor);
	boolean obrisiDoktora(String jmbZaposlenog);
	
}