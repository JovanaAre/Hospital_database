package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Pacijent;

public interface PacijentDataAccess {
	
	List<Pacijent> pacijenti(String jmbPacijenta);
	boolean dodajPacijenta(Pacijent pacijent);
	boolean azurirajPacijenta(Pacijent pacijent);
	boolean obrisiPacijenta(String jmbPacijenta);
}

