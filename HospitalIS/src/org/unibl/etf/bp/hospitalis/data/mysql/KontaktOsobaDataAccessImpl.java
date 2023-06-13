package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.KontaktOsobaDataAccess;
import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;

public class KontaktOsobaDataAccessImpl implements KontaktOsobaDataAccess {

	@Override
	public List<KontaktOsoba> kontaktOsobe(String jmbKontaktOsobe) {
		List<KontaktOsoba> retVal = new ArrayList<KontaktOsoba>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT JMBKontaktOsobe, Ime, Prezime, Telefon "
				+ "FROM kontakt_osoba "
				+ "WHERE JMBKontaktOsobe LIKE ? "
				+ "ORDER BY Prezime ASC ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(jmbKontaktOsobe));
			rs = ps.executeQuery();

			while (rs.next())
			//{
				retVal.add(new KontaktOsoba(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));
				// System.out.println(rs.getString(1));
			//} 
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
	public boolean dodajKontaktOsobu(KontaktOsoba kontaktOsoba) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "INSERT INTO kontakt_osoba VALUES "
					+ "(?, ?, ?, ?) ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, kontaktOsoba.getJmbKontaktOsobe());
				ps.setString(2, kontaktOsoba.getIme());
				ps.setString(3, kontaktOsoba.getPrezime());
				ps.setString(4, kontaktOsoba.getTelefon());

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
		public boolean azurirajKontaktOsobu(KontaktOsoba kontaktOsoba) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "UPDATE kontakt_osoba SET "
					+ "Telefon=? "
					+ "WHERE JMBKontaktOsobe=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, kontaktOsoba.getTelefon());
				ps.setString(2, kontaktOsoba.getJmbKontaktOsobe());

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
		public boolean obrisiKontaktOsobu(String jmbKontaktOsobe) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "DELETE FROM kontakt_osoba "
					+ "WHERE JMBKontaktOsobe=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, jmbKontaktOsobe);

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
