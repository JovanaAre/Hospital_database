package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.TelefonOdjela;

@SuppressWarnings("serial")
public class TelefonOdjelaTableModel extends AbstractTableModel{
	
	private List<TelefonOdjela> podaci;
	String[] kolone = new String[] { "Identifikator", "Naziv odjela", "Telefon" };

	public TelefonOdjelaTableModel(List<TelefonOdjela> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<TelefonOdjela> podaci) {
		this.podaci = podaci;
	}

	public TelefonOdjela getTelefonOdjelaAtRow(int rowIndex) {
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
		TelefonOdjela red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getOdjel().getIdOdjela();
		else if (columnIndex == 1)
			return red.getOdjel().getNazivOdjela();
		else if (columnIndex == 2)
			return red.getTelefon();
		else
			return null;
	}
}

