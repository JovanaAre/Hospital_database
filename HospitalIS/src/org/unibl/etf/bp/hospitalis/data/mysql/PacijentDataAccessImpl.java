package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.PacijentDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;

public class PacijentDataAccessImpl implements PacijentDataAccess{
	
	@Override
	public List<Pacijent> pacijenti(String jmbPacijenta) {
		List<Pacijent> retVal = new ArrayList<Pacijent>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, " 
				+ "zo.Adresa, zo.Telefon "
				+ "from pacijent p "
				+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
				+ "where p.JMBPacijenta like ? "
				+ "order by p.Prezime asc; ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(jmbPacijenta));
			rs = ps.executeQuery();
			
			while (rs.next())
				retVal.add(new Pacijent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), new ZdravstvenoOsiguranje(rs.getString(9), rs.getString(10), rs.getString(11)))); 
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
	public boolean dodajPacijenta(Pacijent pacijent) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO pacijent "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, pacijent.getJmbPacijenta());
			ps.setString(2, pacijent.getIme());
			ps.setString(3, pacijent.getPrezime());
			// java.util.Date utilDate = zaposleni.getDatumRodjenja();
			// java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(4,  pacijent.getDatumRodjenja());
			ps.setString(5, pacijent.getAdresa());
			ps.setString(6, pacijent.getTelefon());
			ps.setString(7, pacijent.getPol());
			ps.setString(8, pacijent.getKrvnaGrupa());
			ps.setString(9, pacijent.getZdravstvenoOsiguranje().getDavalacOsiguranja());

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
	public boolean azurirajPacijenta(Pacijent pacijent) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE pacijent SET "
				+ "Adresa=?, "
				+ "Telefon=?, "
				+ "KrvnaGrupa=?, "
				+ "DavalacOsiguranja=? "
				+ "WHERE JMBPacijenta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, pacijent.getAdresa());
			ps.setString(2, pacijent.getTelefon());
			ps.setString(3, pacijent.getKrvnaGrupa());
			ps.setString(4, pacijent.getZdravstvenoOsiguranje().getDavalacOsiguranja());
			ps.setString(5, pacijent.getJmbPacijenta());
			
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
	public boolean obrisiPacijenta(String jmbPacijenta) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM pacijent "
				+ "WHERE JMBPacijenta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbPacijenta);

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
