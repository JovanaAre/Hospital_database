package org.unibl.etf.bp.hospitalis.data;

import java.sql.Date;
import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.PacijentZadrzan;

public interface PacijentZadrzanDataAccess {
	
	List<PacijentZadrzan> sviZadrzaniPacijenti();
	List<PacijentZadrzan> zadrzaniPacijenti(String jmbPacijenta);
	boolean dodajZadrzanogPacijenta(PacijentZadrzan pacijentZadrzan);
	boolean azurirajZadrzanogPacijenta(PacijentZadrzan pacijentZadrzan);
	boolean obrisiZadrzanogPacijenta(String jmbPacijenta, Date datumPrijema);
}