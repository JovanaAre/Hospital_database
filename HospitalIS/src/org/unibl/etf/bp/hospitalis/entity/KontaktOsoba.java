package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class KontaktOsoba {
	
	private String jmbKontaktOsobe;
	private String ime;
	private String prezime;
	private String telefon;

	public KontaktOsoba() {
	}

	public KontaktOsoba(String jmbKontaktOsobe, String ime, String prezime, String telefon) {
		super();
		this.jmbKontaktOsobe = jmbKontaktOsobe;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}
	
	public String getJmbKontaktOsobe() {
		return jmbKontaktOsobe;
	}

	public void setJmbKontaktOsobe(String jmbKontaktOsobe) {
		this.jmbKontaktOsobe = jmbKontaktOsobe;
	}
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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
				+ ((jmbKontaktOsobe == null) ? 0 : jmbKontaktOsobe.hashCode());
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
		KontaktOsoba other = (KontaktOsoba) obj;
		if (jmbKontaktOsobe == null) {
			if (other.jmbKontaktOsobe != null)
				return false;
		} else if (!jmbKontaktOsobe.equals(other.jmbKontaktOsobe))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ime + " " + prezime +", " + telefon;
	}
}