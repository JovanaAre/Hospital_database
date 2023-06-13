package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Lijek;

public interface LijekDataAccess {
	
	Lijek lijek(int idLijeka);
	List<Lijek> lijekovi(String nazivLijeka);
	boolean dodajLijek(Lijek lijek);
	boolean azurirajLijek(Lijek lijek);
	boolean obrisiLijek(int idLijeka);

}
