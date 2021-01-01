package com.packt.jdeveloper.cookbook.shared.bc.test;

import com.packt.jdeveloper.cookbook.shared.bc.database.SQLProcedure;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtApplicationModuleImpl;


import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewObjectImpl;


import com.packt.jdeveloper.cookbook.shared.bc.test.common.HrAppModule;

import java.math.BigDecimal;

import java.sql.Types;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.domain.Number;
import oracle.jbo.server.ViewLinkImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu May 12 20:30:16 EEST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HrAppModuleImpl extends ExtApplicationModuleImpl implements HrAppModule {

    public static ADFLogger LOGGER =
        ADFLogger.createADFLogger(HrAppModuleImpl.class);

    /**
     * This is the default constructor (do not remove).
     */
    public HrAppModuleImpl() {
    }

    /**
     * Test method executed by the Business Component Browser.
     */
    public void testSQLProcedure() {
        Number employeeId = new Number(108);

        SQLProcedure procIn =
            new SQLProcedure("TEST_PKG.TEST_PROC_IN", this.getDBTransaction());
        procIn.setIN(employeeId);
        procIn.execute();

        SQLProcedure procInOut =
            new SQLProcedure("TEST_PKG.TEST_PROC_IN_OUT", this.getDBTransaction());
        procInOut.setINOUT(employeeId, Types.NUMERIC);
        procInOut.execute();
        Number managerId =
            procInOut.getOUT(1) != null ? new Number(((BigDecimal)procInOut.getOUT(1)).intValue()) :
            null;
        LOGGER.info("managerId ==> " + managerId);

        SQLProcedure funcIn =
            new SQLProcedure("TEST_PKG.TEST_FUNC_IN", this.getDBTransaction());
        funcIn.setIN(managerId);        
        funcIn.setRETURN(Types.BIGINT); // test out of sequence RETURN
        funcIn.setRETURN(Types.CHAR); // test multiple RETURNs
        funcIn.execute();
        String managerLastName = (String)funcIn.getRETURN();
        LOGGER.info("managerLastName ==> " + managerLastName);

        SQLProcedure funcInOut =
            new SQLProcedure("TEST_PKG.TEST_FUNC_IN_OUT", this.getDBTransaction());
        funcInOut.setRETURN(Types.CHAR);
        funcInOut.setINOUT(managerLastName, Types.CHAR);
        funcInOut.execute();
        String managerFirstName = (String)funcInOut.getOUT(2);
        String email = (String)funcInOut.getRETURN();
        LOGGER.info("managerFirstName ==> " + managerFirstName);
        LOGGER.info("email ==> " + email);
    }

    /**
     * Container's getter for Employees.
     * @return Employees
     */
    public ExtViewObjectImpl getEmployees() {
        return (ExtViewObjectImpl)findViewObject("Employees");
    }

    /**
     * Container's getter for Managers.
     * @return Managers
     */
    public ExtViewObjectImpl getManagers() {
        return (ExtViewObjectImpl)findViewObject("Managers");
    }

    /**
     * Container's getter for EmpManagerFkLink1.
     * @return EmpManagerFkLink1
     */
    public ViewLinkImpl getEmpManagerFkLink1() {
        return (ViewLinkImpl)findViewLink("EmpManagerFkLink1");
    }
}
