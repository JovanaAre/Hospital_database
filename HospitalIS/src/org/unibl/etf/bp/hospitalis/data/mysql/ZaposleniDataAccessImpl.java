package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.ZaposleniDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;

public class ZaposleniDataAccessImpl implements ZaposleniDataAccess{
	
	@Override
	public Zaposleni zaposlen(String jmbZaposlenog) {
		Zaposleni retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon " + 
				"FROM zaposleni " + 
				"WHERE JMB=? " + 
				"ORDER BY Prezime ASC; ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9));
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
	public List<Zaposleni> zaposleni(String jmb) {
		List<Zaposleni> retVal = new ArrayList<Zaposleni>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon " + 
				"FROM zaposleni " + 
				"WHERE JMB LIKE ? " + 
				"ORDER BY Prezime ASC;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(jmb));
			rs = ps.executeQuery();
			
			while (rs.next())
				retVal.add(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9))); 
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
	public boolean dodajZaposlenog(Zaposleni zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO zaposleni (JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon)"
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, zaposleni.getJmb());
			ps.setString(2, zaposleni.getIme());
			ps.setString(3, zaposleni.getPrezime());
			ps.setString(4, zaposleni.getEmail());
			// java.util.Date utilDate = zaposleni.getDatumRodjenja();
			// java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(5,  zaposleni.getDatumRodjenja());
			ps.setString(6, zaposleni.getAdresa());
			ps.setDouble(7, zaposleni.getPlata());
			ps.setString(8, zaposleni.getPol());
			ps.setString(9, zaposleni.getTelefon());

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
	public boolean azurirajZaposlenog(Zaposleni zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE zaposleni SET "
				+ "Email=?, "
				+ "Adresa=?, "
				+ "Plata=?, "
				+ "Telefon=? "
				+ "WHERE JMB=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, zaposleni.getEmail());
			ps.setString(2, zaposleni.getAdresa());
			ps.setDouble(3, zaposleni.getPlata());
			ps.setString(4, zaposleni.getTelefon());
			ps.setString(5, zaposleni.getJmb());
			
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
	public boolean obrisiZaposlenog(String jmb) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM zaposleni "
				+ "WHERE JMB=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmb);

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