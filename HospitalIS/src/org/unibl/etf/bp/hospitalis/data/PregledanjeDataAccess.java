package org.unibl.etf.bp.hospitalis.data;

import java.util.List;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.entity.Pregledanje;

public interface PregledanjeDataAccess {
	
	 List<Pregledanje> svaPregledanja();
	 // List<Pregledanje> pregledanja(String);
	 boolean dodajPregledanje(Pregledanje pregledanje);
	 boolean azurirajPregledanje(Pregledanje pregledanje);
	 boolean obrisiPregledanje(Date datumPregleda, String jmbDoktora, String jmbPacijenta, int idPregleda);	
}

