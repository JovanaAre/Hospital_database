package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TelefonOdjela implements Serializable {
	
	private Odjel odjel;
	private String telefon;

	public TelefonOdjela() {
	}

	public TelefonOdjela(Odjel odjel, String telefon) {
		super();
		this.odjel = odjel;
		this.telefon = telefon;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public Odjel getOdjel() {
		return odjel;
	}

	public void setOdjel(Odjel odjel) {
		this.odjel = odjel;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelefonOdjela other = (TelefonOdjela) obj;
		if (odjel == null) {
			if (other.odjel != null)
				return false;
		} else if (!odjel.equals(other.odjel))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getOdjel().getNazivOdjela() + ", " + telefon;
	}	
}