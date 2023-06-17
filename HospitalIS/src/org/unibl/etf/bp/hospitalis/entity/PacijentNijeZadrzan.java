package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class PacijentNijeZadrzan implements Serializable {
	
	private Pacijent pacijent;
	Date datumDolaska;

	public PacijentNijeZadrzan() {
	}

	public PacijentNijeZadrzan(Pacijent pacijent, Date datumDolaska) {
		super();
		this.pacijent = pacijent;
		this.datumDolaska = datumDolaska;
	}
	
	/*public PacijentNijeZadrzan(Pacijent pacijent) {
		super();
		this.pacijent = pacijent;
		this.datumDolaska = new Date(System.currentTimeMillis());
	}*/

	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		//datumDolaska = new Date(System.currentTimeMillis());
		this.datumDolaska = datumDolaska;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pacijent == null) ? 0 : pacijent.hashCode());
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
		PacijentNijeZadrzan other = (PacijentNijeZadrzan) obj;
		if (pacijent == null) {
			if (other.pacijent != null)
				return false;
		} else if (!pacijent.equals(other.pacijent))
			return false;
		if (datumDolaska == null) {
			if (other.datumDolaska != null)
				return false;
		} else if (!datumDolaska.equals(other.datumDolaska))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:" + pacijent.getJmbPacijenta() + "    "+ pacijent.getIme()+" "+pacijent.getPrezime();
	}
	
}
