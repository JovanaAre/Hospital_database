package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.SobaDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Soba;

public class SobaDataAccessImpl implements SobaDataAccess {

	@Override
	public Soba soba(int brojSobe) {
		Soba retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT BrojSobe, CijenaSobe, BrojKreveta "
				+ "FROM soba "
				+ "WHERE BrojSobe=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, brojSobe);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Soba(rs.getInt(1), rs.getDouble(2), rs.getInt(3));
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
	public List<Soba> sveSobe() {
		List<Soba> retVal = new ArrayList<Soba>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT BrojSobe, CijenaSobe, BrojKreveta "
				+ "FROM soba "
				+ "ORDER BY BrojSobe ASC ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Soba(rs.getInt(1), rs.getDouble(2), rs.getInt(3)));
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
	public boolean dodajSobu(Soba soba) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "INSERT INTO soba VALUES "
					+ "(?, ?, ?) ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, soba.getBrojSobe());
				ps.setDouble(2, soba.getCijenaSobe());
				ps.setInt(3, soba.getBrojKreveta());

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
		public boolean azurirajSobu(Soba soba) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "UPDATE soba SET "
					+ "CijenaSobe=?, "
					+ "BrojKreveta=? "
					+ "WHERE BrojSobe=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setDouble(1, soba.getCijenaSobe());
				ps.setInt(2, soba.getBrojKreveta());
				ps.setInt(3, soba.getBrojSobe());

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
		public boolean obrisiSobu(int brojSobe) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "DELETE FROM soba "
					+ "WHERE BrojSobe=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, brojSobe);

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