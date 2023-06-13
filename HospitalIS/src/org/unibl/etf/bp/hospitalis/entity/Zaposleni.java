package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Zaposleni  implements Serializable{
	private String jmb;
	private String ime;
	private String prezime;
	private String email;
	private Date datumRodjenja;
	private String adresa;
	private double plata;
	private String pol;
	private String telefon;

	public Zaposleni(String jmb, String ime, String prezime, String email, Date date, String adresa, Double plata,
			String pol, String telefon) {
		super();
		this.jmb = jmb;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = (Date) date;
		this.adresa = adresa;
		this.plata = plata;
		this.pol = pol;
		this.telefon = telefon;
	}
	
	public String getJmb() {
		return jmb;
	}

	public void setJmb(String jmb) {
		this.jmb = jmb;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja= datumRodjenja;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((jmb == null) ? 0 : jmb.hashCode());
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
		Zaposleni other = (Zaposleni) obj;
		if (jmb == null) {
			if (other.jmb != null)
				return false;
		} else if (!jmb.equals(other.jmb))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:"+jmb + "    " + ime + " " + prezime;
	}
}