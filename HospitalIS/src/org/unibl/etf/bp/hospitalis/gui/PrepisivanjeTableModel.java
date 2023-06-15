package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;
import java.sql.Date;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Prepisivanje;

@SuppressWarnings("serial")
public class PrepisivanjeTableModel extends AbstractTableModel{
	
	private List<Prepisivanje> podaci;
	String[] kolone = new String[] { "Datum prepisivanja", "Ime doktora", "Prezime doktora",
			 "Ime pacijenta", "Prezime pacijenta", "Naziv lijeka", "Količina lijeka" };

	public PrepisivanjeTableModel(List<Prepisivanje> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Prepisivanje> podaci) {
		this.podaci = podaci;
	}

	public Prepisivanje getPrepisivanjeAtRow(int rowIndex) {
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
		Prepisivanje red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getDatumPrepisivanja();
		else if (columnIndex == 1)
			return red.getDoktor().getZaposleni().getIme();
		else if (columnIndex == 2)
			return red.getDoktor().getZaposleni().getPrezime();
		else if (columnIndex == 3)
			return red.getPacijent().getIme();
		else if (columnIndex == 4)
			return red.getPacijent().getPrezime();
		else if (columnIndex == 5)
			return red.getLijek().getNazivLijeka();
		else if (columnIndex == 6)
			return red.getKolicinaLijeka();
		else
			return null;
	}
}
