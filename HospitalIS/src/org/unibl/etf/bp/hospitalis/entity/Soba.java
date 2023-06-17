package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Soba {
	
	private int brojSobe;
	private double cijenaSobe;
	private int brojKreveta;

	public Soba() {
	}

	public Soba(int brojSobe, double cijenaSobe, int brojKreveta) {
		super();
		this.brojSobe = brojSobe;
		this.cijenaSobe = cijenaSobe;
		this.brojKreveta = brojKreveta;
	}
	
	public int getBrojSobe() {
		return brojSobe;
	}

	public void setBrojSobe(int brojSobe) {
		this.brojSobe = brojSobe;
	}
	
	public double getCijenaSobe() {
		return cijenaSobe;
	}

	public void setCijenaSobe(double cijenaSobe) {
		this.cijenaSobe = cijenaSobe;
	}
	
	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brojSobe;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Soba other = (Soba) obj;
		if (brojSobe != other.brojSobe)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Broj sobe:" + brojSobe + ", " + "Broj kreveta:" + brojKreveta;
	}
}
