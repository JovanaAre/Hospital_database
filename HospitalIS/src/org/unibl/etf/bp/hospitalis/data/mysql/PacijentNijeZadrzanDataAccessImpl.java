package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.PacijentNijeZadrzanDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.PacijentNijeZadrzan;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;

import java.sql.Date;

public class PacijentNijeZadrzanDataAccessImpl implements PacijentNijeZadrzanDataAccess{
	
	@Override
	public List<PacijentNijeZadrzan> sviNezadrzaniPacijenti(){
		
	List<PacijentNijeZadrzan> retVal=new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
		
	String query = "select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, "
				+ "zo.Adresa, zo.Telefon, pnz.DatumDolaska "
				+ "from pacijent p "
				+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
				+ "inner join pacijent_nije_zadrzan pnz on pnz.JMBPacijenta=p.JMBPacijenta "
				+ "order by p.Prezime asc; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new PacijentNijeZadrzan(new Pacijent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), new ZdravstvenoOsiguranje(rs.getString(9), rs.getString(10), rs.getString(11))), rs.getDate(12)));
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
	public List<PacijentNijeZadrzan> nezadrzaniPacijenti(String jmbPacijenta){
		List<PacijentNijeZadrzan> retVal = new ArrayList<PacijentNijeZadrzan>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, "
				+ "zo.Adresa, zo.Telefon, pnz.DatumDolaska "
				+ "from pacijent p "
				+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
				+ "inner join pacijent_nije_zadrzan pnz on pnz.JMBPacijenta=p.JMBPacijenta "
				+ "where pnz.JMBPacijenta like ? "
				+ "order by p.Prezime asc; ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbPacijenta);
			rs = ps.executeQuery();
			//System.out.println(jmbZaposlenog);

			while (rs.next())
				retVal.add(new PacijentNijeZadrzan(new Pacijent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), new ZdravstvenoOsiguranje(rs.getString(9), rs.getString(10), rs.getString(11))), rs.getDate(12)));
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
	public boolean dodajNezadrzanogPacijenta(PacijentNijeZadrzan pacijentNijeZadrzan){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO pacijent_nije_zadrzan (JMBPacijenta, DatumDolaska) VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, pacijentNijeZadrzan.getPacijent().getJmbPacijenta());
			ps.setDate(2,  pacijentNijeZadrzan.getDatumDolaska());

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
	public boolean azurirajNezadrzanogPacijenta(PacijentNijeZadrzan pacijentNijeZadrzan){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE pacijent_nije_zadrzan SET "
				+ "DatumDolaska=? "
				+ "WHERE JMBPacijenta=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, pacijentNijeZadrzan.getDatumDolaska());
			ps.setString(2, pacijentNijeZadrzan.getPacijent().getJmbPacijenta());
			
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
	public boolean obrisiNezadrzanogPacijenta(String jmbPacijenta){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM pacijent_nije_zadrzan "
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
