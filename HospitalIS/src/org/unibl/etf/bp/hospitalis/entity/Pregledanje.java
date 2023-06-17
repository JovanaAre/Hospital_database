package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Pregledanje implements Serializable {
	
	private Date datumPregleda;
	private Doktor doktor;
	private Pacijent pacijent;
	private DijagnostickiPregled dijagnostickiPregled;
	String dijagnoza;
	String misljenje;

	public Pregledanje() {
	}

	public Pregledanje(Date datumPregleda, Doktor doktor, Pacijent pacijent,
			DijagnostickiPregled dijagnostickiPregled, String dijagnoza, String misljenje) {
		super();
		this.datumPregleda = datumPregleda;
		this.doktor = doktor;
		this.pacijent = pacijent;
		this.dijagnostickiPregled = dijagnostickiPregled;
		this.dijagnoza = dijagnoza;
		this.misljenje=misljenje;
	}

	public Date getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(Date datumPregleda) {
		this.datumPregleda = datumPregleda;
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
	public DijagnostickiPregled getDijagnostickiPregled() {
		return dijagnostickiPregled;
	}

	public void setDijagnostickiPregled(DijagnostickiPregled dijagnostickiPregled) {
		this.dijagnostickiPregled = dijagnostickiPregled;
	}
	
	public String getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(String dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	public String getMisljenje() {
		return misljenje;
	}

	public void setMisljenje(String misljenje) {
		this.misljenje = misljenje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doktor == null) ? 0 : doktor.hashCode());
		result = prime
				* result
				+ ((pacijent == null) ? 0 : pacijent.hashCode());
		result = prime * result + ((dijagnostickiPregled == null) ? 0 : dijagnostickiPregled.hashCode());
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
		Pregledanje other = (Pregledanje) obj;
		if (datumPregleda == null) {
			if (other.datumPregleda != null)
				return false;
		} else if (!datumPregleda.equals(other.datumPregleda))
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
		if (dijagnostickiPregled == null) {
			if (other.dijagnostickiPregled != null)
				return false;
		} else if (!dijagnostickiPregled.equals(other.dijagnostickiPregled))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "JMBD:" + doktor.getZaposleni().getJmb() + ", " + "JMBP:" + pacijent.getJmbPacijenta() 
		+ ", " +"Id pregleda:" + dijagnostickiPregled.getIdPregleda(); 
	}
		
}
