package org.unibl.etf.bp.hospitalis.data.mysql;

import org.unibl.etf.bp.hospitalis.data.DataAccessFactory;
import org.unibl.etf.bp.hospitalis.data.LijekDataAccess;
import org.unibl.etf.bp.hospitalis.data.ZdravstvenoOsiguranjeDataAccess;
import org.unibl.etf.bp.hospitalis.data.KontaktOsobaDataAccess;
import org.unibl.etf.bp.hospitalis.data.ZaposleniDataAccess;
import org.unibl.etf.bp.hospitalis.data.DoktorDataAccess;
import org.unibl.etf.bp.hospitalis.data.MedicinskiTehnicarDataAccess;
import org.unibl.etf.bp.hospitalis.data.OdjelDataAccess;
import org.unibl.etf.bp.hospitalis.data.TelefonOdjelaDataAccess;
import org.unibl.etf.bp.hospitalis.data.PacijentDataAccess;
import org.unibl.etf.bp.hospitalis.data.PacijentNijeZadrzanDataAccess;
import org.unibl.etf.bp.hospitalis.data.ZaposleniNaOdjelimaDataAccess;
import org.unibl.etf.bp.hospitalis.data.PrepisivanjeDataAccess;
import org.unibl.etf.bp.hospitalis.data.SobaDataAccess;
import org.unibl.etf.bp.hospitalis.data.DijagnostickiPregledDataAccess;
import org.unibl.etf.bp.hospitalis.data.PacijentZadrzanDataAccess;
import org.unibl.etf.bp.hospitalis.data.PregledanjeDataAccess;

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
	
	@Override
	public TelefonOdjelaDataAccess getTelefonOdjelaDataAccess() {
		return new TelefonOdjelaDataAccessImpl();
	}
	
	@Override
	public PacijentDataAccess getPacijentDataAccess() {
		return new PacijentDataAccessImpl();
	}
	
	@Override
	public PacijentNijeZadrzanDataAccess getPacijentNijeZadrzanDataAccess() {
		return new PacijentNijeZadrzanDataAccessImpl();
	}
	
	@Override
	public ZaposleniNaOdjelimaDataAccess getZaposleniNaOdjelimaDataAccess() {
		return new ZaposleniNaOdjelimaDataAccessImpl();
	}
	
	@Override
	public PrepisivanjeDataAccess getPrepisivanjeDataAccess() {
		return new PrepisivanjeDataAccessImpl();
	}
	
	@Override
	public SobaDataAccess getSobaDataAccess() {
		return new SobaDataAccessImpl();
	}
	
	@Override
	public DijagnostickiPregledDataAccess getDijagnostickiPregledDataAccess() {
		return new DijagnostickiPregledDataAccessImpl();
	}
	
	@Override
	public PacijentZadrzanDataAccess getPacijentZadrzanDataAccess() {
		return new PacijentZadrzanDataAccessImpl();
	}
	
	@Override
	public PregledanjeDataAccess getPregledanjeDataAccess() {
		return new PregledanjeDataAccessImpl();
	}

//	@Override
//	public IzvestajiDataAccess getIzvestajiDataAccess() {
//		return new IzvestajiDataAccessImpl();
//	}

}
