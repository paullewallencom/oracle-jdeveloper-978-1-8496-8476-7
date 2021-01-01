package com.packt.jdeveloper.cookbook.hr.components.model.view.client;

import com.packt.jdeveloper.cookbook.hr.components.model.view.common.Employees;

import oracle.jbo.ViewObject;
import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jun 05 19:17:25 EEST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeesClient extends ViewUsageImpl implements Employees {
    /**
     * This is the default constructor (do not remove).
     */
    public EmployeesClient() {
    }


    public void adjustCommission(Number commissionPctAdjustment) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"adjustCommission",new String [] {"oracle.jbo.domain.Number"},new Object[] {commissionPctAdjustment});
        return;
    }
}
