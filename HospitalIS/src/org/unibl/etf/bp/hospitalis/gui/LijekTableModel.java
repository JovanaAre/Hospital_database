package org.unibl.etf.bp.hospitalis.gui;


import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Lijek;

@SuppressWarnings("serial")
public class LijekTableModel extends AbstractTableModel {
	
	List<Lijek> podaci;
	String[] kolone = new String[] { "Identifikator", "Naziv lijeka", "Tip", "Cijena"};

	public LijekTableModel(List<Lijek> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Lijek> podaci) {
		this.podaci = podaci;
	}

	public Lijek getLijekAtRow(int rowIndex) {
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
		Lijek red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getIdLijeka();
		else if (columnIndex == 1)
			return red.getNazivLijeka();
		else if (columnIndex == 2)
			return red.getTipLijeka();
		else if (columnIndex == 3)
			return red.getCijenaLijeka();
		else
			return null;
	}
}