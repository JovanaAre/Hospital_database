package org.unibl.etf.bp.hospitalis.data;

import org.unibl.etf.bp.hospitalis.data.mysql.MySQLDataAccessFactory;

public abstract class DataAccessFactory {
	
	public abstract LijekDataAccess getLijekDataAccess();
    public abstract ZdravstvenoOsiguranjeDataAccess getZdravstvenoOsiguranjeDataAccess();
    public abstract KontaktOsobaDataAccess getKontaktOsobaDataAccess();
    public abstract ZaposleniDataAccess getZaposleniDataAccess();
    public abstract DoktorDataAccess getDoktorDataAccess();
    public abstract MedicinskiTehnicarDataAccess getMedicinskiTehnicarDataAccess();
    public abstract OdjelDataAccess getOdjelDataAccess();
//	public abstract StudijskiProgramDataAccess getStudijskiProgramDataAccess();
//	public abstract PredmetNaStudijskomProgramuDataAccess getPredmetNaStudijskomProgramuDataAccess();
//	public abstract IzvestajiDataAccess getIzvestajiDataAccess();

	public static DataAccessFactory getFactory(DataAccessFactoryType type) {
		if (DataAccessFactoryType.MySQL.equals(type)) {
			return new MySQLDataAccessFactory();
		}
		throw new IllegalArgumentException();
	}
	
}
