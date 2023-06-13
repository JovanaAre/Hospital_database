package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;

@SuppressWarnings("serial")
public class MedicinskiTehnicarTableModel extends AbstractTableModel{
	
	private List<MedicinskiTehnicar> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "Plata", "Stručna sprema"};

	public MedicinskiTehnicarTableModel(List<MedicinskiTehnicar> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<MedicinskiTehnicar> podaci) {
		this.podaci = podaci;
	}

	public MedicinskiTehnicar getMedicinskiTehnicarAtRow(int rowIndex) {
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
		MedicinskiTehnicar red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getZaposleni().getJmb();
		else if (columnIndex == 1)
			return red.getZaposleni().getIme();
		else if (columnIndex == 2)
			return red.getZaposleni().getPrezime();
		else if (columnIndex == 3)
			return red.getZaposleni().getPlata();
		else if (columnIndex == 4)
			return red.getStrucnaSprema();
		else
			return null;
	}
}