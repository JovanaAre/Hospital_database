package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.PacijentZadrzanDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.PacijentZadrzan;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
import org.unibl.etf.bp.hospitalis.entity.Soba;
import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;

import java.sql.Date;

public class PacijentZadrzanDataAccessImpl implements PacijentZadrzanDataAccess{
	
	@Override
	public List<PacijentZadrzan> sviZadrzaniPacijenti(){
		
	List<PacijentZadrzan> retVal=new ArrayList<>();
	Connection conn = null;
	CallableStatement cs = null;
	// PreparedStatement ps = null;
	ResultSet rs = null;
		
	String query = "SELECT * FROM zadrzani_pacijenti";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			// ps = conn.prepareStatement(query);
			cs = conn.prepareCall(query);
			// rs = ps.executeQuery();
			rs = cs.executeQuery();

			while (rs.next())
				retVal.add(new PacijentZadrzan(new Pacijent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), new ZdravstvenoOsiguranje(rs.getString(9), rs.getString(10), rs.getString(11))), rs.getDate(12),
						rs.getDate(13),new Soba(rs.getInt(14), rs.getDouble(15), rs.getInt(16)), 
						new KontaktOsoba(rs.getString(17), rs.getString(18), rs
								.getString(19), rs.getString(20))));
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
	public List<PacijentZadrzan> zadrzaniPacijenti(String jmbPacijenta){
		List<PacijentZadrzan> retVal = new ArrayList<PacijentZadrzan>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, "
				+ "zo.Adresa, zo.Telefon, pz.DatumPrijema, pz.DatumOtpustanja, s.BrojSobe, s.CijenaSobe, s.BrojKreveta, "
				+ "ko.JMBKontaktOsobe, ko.Ime, ko.Prezime, ko.Telefon "
				+ "from pacijent p "
				+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
				+ "inner join pacijent_zadrzan pz on pz.JMBPacijenta=p.JMBPacijenta "
				+ "inner join soba s on s.BrojSobe=pz.BrojSobe "
				+ "inner join kontakt_osoba ko on ko.JMBKontaktOsobe=pz.JMBKontaktOsobe "
				+ "where pz.JMBPacijenta like ? "
				+ "order by p.Prezime asc; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbPacijenta);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new PacijentZadrzan(new Pacijent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), new ZdravstvenoOsiguranje(rs.getString(9), rs.getString(10), rs.getString(11))), rs.getDate(12),
						rs.getDate(13),new Soba(rs.getInt(14), rs.getDouble(15), rs.getInt(16)), 
						new KontaktOsoba(rs.getString(17), rs.getString(18), rs
								.getString(19), rs.getString(20))));
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
	public boolean dodajZadrzanogPacijenta(PacijentZadrzan pacijentZadrzan){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO pacijent_zadrzan VALUES "
				+ "(?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, pacijentZadrzan.getPacijent().getJmbPacijenta());
			ps.setDate(2,  pacijentZadrzan.getDatumPrijema());
			ps.setDate(3,  pacijentZadrzan.getDatumOtpustanja());
			ps.setInt(4,  pacijentZadrzan.getSoba().getBrojSobe());
			ps.setString(5, pacijentZadrzan.getKontaktOsoba().getJmbKontaktOsobe());

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
	public boolean azurirajZadrzanogPacijenta(PacijentZadrzan pacijentZadrzan){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		// modifikuje se samo Datum otpustanja zbog strukture trigera (nemogucnosti definisanja
		// azuriranja samo jedne kolone unutar trigera)
		String query = "UPDATE pacijent_zadrzan SET "
				+ "DatumOtpustanja=? "   
				+ "WHERE JMBPacijenta=? AND DatumPrijema=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, pacijentZadrzan.getDatumOtpustanja());
			ps.setString(2, pacijentZadrzan.getPacijent().getJmbPacijenta());
			ps.setDate(3, pacijentZadrzan.getDatumPrijema());
			
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
	public boolean obrisiZadrzanogPacijenta(String jmbPacijenta, Date datumPrijema){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM pacijent_zadrzan "
				+ "WHERE JMBPacijenta=? AND DatumPrijema=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbPacijenta);
			ps.setDate(2, datumPrijema);

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
