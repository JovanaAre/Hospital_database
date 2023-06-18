package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.Soba;

public interface SobaDataAccess {
	
	Soba soba(int brojSobe);
	List<Soba> sveSobe();
	boolean dodajSobu(Soba soba);
	boolean azurirajSobu(Soba soba);
	boolean obrisiSobu(int brojSobe);
}
