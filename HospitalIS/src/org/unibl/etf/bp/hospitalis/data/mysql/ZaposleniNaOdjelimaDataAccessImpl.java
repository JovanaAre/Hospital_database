package org.unibl.etf.bp.hospitalis.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.unibl.etf.bp.hospitalis.data.ZaposleniNaOdjelimaDataAccess;
import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.entity.ZaposleniNaOdjelima;


public class ZaposleniNaOdjelimaDataAccessImpl implements
   ZaposleniNaOdjelimaDataAccess {

	@Override
	public List<ZaposleniNaOdjelima> zaposleniNaOdjelima(int idOdjela) {
		List<ZaposleniNaOdjelima> retVal = new ArrayList<ZaposleniNaOdjelima>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select rn.DatumZaposlenja, z.JMB, z.Ime, z.Prezime, z.Email, z.DatumRodjenja, z.Adresa, z.Plata, z.Pol, z.Telefon, "
				+ "o.IdOdjela, o.NazivOdjela, o.Adresa, za.JMB, za.Ime, za.Prezime, za.Email, za.DatumRodjenja, za.Adresa, za.Plata, "
				+ "za.Pol, za.Telefon, d.Specijalizacija, zap.JMB, zap.Ime, zap.Prezime, zap.Email, zap.DatumRodjenja, zap.Adresa, zap.Plata, "
				+ "zap.Pol, zap.Telefon, mst.StrucnaSprema "
				+ "from radi_na rn "
				+ "inner join zaposleni z on z.JMB=rn.JMB "
				+ "inner join odjel o on o.IdOdjela=rn.IdOdjela "
				+ "inner join doktor d on d.JMB=o.JMBSefaOdjela "
				+ "inner join zaposleni za on za.JMB=d.JMB "
				+ "inner join med_sestra_tehnicar mst on mst.JMB=o.JMBGlavneMedSestreTehnicara "
		        + "inner join zaposleni zap on zap.JMB=mst.JMB "
		        + "where rn.IdOdjela=? "
		        + "order by z.Prezime asc; ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOdjela);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new ZaposleniNaOdjelima(rs.getDate(1), new Zaposleni(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6),
						rs.getString(7), rs.getDouble(8), rs.getString(9), rs.getString(10)),new Odjel(rs.getInt(11), rs.getString(12), rs.getString(13), new Doktor(new Zaposleni(rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getDate(18),
								rs.getString(19), rs.getDouble(20), rs.getString(21), rs.getString(22)), rs.getString(23)),new MedicinskiTehnicar(new Zaposleni(rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getDate(28),
										rs.getString(29), rs.getDouble(30), rs.getString(31), rs.getString(32)), rs.getString(33)))));
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
	public boolean dodajZaposlenogNaOdjelu(ZaposleniNaOdjelima zaposleniNaOdjelu) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO radi_na VALUES "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, zaposleniNaOdjelu.getDatumZaposlenja());
			ps.setString(2, zaposleniNaOdjelu.getZaposleni().getJmb());
			ps.setInt(3, zaposleniNaOdjelu.getOdjel().getIdOdjela());

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

/*	@Override
	public boolean azurirajPredmetNaSP(
			PredmetNaStudijskomProgramu predmetNaSP) {
		boolean retVal = false;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{CALL azuriraj_p_na_sp(?, ?, ?, ?, ?, ?)}";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, predmetNaSP.getPredmet().getIdPredmeta());
			cs.setInt(2, predmetNaSP.getStudijskiProgram().getIdSP());
			cs.setByte(3, predmetNaSP.getSemestar());
			cs.setString(4, predmetNaSP.getTipPredmeta());
			cs.registerOutParameter(5, Types.BOOLEAN);
			cs.registerOutParameter(6, Types.VARCHAR);

			cs.execute();
			retVal = cs.getBoolean(5);
			if (!retVal)
				MySQLUtilities.getInstance().showErrorMessage(cs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}*/

	@Override
	public boolean obrisiZaposlenogNaOdjelu(Date datumZaposlenja, String jmbZaposlenog, int idOdjela) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM radi_na "
				+ "WHERE DatumZaposlenja=? AND JMB=? AND IdOdjela=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDate(1, datumZaposlenja);
			ps.setString(2, jmbZaposlenog);
			ps.setInt(3, idOdjela);

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
