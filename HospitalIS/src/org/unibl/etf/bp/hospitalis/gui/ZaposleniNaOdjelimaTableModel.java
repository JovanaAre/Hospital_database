package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;
import java.sql.Date;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.ZaposleniNaOdjelima;

@SuppressWarnings("serial")
public class ZaposleniNaOdjelimaTableModel extends AbstractTableModel {
	
	private List<ZaposleniNaOdjelima> podaci;
	String[] kolone = new String[] { "Datum zaposlenja",
			"JMB", "Ime zaposlenog", "Prezime zaposlenog" };

	public ZaposleniNaOdjelimaTableModel(List<ZaposleniNaOdjelima> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<ZaposleniNaOdjelima> podaci) {
		this.podaci = podaci;
	}

	public ZaposleniNaOdjelima getZaposleniNaOdjelimaAtRow(
			int rowIndex) {
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
		ZaposleniNaOdjelima red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getDatumZaposlenja();
		else if (columnIndex == 1)
			return red.getZaposleni().getJmb();
		else if (columnIndex == 2)
			return red.getZaposleni().getIme();
		else if (columnIndex == 3)
			return red.getZaposleni().getPrezime();
		else
			return null;
	}

}
