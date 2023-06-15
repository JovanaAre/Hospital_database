package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.PacijentNijeZadrzan;

@SuppressWarnings("serial")
public class PacijentNijeZadrzanTableModel extends AbstractTableModel{
	
	private List<PacijentNijeZadrzan> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "Datum dolaska" };

	public PacijentNijeZadrzanTableModel(List<PacijentNijeZadrzan> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<PacijentNijeZadrzan> podaci) {
		this.podaci = podaci;
	}

	public PacijentNijeZadrzan getPacijentNijeZadrzanAtRow(int rowIndex) {
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
		PacijentNijeZadrzan red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getPacijent().getJmbPacijenta();
		else if (columnIndex == 1)
			return red.getPacijent().getIme();
		else if (columnIndex == 2)
			return red.getPacijent().getPrezime();
		else if (columnIndex == 3)
			return red.getDatumDolaska();
		else
			return null;
	}
}
