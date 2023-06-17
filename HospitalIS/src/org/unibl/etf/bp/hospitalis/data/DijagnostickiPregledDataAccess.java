package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;

public interface DijagnostickiPregledDataAccess {
	
	DijagnostickiPregled dijagnostickiPregled(int idPregleda);
	List<DijagnostickiPregled> dijagnostickiPregledi(String nazivPregleda);
	boolean dodajDijagnostickiPregled(DijagnostickiPregled dijagnostickiPregled);
	boolean azurirajDijagnostickiPregled(DijagnostickiPregled dijagnostickiPregled);
	boolean obrisiDijagnostickiPregled(int idPregleda);

}