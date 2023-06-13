package org.unibl.etf.bp.hospitalis.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Odjel implements Serializable {
	
	private int idOdjela;
	private String nazivOdjela;
	private String adresa;
	private Doktor doktor;
	private MedicinskiTehnicar medicinskiTehnicar;

	public Odjel() {
	}

	public Odjel(int idOdjela, String nazivOdjela, String adresa,
			Doktor doktor, MedicinskiTehnicar medicinskiTehnicar) {
		super();
		this.idOdjela = idOdjela;
		this.nazivOdjela = nazivOdjela;
		this.adresa = adresa;
		this.doktor = doktor;
		this.medicinskiTehnicar = medicinskiTehnicar;
	}

	public int getIdOdjela() {
		return idOdjela;
	}

	public void setIdOdjela(int idOdjela) {
		this.idOdjela = idOdjela;
	}

	public String getNazivOdjela() {
		return nazivOdjela;
	}

	public void setNazivPredmeta(String nazivOdjela) {
		this.nazivOdjela = nazivOdjela;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}
	
	public MedicinskiTehnicar getMedicinskiTehnicar() {
		return medicinskiTehnicar;
	}

	public void setMedicinskiTehnicar(MedicinskiTehnicar medicinskiTehnicar) {
		this.medicinskiTehnicar = medicinskiTehnicar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idOdjela;
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
		Odjel other = (Odjel) obj;
		if (idOdjela != other.idOdjela)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nazivOdjela;
	}	
}