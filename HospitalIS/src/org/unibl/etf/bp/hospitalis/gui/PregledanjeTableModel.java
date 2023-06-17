package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;
import java.sql.Date;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Pregledanje;

@SuppressWarnings("serial")
public class PregledanjeTableModel extends AbstractTableModel{
	
	private List<Pregledanje> podaci;
	String[] kolone = new String[] { "Datum pregleda", "Ime doktora", "Prezime doktora",
			 "Ime pacijenta", "Prezime pacijenta", "Naziv pregleda", "Dijagnoza", "Mišljenje" };

	public PregledanjeTableModel(List<Pregledanje> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Pregledanje> podaci) {
		this.podaci = podaci;
	}

	public Pregledanje getPregledanjeAtRow(int rowIndex) {
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
		Pregledanje red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getDatumPregleda();
		else if (columnIndex == 1)
			return red.getDoktor().getZaposleni().getIme();
		else if (columnIndex == 2)
			return red.getDoktor().getZaposleni().getPrezime();
		else if (columnIndex == 3)
			return red.getPacijent().getIme();
		else if (columnIndex == 4)
			return red.getPacijent().getPrezime();
		else if (columnIndex == 5)
			return red.getDijagnostickiPregled().getNazivPregleda();
		else if (columnIndex == 6)
			return red.getDijagnoza();
		else if (columnIndex == 7)
			return red.getMisljenje();
		else
			return null;
	}
}

