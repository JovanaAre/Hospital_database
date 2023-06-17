package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.data.PregledanjeDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Pregledanje;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;

public class PregledanjeDataAccessImpl implements PregledanjeDataAccess {

	@Override
	public List<Pregledanje> svaPregledanja(){
		
	List<Pregledanje> retVal=new ArrayList<>();
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
		
	String query = "select pr.DatumPregleda, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, "
			+ "d.Specijalizacija, p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, "
			+ "zo.Adresa, zo.Telefon, preg.IdPregleda, preg.NazivPregleda, preg.CijenaPregleda, pr.Dijagnoza, pr.Misljenje "
			+ "from pregledanje pr "
			+ "inner join doktor d on d.JMB=pr.JMBDoktora "
			+ "inner join zaposleni z on z.JMB=d.JMB "
			+ "inner join pacijent p on p.JMBPacijenta=pr.JMBPacijenta "
			+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
			+ "inner join dijagnosticki_pregled preg on preg.IdPregleda=pr.IdPregleda "
			+ "order by pr.DatumPregleda asc; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next())
				retVal.add(new Pregledanje(rs.getDate(1), new Doktor(new Zaposleni(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6),
						rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getString(10)),rs.getString(11)), new Pacijent(rs.getString(12), rs.getString(13), rs.getString(14), rs.getDate(15), rs.getString(16), rs.getString(17),
								rs.getString(18), rs.getString(19), new ZdravstvenoOsiguranje(rs.getString(20), rs.getString(21), rs.getString(22))), new DijagnostickiPregled(rs.getInt(23), rs.getString(24),
										rs.getDouble(25)),rs.getString(26), rs.getString(27)));

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
	public boolean dodajPregledanje(Pregledanje pregledanje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO pregledanje VALUES "
				+ "(?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, pregledanje.getDatumPregleda());
			ps.setString(2, pregledanje.getDoktor().getZaposleni().getJmb());
			ps.setString(3, pregledanje.getPacijent().getJmbPacijenta());
			ps.setInt(4, pregledanje.getDijagnostickiPregled().getIdPregleda());
			ps.setString(5, pregledanje.getDijagnoza());
			ps.setString(6, pregledanje.getMisljenje());

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
	public boolean azurirajPregledanje(Pregledanje pregledanje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE pregledanje SET "
				+ "Dijagnoza=?, "
				+ "Misljenje=? "
				+ "WHERE DatumPregleda=? AND JMBDoktora=? AND JMBPacijenta=? AND IdPregleda=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, pregledanje.getDijagnoza());
			ps.setString(2, pregledanje.getMisljenje());
			ps.setDate(3, pregledanje.getDatumPregleda());
			ps.setString(4, pregledanje.getDoktor().getZaposleni().getJmb());
			ps.setString(5, pregledanje.getPacijent().getJmbPacijenta());
			ps.setInt(6, pregledanje.getDijagnostickiPregled().getIdPregleda());

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
	public boolean obrisiPregledanje(Date datumPregleda, String jmbDoktora, String jmbPacijenta, int idPregleda) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM pregledanje "
				+ "WHERE DatumPregleda=? AND JMBDoktora=? AND JMBPacijenta=? AND IdPregleda=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, datumPregleda);
			ps.setString(2, jmbDoktora);
			ps.setString(3, jmbPacijenta);
			ps.setInt(4, idPregleda);

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
