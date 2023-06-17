package org.unibl.etf.bp.hospitalis.gui;


import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;

@SuppressWarnings("serial")
public class DijagnostickiPregledTableModel extends AbstractTableModel {
	
	List<DijagnostickiPregled> podaci;
	String[] kolone = new String[] { "Identifikator", "Naziv pregleda", "Cijena"};

	public DijagnostickiPregledTableModel(List<DijagnostickiPregled> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<DijagnostickiPregled> podaci) {
		this.podaci = podaci;
	}

	public DijagnostickiPregled getDijagnostickiPregledAtRow(int rowIndex) {
		return podaci.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public int getRowCount() {
		return podaci.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DijagnostickiPregled red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getIdPregleda();
		else if (columnIndex == 1)
			return red.getNazivPregleda();
		else if (columnIndex == 2)
			return red.getCijenaPregleda();
		else
			return null;
	}
}
