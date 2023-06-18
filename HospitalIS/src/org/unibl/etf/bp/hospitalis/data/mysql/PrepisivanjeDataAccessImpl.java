package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.data.PrepisivanjeDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Prepisivanje;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
import org.unibl.etf.bp.hospitalis.entity.Lijek;

public class PrepisivanjeDataAccessImpl implements PrepisivanjeDataAccess {

	@Override
	public List<Prepisivanje> svaPrepisivanja(){
		
	List<Prepisivanje> retVal=new ArrayList<>();
	Connection conn = null;
	CallableStatement cs = null;
	// PreparedStatement ps = null;
	ResultSet rs = null;
		
	/*String query = "select pr.DatumPrepisivanja, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, "
			+ "d.Specijalizacija, p.JMBPacijenta, p.Ime, p.Prezime, p.DatumRodjenja, p.Adresa, p.Telefon, p.Pol, p.KrvnaGrupa, zo.DavalacOsiguranja, "
			+ "zo.Adresa, zo.Telefon, l.IdLijeka, l.NazivLijeka, l.TipLijeka, l.CijenaLijeka, pr.KolicinaLijeka "
			+ "from prepisivanje pr "
			+ "inner join doktor d on d.JMB=pr.JMBDoktora "
			+ "inner join zaposleni z on z.JMB=d.JMB "
			+ "inner join pacijent p on p.JMBPacijenta=pr.JMBPacijenta "
			+ "inner join zdravstveno_osiguranje zo on zo.DavalacOsiguranja=p.DavalacOsiguranja "
			+ "inner join lijek l on l.IdLijeka=pr.IdLijeka "
			+ "order by pr.DatumPrepisivanja asc; ";*/
	
	String query = "SELECT * FROM prepisivanja_lijekova";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			// ps = conn.prepareStatement(query);
			cs = conn.prepareCall(query);
			// rs = ps.executeQuery();
			rs = cs.executeQuery();
			
			while (rs.next())
				retVal.add(new Prepisivanje(rs.getDate(1), new Doktor(new Zaposleni(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6),
						rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getString(10)),rs.getString(11)), new Pacijent(rs.getString(12), rs.getString(13), rs.getString(14), rs.getDate(15), rs.getString(16), rs.getString(17),
								rs.getString(18), rs.getString(19), new ZdravstvenoOsiguranje(rs.getString(20), rs.getString(21), rs.getString(22))), new Lijek(rs.getInt(23), rs.getString(24),
										rs.getString(25),
										rs.getDouble(26)),rs.getInt(27)));

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
	public boolean dodajPrepisivanje(Prepisivanje prepisivanje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO prepisivanje VALUES "
				+ "(?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, prepisivanje.getDatumPrepisivanja());
			ps.setString(2, prepisivanje.getDoktor().getZaposleni().getJmb());
			ps.setString(3, prepisivanje.getPacijent().getJmbPacijenta());
			ps.setInt(4, prepisivanje.getLijek().getIdLijeka());
			ps.setInt(5, prepisivanje.getKolicinaLijeka());

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
	public boolean azurirajPrepisivanje(Prepisivanje prepisivanje) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE prepisivanje SET "
				+ "KolicinaLijeka=? "
				+ "WHERE DatumPrepisivanja=? AND JMBDoktora=? AND JMBPacijenta=? AND IdLijeka=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, prepisivanje.getKolicinaLijeka());
			ps.setDate(2, prepisivanje.getDatumPrepisivanja());
			ps.setString(3, prepisivanje.getDoktor().getZaposleni().getJmb());
			ps.setString(4, prepisivanje.getPacijent().getJmbPacijenta());
			ps.setInt(5, prepisivanje.getLijek().getIdLijeka());

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
	public boolean obrisiPrepisivanje(Date datumPrepisivanja, String jmbDoktora, String jmbPacijenta, int idLijeka) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM prepisivanje "
				+ "WHERE DatumPrepisivanja=? AND JMBDoktora=? AND JMBPacijenta=? AND IdLijeka=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, datumPrepisivanja);
			ps.setString(2, jmbDoktora);
			ps.setString(3, jmbPacijenta);
			ps.setInt(4, idLijeka);

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

