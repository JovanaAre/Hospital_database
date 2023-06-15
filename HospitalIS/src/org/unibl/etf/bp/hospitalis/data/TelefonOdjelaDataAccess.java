package org.unibl.etf.bp.hospitalis.data;

import java.util.List;

import org.unibl.etf.bp.hospitalis.entity.TelefonOdjela;

public interface TelefonOdjelaDataAccess {
	
	 List<TelefonOdjela> sviTelefoniOdjela();
	 List<TelefonOdjela> telefoniOdjela(int idOdjela);
	 boolean dodajTelefonOdjela(TelefonOdjela telefonOdjela);
	 boolean azurirajTelefonOdjela(TelefonOdjela telefonOdjela);
	 boolean obrisiTelefonOdjela(int idOdjela, String telefon);
}