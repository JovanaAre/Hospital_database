package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;

public interface MedicinskiTehnicarDataAccess {
	
	List<MedicinskiTehnicar> sviMedicinskiTehnicari();
	List<MedicinskiTehnicar> medicinskiTehnicari(String jmbZaposlenog);
	boolean dodajMedicinskogTehnicara(MedicinskiTehnicar medicinskiTehnicar);
	boolean azurirajMedicinskogTehnicara(MedicinskiTehnicar medicinskiTehnicar);
	boolean obrisiMedicinskogTehnicara(String jmbZaposlenog);
	
}