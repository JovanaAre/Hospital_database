package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class PacijentZadrzan implements Serializable {
	
	private Pacijent pacijent;
	Date datumPrijema;
	Date datumOtpustanja;
	private Soba soba;
	private KontaktOsoba kontaktOsoba;

	public PacijentZadrzan() {
	}

	public PacijentZadrzan(Pacijent pacijent, Date datumPrijema, Date datumOtpustanja,
			Soba soba, KontaktOsoba kontaktOsoba) {
		super();
		this.pacijent = pacijent;
		this.datumPrijema = datumPrijema;
		this.datumOtpustanja = datumOtpustanja;
		this.soba = soba;
		this.kontaktOsoba = kontaktOsoba;
	}
	
	public Date getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		//datumPrijema = new Date(System.currentTimeMillis());
		this.datumPrijema = datumPrijema;
	}
	
	public Date getDatumOtpustanja() {
		return datumOtpustanja;
	}

	public void setDatumOtpustanja(Date datumOtpustanja) {
		//datumOtpustanja = new Date(System.currentTimeMillis());
		this.datumOtpustanja = datumOtpustanja;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}
	
	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	
	public KontaktOsoba getKontaktOsoba() {
		return kontaktOsoba;
	}
	public void setKontaktOsoba(KontaktOsoba kontaktOsoba) {
		this.kontaktOsoba = kontaktOsoba;
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
		PacijentZadrzan other = (PacijentZadrzan) obj;
		if (pacijent == null) {
			if (other.pacijent != null)
				return false;
		} else if (!pacijent.equals(other.pacijent))
			return false;
		if (datumPrijema == null) {
			if (other.datumPrijema != null)
				return false;
		} else if (!datumPrijema.equals(other.datumPrijema))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JMB:" + pacijent.getJmbPacijenta() + "    "+ pacijent.getIme()+" "+pacijent.getPrezime();
	}
	
}
