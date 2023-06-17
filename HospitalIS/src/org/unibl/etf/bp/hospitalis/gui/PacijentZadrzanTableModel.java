package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.PacijentZadrzan;

@SuppressWarnings("serial")
public class PacijentZadrzanTableModel extends AbstractTableModel{
	
	private List<PacijentZadrzan> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "Datum prijema", "Datum otpuštanja",
			"Broj sobe", "Ime kontakt osobe", "Prezime kontakt osobe", "Broj telefona kontakt osobe"};

	public PacijentZadrzanTableModel(List<PacijentZadrzan> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<PacijentZadrzan> podaci) {
		this.podaci = podaci;
	}

	public PacijentZadrzan getPacijentZadrzanAtRow(int rowIndex) {
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
		PacijentZadrzan red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getPacijent().getJmbPacijenta();
		else if (columnIndex == 1)
			return red.getPacijent().getIme();
		else if (columnIndex == 2)
			return red.getPacijent().getPrezime();
		else if (columnIndex == 3)
			return red.getDatumPrijema();
		else if (columnIndex == 4)
			return red.getDatumOtpustanja();
		else if (columnIndex == 5)
			return red.getSoba().getBrojSobe();
		else if (columnIndex == 6)
			return red.getKontaktOsoba().getIme();
		else if (columnIndex == 7)
			return red.getKontaktOsoba().getPrezime();
		else if (columnIndex == 8)
			return red.getKontaktOsoba().getTelefon();
		else
			return null;
	}
}

