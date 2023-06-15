package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Zaposleni;

public interface ZaposleniDataAccess {
	
	Zaposleni zaposlen(String jmbZaposlenog);
	List<Zaposleni> zaposleni(String jmb);
	boolean dodajZaposlenog(Zaposleni zaposleni);
	boolean azurirajZaposlenog(Zaposleni zaposleni);
	boolean obrisiZaposlenog(String jmb);
}
