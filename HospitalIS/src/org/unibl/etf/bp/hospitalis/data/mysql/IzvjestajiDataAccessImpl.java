package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Date;
import java.sql.Types;

import org.unibl.etf.bp.hospitalis.data.IzvjestajiDataAccess;

public class IzvjestajiDataAccessImpl implements IzvjestajiDataAccess {

	@Override
	public Vector<Vector<Object>> racuniPacijenata() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "{ CALL prikazi_racune() }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getDate(1));
				red.add(rs.getString(2));
				red.add(rs.getString(3));
				red.add(rs.getDouble(4));
				red.add(rs.getDouble(5));
				red.add(rs.getDouble(6));
				red.add(rs.getDouble(7));
                retVal.add(red);
			}
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
	public Vector<Vector<Object>> ukupanIznosRacuna2022() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM ukupan_iznos_racuna_2022";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			rs = cs.executeQuery();
			
				while (rs.next()) {
					Vector<Object> red = new Vector<Object>();
					red.add(rs.getDouble(1));
					retVal.add(red);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	@Override
	public Vector<Vector<Object>> ukupanIznosRacunaTekucaGodina() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM ukupan_iznos_racuna_tekuca_godina";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			rs = cs.executeQuery();
			
				while (rs.next()) {
					Vector<Object> red = new Vector<Object>();
					red.add(rs.getDouble(1));
					retVal.add(red);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	@Override
	public Vector<Vector<Object>> prosjecnePlateDoktoraOdjeli() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "{ CALL prosjecne_plate_doktora_odjeli() }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getDouble(2));
                retVal.add(red);
			}
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
	public Vector<Vector<Object>> prosjecnePlateMedicinskihTehnicaraOdjeli() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "{ CALL prosjecne_plate_med_tehnicara_odjeli() }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getDouble(2));
                retVal.add(red);
			}
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
	public Vector<Vector<Object>> ukupanBrojZaposlenihOdjeli() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "{ CALL ukupan_broj_zaposlenih_odjeli() }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1));
				red.add(rs.getInt(2));
                retVal.add(red);
			}
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
	public Vector<Vector<Object>> ukupnoEvidentiranihPacijenataTekucaGodina() {
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM ukupno_evidentiranih_pacijenata_tekuca_godina";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			rs = cs.executeQuery();
			
				while (rs.next()) {
					Vector<Object> red = new Vector<Object>();
					red.add(rs.getInt(1));
					retVal.add(red);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
}