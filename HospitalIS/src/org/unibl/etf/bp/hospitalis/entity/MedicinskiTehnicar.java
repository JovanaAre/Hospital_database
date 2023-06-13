package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MedicinskiTehnicar implements Serializable {
	
	private String strucnaSprema;
	private Zaposleni zaposleni;

	public MedicinskiTehnicar() {
	}

	public MedicinskiTehnicar(Zaposleni zaposleni, String strucnaSprema) {
		super();
		this.zaposleni = zaposleni;
		this.strucnaSprema = strucnaSprema;
	}

	public String getStrucnaSprema() {
		return strucnaSprema;
	}

	public void setStrucnaSprema(String strucnaSprema) {
		this.strucnaSprema = strucnaSprema;
	}

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zaposleni == null) ? 0 : zaposleni.hashCode());
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
		MedicinskiTehnicar other = (MedicinskiTehnicar) obj;
		if (zaposleni == null) {
			if (other.zaposleni != null)
				return false;
		} else if (!zaposleni.equals(other.zaposleni))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "JMB:" + zaposleni.getJmb() + "    "+ zaposleni.getIme()+" "+zaposleni.getPrezime()
		+ ", " + strucnaSprema;
	}
	
}