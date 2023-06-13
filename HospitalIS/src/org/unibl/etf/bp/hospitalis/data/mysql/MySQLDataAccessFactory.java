package org.unibl.etf.bp.hospitalis.data.mysql;

import org.unibl.etf.bp.hospitalis.data.DataAccessFactory;
import org.unibl.etf.bp.hospitalis.data.LijekDataAccess;
import org.unibl.etf.bp.hospitalis.data.ZdravstvenoOsiguranjeDataAccess;
import org.unibl.etf.bp.hospitalis.data.KontaktOsobaDataAccess;
import org.unibl.etf.bp.hospitalis.data.ZaposleniDataAccess;
import org.unibl.etf.bp.hospitalis.data.DoktorDataAccess;
import org.unibl.etf.bp.hospitalis.data.MedicinskiTehnicarDataAccess;
import org.unibl.etf.bp.hospitalis.data.OdjelDataAccess;

public class MySQLDataAccessFactory extends DataAccessFactory {

	@Override
	public LijekDataAccess getLijekDataAccess() {
		return new LijekDataAccessImpl();
	}

	@Override
	public ZdravstvenoOsiguranjeDataAccess getZdravstvenoOsiguranjeDataAccess() {
		return new ZdravstvenoOsiguranjeDataAccessImpl();
	}
	
	@Override
	public KontaktOsobaDataAccess getKontaktOsobaDataAccess() {
		return new KontaktOsobaDataAccessImpl();
	}
	
	@Override
	public ZaposleniDataAccess getZaposleniDataAccess() {
		return new ZaposleniDataAccessImpl();
	}
	
	@Override
	public DoktorDataAccess getDoktorDataAccess() {
		return new DoktorDataAccessImpl();
	}
	
	@Override
	public MedicinskiTehnicarDataAccess getMedicinskiTehnicarDataAccess() {
		return new MedicinskiTehnicarDataAccessImpl();
	}
	
	@Override
	public OdjelDataAccess getOdjelDataAccess() {
		return new OdjelDataAccessImpl();
	}
//
//	@Override
//	public StudijskiProgramDataAccess getStudijskiProgramDataAccess() {
//		return new StudijskiProgramDataAccessImpl();
//	}
//
//	@Override
//	public PredmetNaStudijskomProgramuDataAccess getPredmetNaStudijskomProgramuDataAccess() {
//		return new PredmetNaStudijskomProgramuDataAccessImpl();
//	}
//
//	@Override
//	public IzvestajiDataAccess getIzvestajiDataAccess() {
//		return new IzvestajiDataAccessImpl();
//	}

}
