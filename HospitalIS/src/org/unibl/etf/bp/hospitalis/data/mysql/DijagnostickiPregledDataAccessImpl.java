package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.DijagnostickiPregledDataAccess;
import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;

public class DijagnostickiPregledDataAccessImpl implements DijagnostickiPregledDataAccess {

	@Override
	public DijagnostickiPregled dijagnostickiPregled(int idPregleda) {
		DijagnostickiPregled retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT IdPregleda, NazivPregleda, CijenaPregleda "
				+ "FROM dijagnosticki_pregled "
				+ "WHERE IdPregleda=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idPregleda);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new DijagnostickiPregled(rs.getInt(1), rs.getString(2),
								rs.getDouble(3));
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
	public List<DijagnostickiPregled> dijagnostickiPregledi(String nazivPregleda) {
		List<DijagnostickiPregled> retVal = new ArrayList<DijagnostickiPregled>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT IdPregleda, NazivPregleda, CijenaPregleda "
				+ "FROM dijagnosticki_pregled "
				+ "WHERE NazivPregleda LIKE ? "
				+ "ORDER BY IdPregleda ASC ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(nazivPregleda));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new DijagnostickiPregled(rs.getInt(1), rs.getString(2),
						rs.getDouble(3)));
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
	public boolean dodajDijagnostickiPregled(DijagnostickiPregled dijagnostickiPregled) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "INSERT INTO dijagnosticki_pregled VALUES "
					+ "(?, ?, ?) ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, dijagnostickiPregled.getIdPregleda());
				ps.setString(2, dijagnostickiPregled.getNazivPregleda());
				ps.setDouble(3, dijagnostickiPregled.getCijenaPregleda());

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
		public boolean azurirajDijagnostickiPregled(DijagnostickiPregled dijagnostickiPregled) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "UPDATE dijagnosticki_pregled SET "
					+ "NazivPregleda=?, "
					+ "CijenaPregleda=? "
					+ "WHERE IdPregleda=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, dijagnostickiPregled.getNazivPregleda());
				ps.setDouble(2, dijagnostickiPregled.getCijenaPregleda());
				ps.setInt(3, dijagnostickiPregled.getIdPregleda());

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
		public boolean obrisiDijagnostickiPregled(int idPregleda) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "DELETE FROM dijagnosticki_pregled "
					+ "WHERE IdPregleda=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, idPregleda);

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
