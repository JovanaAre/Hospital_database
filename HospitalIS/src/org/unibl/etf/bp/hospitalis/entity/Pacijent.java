package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Pacijent  implements Serializable{
	private String jmbPacijenta;
	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private String adresa;
	private String telefon;
	private String pol;
	private String krvnaGrupa;
	private ZdravstvenoOsiguranje osiguranje;

	public Pacijent(String jmbPacijenta, String ime, String prezime, Date datumRodjenja, String adresa, String telefon,
			String pol, String krvnaGrupa, ZdravstvenoOsiguranje osiguranje) {
		super();
		this.jmbPacijenta = jmbPacijenta;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = (Date) datumRodjenja;
		this.adresa = adresa;
		this.telefon = telefon;
		this.pol = pol;
		this.krvnaGrupa = krvnaGrupa;
		this.osiguranje = osiguranje;
	}
	
	public String getJmbPacijenta() {
		return jmbPacijenta;
	}

	public void setJmbPacijenta(String jmbPacijenta) {
		this.jmbPacijenta = jmbPacijenta;
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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public String getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(String krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}
	
	public ZdravstvenoOsiguranje getZdravstvenoOsiguranje() {
		return osiguranje;
	}

	public void setZdravstvenoOsiguranje(ZdravstvenoOsiguranje osiguranje) {
		this.osiguranje = osiguranje;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jmbPacijenta == null) ? 0 : jmbPacijenta.hashCode());
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
		Pacijent other = (Pacijent) obj;
		if (jmbPacijenta == null) {
			if (other.jmbPacijenta != null)
				return false;
		} else if (!jmbPacijenta.equals(other.jmbPacijenta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:"+ jmbPacijenta + "    " + ime + " " + prezime;
	}
}
