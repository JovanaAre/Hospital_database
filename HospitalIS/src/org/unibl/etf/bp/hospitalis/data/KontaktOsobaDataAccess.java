package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;

public interface KontaktOsobaDataAccess {
	
	List<KontaktOsoba> kontaktOsobe(String jmbKontaktOsobe);
	boolean dodajKontaktOsobu(KontaktOsoba kontaktOsoba);
	boolean azurirajKontaktOsobu(KontaktOsoba kontaktOsoba);
	boolean obrisiKontaktOsobu(String jmbKontaktOsobe);
	
}