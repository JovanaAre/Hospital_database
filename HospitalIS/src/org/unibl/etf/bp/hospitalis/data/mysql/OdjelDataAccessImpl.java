package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.unibl.etf.bp.hospitalis.data.OdjelDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import org.unibl.etf.bp.hospitalis.entity.Odjel;

public class OdjelDataAccessImpl implements OdjelDataAccess {

	@Override
	public List<Odjel> sviOdjeli(){
		
	List<Odjel> retVal=new ArrayList<>();
	Connection conn = null;
	// PreparedStatement ps = null;
	CallableStatement cs = null;
	ResultSet rs = null;
		
	/*String query = "select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, "
			+ "za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema "
			+ "from odjel o "
			+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
			+ "inner join zaposleni z on z.JMB=d.JMB "
			+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
			+ "inner join zaposleni za on za.JMB=mst.JMB "
			+ "order by o.IdOdjela asc; ";*/
	
		String query = "SELECT * FROM odjel_doktor_med_tehnicar";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			// ps = conn.prepareStatement(query);
			cs = conn.prepareCall(query);
			rs = cs.executeQuery();
			
			while (rs.next())
				retVal.add(new Odjel(rs.getInt(1), rs.getString(2), rs.getString(3), new Doktor(new Zaposleni(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
				rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12)), rs.getString(13)),new MedicinskiTehnicar(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
				rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23))));

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
	public Odjel odjel(int idOdjela) {
		Odjel retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, "
				+ "za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema "
				+ "from odjel o "
				+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
				+ "inner join zaposleni z on z.JMB=d.JMB "
				+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
				+ "inner join zaposleni za on za.JMB=mst.JMB "
				+ "WHERE o.IdOdjela=? "
				+ "order by o.IdOdjela desc; ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOdjela);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Odjel(rs.getInt(1), rs.getString(2), rs.getString(3), new Doktor(new Zaposleni(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
				rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12)), rs.getString(13)),new MedicinskiTehnicar(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
				rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23)));
			
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
	public List<Odjel> odjeli(String nazivOdjela) {
		List<Odjel> retVal = new ArrayList<Odjel>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select o.IdOdjela, o.NazivOdjela, o.Adresa, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, d.Specijalizacija, za.JMB, "
				+ "za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, za.Pol, za.Telefon, mst.StrucnaSprema "
				+ "from odjel o "
				+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
				+ "inner join zaposleni z on z.JMB=d.JMB "
				+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
				+ "inner join zaposleni za on za.JMB=mst.JMB "
				+ "where o.NazivOdjela LIKE ? "
				+ "order by o.IdOdjela desc; ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(nazivOdjela));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Odjel(rs.getInt(1), rs.getString(2), rs.getString(3), new Doktor(new Zaposleni(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8),
						rs.getString(9), rs.getDouble(10), rs.getString(11), rs.getString(12)), rs.getString(13)),new MedicinskiTehnicar(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
						rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23))));
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
	public boolean dodajOdjel(Odjel odjel) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO odjel VALUES "
				+ "(?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, odjel.getIdOdjela());
			ps.setString(2, odjel.getNazivOdjela());
			ps.setString(3, odjel.getAdresa());
			ps.setString(4, odjel.getDoktor().getZaposleni().getJmb());
			ps.setString(5, odjel.getMedicinskiTehnicar().getZaposleni().getJmb());

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
	public boolean azurirajOdjel(Odjel odjel) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE odjel SET "
				+ "NazivOdjela=?, "
				+ "Adresa=?, "
				+ "JMBSefaOdjela=?, "
				+ "JMBGlavneMedSestreTehnicara=? "
				+ "WHERE IdOdjela=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, odjel.getNazivOdjela());
			ps.setString(2, odjel.getAdresa());
			ps.setString(3, odjel.getDoktor().getZaposleni().getJmb());
			ps.setString(4, odjel.getMedicinskiTehnicar().getZaposleni().getJmb());
			ps.setInt(5, odjel.getIdOdjela());

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
	public boolean obrisiOdjel(int idOdjela) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM odjel "
				+ "WHERE IdOdjela=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOdjela);

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
