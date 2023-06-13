package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;

public interface ZdravstvenoOsiguranjeDataAccess {
	
	ZdravstvenoOsiguranje zdravstvenoOsiguranje(int idOsiguranja);
	List<ZdravstvenoOsiguranje> zdravstvenaOsiguranja(String davalacOsiguranja);
	boolean dodajZdravstvenoOsiguranje(ZdravstvenoOsiguranje zdravstvenoOsiguranje);
	boolean azurirajZdravstvenoOsiguranje(ZdravstvenoOsiguranje zdravstvenoOsiguranje);
	boolean obrisiZdravstvenoOsiguranje(int idOsiguranja);

}
