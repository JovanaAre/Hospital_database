package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class ZaposleniNaOdjelima implements Serializable {
	
	private Date datumZaposlenja;
	private Zaposleni zaposleni;
	private Odjel odjel;

	public ZaposleniNaOdjelima() {
	}

	public ZaposleniNaOdjelima(Date datumZaposlenja, Zaposleni zaposleni, Odjel odjel) {
		super();
		this.datumZaposlenja = datumZaposlenja;
		this.zaposleni = zaposleni;
		this.odjel = odjel;
	}

	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	public Odjel getOdjel() {
		return odjel;
	}

	public void setOdjel(Odjel odjel) {
		this.odjel = odjel;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zaposleni == null) ? 0 : zaposleni.hashCode());
		result = prime
				* result
				+ ((odjel == null) ? 0 : odjel.hashCode());
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
		ZaposleniNaOdjelima other = (ZaposleniNaOdjelima ) obj;
		if (datumZaposlenja == null) {
			if (other.datumZaposlenja != null)
				return false;
		} else if (!datumZaposlenja.equals(other.datumZaposlenja))
			return false;
		if (zaposleni == null) {
			if (other.zaposleni != null)
				return false;
		} else if (!zaposleni.equals(other.zaposleni))
			return false;
		if (odjel == null) {
			if (other.odjel != null)
				return false;
		} else if (!odjel.equals(other.odjel))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return zaposleni + " (" + odjel + ")";
	}
		
}
