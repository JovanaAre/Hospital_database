package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DijagnostickiPregled {
	
	private int idPregleda;
	private String nazivPregleda;
	private double cijenaPregleda;

	public DijagnostickiPregled() {
	}

	public DijagnostickiPregled(int idPregleda, String nazivPregleda, double cijenaPregleda) {
		super();
		this.idPregleda = idPregleda;
		this.nazivPregleda = nazivPregleda;
		this.cijenaPregleda = cijenaPregleda;
	}
	
	public int getIdPregleda() {
		return idPregleda;
	}

	public void setIdPregleda(int idPregleda) {
		this.idPregleda = idPregleda;
	}
	
	public String getNazivPregleda() {
		return nazivPregleda;
	}
	
	public void setNazivPregleda(String nazivPregleda) {
		this.nazivPregleda = nazivPregleda;
	}
	
	public double getCijenaPregleda() {
		return cijenaPregleda;
	}

	public void setCijenaPregleda(double cijenaPregleda) {
		this.cijenaPregleda = cijenaPregleda;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPregleda;
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
		DijagnostickiPregled other = (DijagnostickiPregled) obj;
		if (idPregleda != other.idPregleda)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nazivPregleda;
	}
}
