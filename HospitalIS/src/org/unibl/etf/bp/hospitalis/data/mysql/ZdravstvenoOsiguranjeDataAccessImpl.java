package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.ZdravstvenoOsiguranjeDataAccess;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;

public class ZdravstvenoOsiguranjeDataAccessImpl implements ZdravstvenoOsiguranjeDataAccess {

	@Override
	public List<ZdravstvenoOsiguranje> zdravstvenaOsiguranja(String davalacOsiguranja) {
		List<ZdravstvenoOsiguranje> retVal = new ArrayList<ZdravstvenoOsiguranje>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT DavalacOsiguranja, Adresa, Telefon "
				+ "FROM zdravstveno_osiguranje "
				+ "WHERE DavalacOsiguranja LIKE ? "
				+ "ORDER BY DavalacOsiguranja ASC ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(davalacOsiguranja));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new ZdravstvenoOsiguranje(rs.getString(1), rs
						.getString(2), rs.getString(3)));
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
	public boolean dodajZdravstvenoOsiguranje(ZdravstvenoOsiguranje zdravstvenoOsiguranje) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "INSERT INTO zdravstveno_osiguranje VALUES "
					+ "(?, ?, ?) ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, zdravstvenoOsiguranje.getDavalacOsiguranja());
				ps.setString(2, zdravstvenoOsiguranje.getAdresa());
				ps.setString(3, zdravstvenoOsiguranje.getTelefon());

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
		public boolean azurirajZdravstvenoOsiguranje(ZdravstvenoOsiguranje zdravstvenoOsiguranje) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "UPDATE zdravstveno_osiguranje SET "
					+ "Adresa=?, "
					+ "Telefon=? "
					+ "WHERE DavalacOsiguranja=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, zdravstvenoOsiguranje.getAdresa());
				ps.setString(2, zdravstvenoOsiguranje.getTelefon());
				ps.setString(3, zdravstvenoOsiguranje.getDavalacOsiguranja());

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
		public boolean obrisiZdravstvenoOsiguranje(String davalacOsiguranja) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "DELETE FROM zdravstveno_osiguranje "
					+ "WHERE DavalacOsiguranja=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, davalacOsiguranja);

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

