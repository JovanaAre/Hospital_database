package org.unibl.etf.bp.hospitalis.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.bp.hospitalis.entity.Doktor;

@SuppressWarnings("serial")
public class DoktorTableModel extends AbstractTableModel{
	
	private List<Doktor> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime", "Plata", "Specijalizacija"};

	public DoktorTableModel(List<Doktor> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Doktor> podaci) {
		this.podaci = podaci;
	}

	public Doktor getDoktorAtRow(int rowIndex) {
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
		Doktor red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getZaposleni().getJmb();
		else if (columnIndex == 1)
			return red.getZaposleni().getIme();
		else if (columnIndex == 2)
			return red.getZaposleni().getPrezime();
		else if (columnIndex == 3)
			return red.getZaposleni().getPlata();
		else if (columnIndex == 4)
			return red.getSpecijalizacija();
		else
			return null;
	}
}
