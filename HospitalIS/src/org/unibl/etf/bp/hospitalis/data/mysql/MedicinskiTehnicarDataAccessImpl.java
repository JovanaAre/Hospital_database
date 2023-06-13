package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.MedicinskiTehnicarDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import java.sql.Date;

public class MedicinskiTehnicarDataAccessImpl implements MedicinskiTehnicarDataAccess{
	
	@Override
	public List<MedicinskiTehnicar> sviMedicinskiTehnicari(){
		
	List<MedicinskiTehnicar> retVal=new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
		
	String query = "SELECT Z.JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon, StrucnaSprema "
				+ "FROM med_sestra_tehnicar MST "
				+ "INNER JOIN zaposleni Z ON Z.JMB=MST.JMB "
				+ "ORDER BY Prezime ASC; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new MedicinskiTehnicar(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
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
	public List<MedicinskiTehnicar> medicinskiTehnicari(String jmbZaposlenog){
		List<MedicinskiTehnicar> retVal = new ArrayList<MedicinskiTehnicar>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT Z.JMB, Ime, Prezime, Email, DatumRodjenja, Adresa, Plata, Pol, Telefon, StrucnaSprema "
				+ "FROM med_sestra_tehnicar MST "
				+ "INNER JOIN zaposleni Z ON Z.JMB=MST.JMB "
				+ "WHERE MST.JMB LIKE ? "
				+ "ORDER BY Prezime ASC; ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new MedicinskiTehnicar(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
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
	public boolean dodajMedicinskogTehnicara(MedicinskiTehnicar medicinskiTehnicar){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO med_sestra_tehnicar VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, medicinskiTehnicar.getZaposleni().getJmb());
			ps.setString(2, medicinskiTehnicar.getStrucnaSprema());
			

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
	public boolean azurirajMedicinskogTehnicara(MedicinskiTehnicar medicinskiTehnicar){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE med_sestra_tehnicar SET "
				+ "StrucnaSprema=? "
				+ "WHERE JMB=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, medicinskiTehnicar.getStrucnaSprema());
			ps.setString(2, medicinskiTehnicar.getZaposleni().getJmb());
			
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
	public boolean obrisiMedicinskogTehnicara(String jmbZaposlenog){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM med_sestra_tehnicar "
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
