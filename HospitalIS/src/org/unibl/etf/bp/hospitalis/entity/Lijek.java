package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Lijek {
	
	private int idLijeka;
	private String nazivLijeka;
	private String tipLijeka;
	private double cijenaLijeka;

	public Lijek() {
	}

	public Lijek(int idLijeka, String nazivLijeka, String tipLijeka, double cijenaLijeka) {
		super();
		this.idLijeka = idLijeka;
		this.nazivLijeka = nazivLijeka;
		this.tipLijeka = tipLijeka;
		this.cijenaLijeka = cijenaLijeka;
	}
	
	public int getIdLijeka() {
		return idLijeka;
	}

	public void setIdLijeka(int idLijeka) {
		this.idLijeka = idLijeka;
	}
	
	public String getNazivLijeka() {
		return nazivLijeka;
	}
	
	public void setNazivLijeka(String nazivLijeka) {
		this.nazivLijeka = nazivLijeka;
	}
	
	public String getTipLijeka() {
		return tipLijeka;
	}

	public void setTipLijeka(String tipLijeka) {
		this.tipLijeka = tipLijeka;
	}

	public double getCijenaLijeka() {
		return cijenaLijeka;
	}

	public void setCijenaLijeka(double cijenaLijeka) {
		this.cijenaLijeka = cijenaLijeka;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLijeka;
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
		Lijek other = (Lijek) obj;
		if (idLijeka != other.idLijeka)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nazivLijeka;
	}
}
