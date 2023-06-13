package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Odjel;

@SuppressWarnings("serial")
public class OdjelTableModel extends AbstractTableModel{
	
	private List<Odjel> podaci;
	String[] kolone = new String[] { "Identifikator", "Naziv", "Adresa", "Ime šefa odjela",
			"Prezime šefa odjela", "Ime glavne sestre", "Prezime glavne sestre" };

	public OdjelTableModel(List<Odjel> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Odjel> podaci) {
		this.podaci = podaci;
	}

	public Odjel getOdjelAtRow(int rowIndex) {
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
		Odjel red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getIdOdjela();
		else if (columnIndex == 1)
			return red.getNazivOdjela();
		else if (columnIndex == 2)
			return red.getAdresa();
		else if (columnIndex == 3)
			return red.getDoktor().getZaposleni().getIme();
		else if (columnIndex == 4)
			return red.getDoktor().getZaposleni().getPrezime();
		else if (columnIndex == 5)
			return red.getMedicinskiTehnicar().getZaposleni().getIme();
		else if (columnIndex == 6)
			return red.getMedicinskiTehnicar().getZaposleni().getPrezime();
		else
			return null;
	}
}
