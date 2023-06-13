package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZdravstvenoOsiguranje {
	
	private int idOsiguranja;
	private String davalacOsiguranja;
	private String adresa;
	private String telefon;

	public ZdravstvenoOsiguranje() {
	}

	public ZdravstvenoOsiguranje(int idOsiguranja, String davalacOsiguranja, String adresa, String telefon) {
		super();
		this.idOsiguranja = idOsiguranja;
		this.davalacOsiguranja = davalacOsiguranja;
		this.adresa = adresa;
		this.telefon = telefon;
	}
	
	public int getIdOsiguranja() {
		return idOsiguranja;
	}

	public void setIdOsiguranja(int idOsiguranja) {
		this.idOsiguranja = idOsiguranja;
	}
	
	public String getDavalacOsiguranja() {
		return davalacOsiguranja;
	}
	
	public void setDavalacOsiguranja(String davalacOsiguranja) {
		this.davalacOsiguranja = davalacOsiguranja;
	}
	
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOsiguranja;
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
		ZdravstvenoOsiguranje other = (ZdravstvenoOsiguranje) obj;
		if (idOsiguranja != other.idOsiguranja)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return davalacOsiguranja;
	}
}