package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Prepisivanje implements Serializable {
	
	private Date datumPrepisivanja;
	private Doktor doktor;
	private Pacijent pacijent;
	private Lijek lijek;
	int kolicinaLijeka;

	public Prepisivanje() {
	}

	public Prepisivanje(Date datumPrepisivanja, Doktor doktor, Pacijent pacijent,
			Lijek lijek, int kolicinaLijeka) {
		super();
		this.datumPrepisivanja = datumPrepisivanja;
		this.doktor = doktor;
		this.pacijent = pacijent;
		this.lijek = lijek;
		this.kolicinaLijeka = kolicinaLijeka;
	}

	public Date getDatumPrepisivanja() {
		return datumPrepisivanja;
	}

	public void setDatumPrepisivanja(Date datumPrepisivanja) {
		this.datumPrepisivanja = datumPrepisivanja;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}
	
	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
	public Lijek getLijek() {
		return lijek;
	}

	public void setLijek(Lijek lijek) {
		this.lijek = lijek;
	}
	
	public int getKolicinaLijeka() {
		return kolicinaLijeka;
	}

	public void setKolicinaLijeka(int kolicinaLijeka) {
		this.kolicinaLijeka = kolicinaLijeka;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doktor == null) ? 0 : doktor.hashCode());
		result = prime
				* result
				+ ((pacijent == null) ? 0 : pacijent.hashCode());
		result = prime * result + ((lijek == null) ? 0 : lijek.hashCode());
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
		Prepisivanje other = (Prepisivanje) obj;
		if (datumPrepisivanja == null) {
			if (other.datumPrepisivanja != null)
				return false;
		} else if (!datumPrepisivanja.equals(other.datumPrepisivanja))
			return false;
		if (doktor == null) {
			if (other.doktor != null)
				return false;
		} else if (!doktor.equals(other.doktor))
			return false;
		if (pacijent == null) {
			if (other.pacijent != null)
				return false;
		} else if (!pacijent.equals(other.pacijent))
			return false;
		if (lijek == null) {
			if (other.lijek != null)
				return false;
		} else if (!lijek.equals(other.lijek))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "JMBD:" + doktor.getZaposleni().getJmb() + ", " + "JMBP:" + pacijent.getJmbPacijenta() 
		+ ", " +"Id lijeka:" + lijek.getIdLijeka(); 
	}
		
}
