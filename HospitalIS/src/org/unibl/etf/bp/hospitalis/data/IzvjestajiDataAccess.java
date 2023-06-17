package org.unibl.etf.bp.hospitalis.data;

import java.util.Vector;

public interface IzvjestajiDataAccess {
	
	Vector<Vector<Object>> racuniPacijenata();
	Vector<Vector<Object>> ukupanIznosRacuna2022();
	Vector<Vector<Object>> ukupanIznosRacunaTekucaGodina();
	Vector<Vector<Object>> prosjecnePlateDoktoraOdjeli();
	Vector<Vector<Object>> prosjecnePlateMedicinskihTehnicaraOdjeli();
	Vector<Vector<Object>> ukupanBrojZaposlenihOdjeli();
	Vector<Vector<Object>> ukupnoEvidentiranihPacijenataTekucaGodina();
	
}