package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;

@SuppressWarnings("serial")
public class KontaktOsobaTableModel extends AbstractTableModel {
	
	List<KontaktOsoba> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "Telefon"};

	public KontaktOsobaTableModel(List<KontaktOsoba> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<KontaktOsoba> podaci) {
		this.podaci = podaci;
	}

	public KontaktOsoba getOsobuAtRow(int rowIndex) {
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
		KontaktOsoba red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getJmbKontaktOsobe();
		else if (columnIndex == 1)
			return red.getIme();
		else if (columnIndex == 2)
			return red.getPrezime();
		else if (columnIndex == 3)
			return red.getTelefon();
		else
			return null;
	}
}
