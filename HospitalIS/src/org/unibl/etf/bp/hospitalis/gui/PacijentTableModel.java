package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Pacijent;

@SuppressWarnings("serial")
public class PacijentTableModel extends AbstractTableModel{
	
	private List<Pacijent> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "DatumRodjenja", "Adresa", "Telefon",
			"Pol", "Krvna grupa", "Davalac zdravstvenog osiguranja"};

	public PacijentTableModel(List<Pacijent> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Pacijent> podaci) {
		this.podaci = podaci;
	}

	public Pacijent getPacijentAtRow(int rowIndex) {
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
		Pacijent red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getJmbPacijenta();
		else if (columnIndex == 1)
			return red.getIme();
		else if (columnIndex == 2)
			return red.getPrezime();
		else if (columnIndex == 3)
			return red.getDatumRodjenja();
		else if (columnIndex == 4)
			return red.getAdresa();
		else if (columnIndex == 5)
			return red.getTelefon();
		else if (columnIndex == 6)
			return red.getPol();
		else if (columnIndex == 7)
			return red.getKrvnaGrupa();
		else if (columnIndex == 8)
			return red.getZdravstvenoOsiguranje().getDavalacOsiguranja();
		
		else
			return null;
	}
}
