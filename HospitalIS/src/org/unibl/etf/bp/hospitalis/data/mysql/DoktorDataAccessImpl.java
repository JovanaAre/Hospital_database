package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.DoktorDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import java.sql.Date;

public class DoktorDataAccessImpl implements DoktorDataAccess{
	
	@Override
	public List<Doktor> sviDoktori(){
		
	List<Doktor> retVal=new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
		
	String query = "SELECT Z.JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon, Specijalizacija "
				+ "FROM doktor D "
				+ "INNER JOIN zaposleni Z ON Z.JMB=D.JMB "
				+ "ORDER BY Prezime ASC; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Doktor(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9)),rs.getString(10)));
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
	public List<Doktor> doktori(String jmbZaposlenog){
		List<Doktor> retVal = new ArrayList<Doktor>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT Z.JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon, Specijalizacija "
				+ "FROM doktor D "
				+ "INNER JOIN zaposleni Z ON Z.JMB=D.JMB "
				+ "WHERE D.JMB LIKE ? "
				+ "ORDER BY Prezime ASC; ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);
			rs = ps.executeQuery();

			while (rs.next())
			{
				retVal.add(new Doktor(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9)),rs.getString(10)));
			}
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
	public boolean dodajDoktora(Doktor doktor){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO doktor VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, doktor.getZaposleni().getJmb());
			ps.setString(2, doktor.getSpecijalizacija());
			

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
	public boolean azurirajDoktora(Doktor doktor){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE doktor SET "
				+ "Specijalizacija=? "
				+ "WHERE JMB=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, doktor.getSpecijalizacija());
			ps.setString(2, doktor.getZaposleni().getJmb());
			
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
	public boolean obrisiDoktora(String jmbZaposlenog){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM doktor "
				+ "WHERE JMB=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);

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