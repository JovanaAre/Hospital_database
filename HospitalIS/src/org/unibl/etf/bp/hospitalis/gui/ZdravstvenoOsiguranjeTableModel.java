package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;

@SuppressWarnings("serial")
public class ZdravstvenoOsiguranjeTableModel extends AbstractTableModel {
	
	List<ZdravstvenoOsiguranje> podaci;
	String[] kolone = new String[] { "Identifikator", "Davalac osiguranja", "Adresa", "Telefon"};

	public ZdravstvenoOsiguranjeTableModel(List<ZdravstvenoOsiguranje> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<ZdravstvenoOsiguranje> podaci) {
		this.podaci = podaci;
	}

	public ZdravstvenoOsiguranje getOsiguranjeAtRow(int rowIndex) {
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
		ZdravstvenoOsiguranje red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getIdOsiguranja();
		else if (columnIndex == 1)
			return red.getDavalacOsiguranja();
		else if (columnIndex == 2)
			return red.getAdresa();
		else if (columnIndex == 3)
			return red.getTelefon();
		else
			return null;
	}
}