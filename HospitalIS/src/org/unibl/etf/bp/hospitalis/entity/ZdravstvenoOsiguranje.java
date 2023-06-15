package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZdravstvenoOsiguranje {
	
	private String davalacOsiguranja;
	private String adresa;
	private String telefon;

	public ZdravstvenoOsiguranje() {
	}

	public ZdravstvenoOsiguranje(String davalacOsiguranja, String adresa, String telefon) {
		super();
		this.davalacOsiguranja = davalacOsiguranja;
		this.adresa = adresa;
		this.telefon = telefon;
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
		result = prime * result
				+ ((davalacOsiguranja == null) ? 0 : davalacOsiguranja.hashCode());
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
		if (davalacOsiguranja == null) {
			if (other.davalacOsiguranja != null)
				return false;
		} else if (!davalacOsiguranja.equals(other.davalacOsiguranja))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return davalacOsiguranja;
	}
}