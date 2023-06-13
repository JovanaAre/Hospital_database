package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.bp.hospitalis.data.LijekDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Lijek;

public class LijekDataAccessImpl implements LijekDataAccess {

	@Override
	public Lijek lijek(int idLijeka) {
		Lijek retVal = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka "
				+ "FROM lijek "
				+ "WHERE IdLijeka=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idLijeka);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Lijek(rs.getInt(1), rs.getString(2),
						rs.getString(3),
								rs.getDouble(4));
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
	public List<Lijek> lijekovi(String nazivLijeka) {
		List<Lijek> retVal = new ArrayList<Lijek>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT IdLijeka, NazivLijeka, TipLijeka, CijenaLijeka "
				+ "FROM lijek "
				+ "WHERE NazivLijeka LIKE ? "
				+ "ORDER BY IdLijeka ASC ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(nazivLijeka));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Lijek(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getDouble(4)));
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
	public boolean dodajLijek(Lijek lijek) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "INSERT INTO lijek VALUES "
					+ "(?, ?, ?, ?) ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, lijek.getIdLijeka());
				ps.setString(2, lijek.getNazivLijeka());
				ps.setString(3, lijek.getTipLijeka());
				ps.setDouble(4, lijek.getCijenaLijeka());

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
		public boolean azurirajLijek(Lijek lijek) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "UPDATE lijek SET "
					+ "NazivLijeka=?, "
					+ "TipLijeka=?, "
					+ "CijenaLijeka=? "
					+ "WHERE IdLijeka=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, lijek.getNazivLijeka());
				ps.setString(2, lijek.getTipLijeka());
				ps.setDouble(3, lijek.getCijenaLijeka());
				ps.setInt(4, lijek.getIdLijeka());

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
		public boolean obrisiLijek(int idLijeka) {
			boolean retVal = false;
			Connection conn = null;
			PreparedStatement ps = null;

			String query = "DELETE FROM lijek "
					+ "WHERE IdLijeka=? ";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, idLijeka);

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
