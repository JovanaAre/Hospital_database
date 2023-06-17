package org.unibl.etf.bp.hospitalis.data;

import java.util.List;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.entity.PacijentNijeZadrzan;

public interface PacijentNijeZadrzanDataAccess {
	
	List<PacijentNijeZadrzan> sviNezadrzaniPacijenti();
	List<PacijentNijeZadrzan> nezadrzaniPacijenti(String jmbPacijenta);
	boolean dodajNezadrzanogPacijenta(PacijentNijeZadrzan pacijentNijeZadrzan);
	// boolean azurirajNezadrzanogPacijenta(PacijentNijeZadrzan pacijentNijeZadrzan);
	boolean obrisiNezadrzanogPacijenta(String jmbPacijenta, Date datumDolaska);
	
}