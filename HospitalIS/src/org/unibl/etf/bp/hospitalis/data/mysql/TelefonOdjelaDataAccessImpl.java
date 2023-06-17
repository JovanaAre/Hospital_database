package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.unibl.etf.bp.hospitalis.data.TelefonOdjelaDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.entity.TelefonOdjela;

public class TelefonOdjelaDataAccessImpl implements TelefonOdjelaDataAccess {

	@Override
	public List<TelefonOdjela> sviTelefoniOdjela() {
		
		List<TelefonOdjela> retVal = new ArrayList<TelefonOdjela>();
		Connection conn = null;
		//PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		/*String query = "select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, "
				+ "za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema, t.Telefon "
				+ "from odjel o "
				+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
				+ "inner join zaposleni z on z.JMB=d.JMB "
				+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
				+ "inner join zaposleni za on za.JMB=mst.JMB "
				+ "inner join telefon_odjela t on t.IdOdjela=o.IdOdjela "
				+ "order by o.IdOdjela asc; ";*/
		
		String query = "SELECT * FROM telefoni_odjela";
			
			try {
				conn = ConnectionPool.getInstance().checkOut();
				 //ps = conn.prepareStatement(query);
				cs = conn.prepareCall(query);
				rs = cs.executeQuery();
				
				while (rs.next())
					retVal.add(new TelefonOdjela(new Odjel(rs.getInt(1), rs.getString(2), rs.getString(3), new Doktor(new Zaposleni(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
					rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12)), rs.getString(13)),new MedicinskiTehnicar(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
					rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23))),rs.getString(24)));

			} catch (SQLException e) {
				e.printStackTrace();
				MySQLUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				MySQLUtilities.getInstance().close(cs, rs);
			}
			return retVal;
	}
	
	@Override
	public List<TelefonOdjela> telefoniOdjela(int idOdjela) {
		List<TelefonOdjela> retVal = new ArrayList<TelefonOdjela>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, "
				+ "za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema, t.Telefon "
				+ "from odjel o "
				+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
				+ "inner join zaposleni z on z.JMB=d.JMB "
				+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
				+ "inner join zaposleni za on za.JMB=mst.JMB "
				+ "inner join telefon_odjela t on t.IdOdjela=o.IdOdjela "
				+ "where o.IdOdjela like ? "
				+ "order by o.IdOdjela desc; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOdjela);;
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new TelefonOdjela(new Odjel(rs.getInt(1), rs.getString(2), rs.getString(3), new Doktor(new Zaposleni(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
				rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12)), rs.getString(13)),new MedicinskiTehnicar(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
				rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23))),rs.getString(24)));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}

	@Override
	public boolean dodajTelefonOdjela(TelefonOdjela telefonOdjela) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO telefon_odjela VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, telefonOdjela.getOdjel().getIdOdjela());
			ps.setString(2, telefonOdjela.getTelefon());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	@Override
	public boolean azurirajTelefonOdjela(TelefonOdjela telefonOdjela) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE telefon_odjela SET "
				+ "Telefon=? "
				+ "WHERE IdOdjela=? and Telefon=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, telefonOdjela.getTelefon());
			ps.setInt(2, telefonOdjela.getOdjel().getIdOdjela());
			ps.setString(3, telefonOdjela.getTelefon());

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}

	@Override
	public boolean obrisiTelefonOdjela(int idOdjela, String telefon) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM telefon_odjela "
				+ "WHERE IdOdjela=? AND Telefon=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOdjela);
			ps.setString(2, telefon);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
}
