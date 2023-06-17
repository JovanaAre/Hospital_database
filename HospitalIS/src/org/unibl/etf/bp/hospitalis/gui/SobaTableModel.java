package org.unibl.etf.bp.hospitalis.gui;


import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Soba;

@SuppressWarnings("serial")
public class SobaTableModel extends AbstractTableModel {
	
	List<Soba> podaci;
	String[] kolone = new String[] { "Broj sobe", "Cijena", "Broj kreveta"};

	public SobaTableModel(List<Soba> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Soba> podaci) {
		this.podaci = podaci;
	}

	public Soba getSobaAtRow(int rowIndex) {
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
		Soba red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getBrojSobe();
		else if (columnIndex == 1)
			return red.getCijenaSobe();
		else if (columnIndex == 2)
			return red.getBrojKreveta();
		else
			return null;
	}
}
