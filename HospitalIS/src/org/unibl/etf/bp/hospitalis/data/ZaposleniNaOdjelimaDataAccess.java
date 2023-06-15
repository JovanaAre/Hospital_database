package org.unibl.etf.bp.hospitalis.data;

import java.util.List;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.entity.ZaposleniNaOdjelima;

public interface ZaposleniNaOdjelimaDataAccess {
	
	List<ZaposleniNaOdjelima> zaposleniNaOdjelima(int idOdjela);
	boolean dodajZaposlenogNaOdjelu(ZaposleniNaOdjelima zaposleniNaOdjelu);
	// boolean azurirajZaposlenogNaOdjelu(ZaposleniNaOdjelima zaposleniNaOdjelu);
	boolean obrisiZaposlenogNaOdjelu(Date datumZaposlenja, String jmbZaposlenog, int idOdjela);
}