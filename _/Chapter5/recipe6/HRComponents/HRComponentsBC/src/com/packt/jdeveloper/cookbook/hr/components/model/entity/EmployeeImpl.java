package com.packt.jdeveloper.cookbook.hr.components.model.entity;

import com.packt.jdeveloper.cookbook.hr.components.model.view.EmployeeCountRowImpl;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtEntityDefImpl;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu May 26 22:48:49 EEST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeeImpl extends ExtEntityImpl {


    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        EmployeeId {
            public Object get(EmployeeImpl obj) {
                return obj.getEmployeeId();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setEmployeeId((Number)value);
            }
        }
        ,
        FirstName {
            public Object get(EmployeeImpl obj) {
                return obj.getFirstName();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setFirstName((String)value);
            }
        }
        ,
        LastName {
            public Object get(EmployeeImpl obj) {
                return obj.getLastName();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setLastName((String)value);
            }
        }
        ,
        Email {
            public Object get(EmployeeImpl obj) {
                return obj.getEmail();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setEmail((String)value);
            }
        }
        ,
        PhoneNumber {
            public Object get(EmployeeImpl obj) {
                return obj.getPhoneNumber();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setPhoneNumber((String)value);
            }
        }
        ,
        HireDate {
            public Object get(EmployeeImpl obj) {
                return obj.getHireDate();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setHireDate((Date)value);
            }
        }
        ,
        JobId {
            public Object get(EmployeeImpl obj) {
                return obj.getJobId();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setJobId((String)value);
            }
        }
        ,
        Salary {
            public Object get(EmployeeImpl obj) {
                return obj.getSalary();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setSalary((Number)value);
            }
        }
        ,
        CommissionPct {
            public Object get(EmployeeImpl obj) {
                return obj.getCommissionPct();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setCommissionPct((Number)value);
            }
        }
        ,
        ManagerId {
            public Object get(EmployeeImpl obj) {
                return obj.getManagerId();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setManagerId((Number)value);
            }
        }
        ,
        DepartmentId {
            public Object get(EmployeeImpl obj) {
                return obj.getDepartmentId();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setDepartmentId((Number)value);
            }
        }
        ,
        DepartmentsManaged {
            public Object get(EmployeeImpl obj) {
                return obj.getDepartmentsManaged();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        EmployeeDepartment {
            public Object get(EmployeeImpl obj) {
                return obj.getEmployeeDepartment();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setEmployeeDepartment((DepartmentImpl)value);
            }
        }
        ,
        EmployeesManaged {
            public Object get(EmployeeImpl obj) {
                return obj.getEmployeesManaged();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        EmployeeManager {
            public Object get(EmployeeImpl obj) {
                return obj.getEmployeeManager();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setEmployeeManager((EmployeeImpl)value);
            }
        }
        ,
        EmployeeCount {
            public Object get(EmployeeImpl obj) {
                return obj.getEmployeeCount();
            }

            public void put(EmployeeImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(EmployeeImpl object);

        public abstract void put(EmployeeImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int EMPLOYEEID = AttributesEnum.EmployeeId.index();
    public static final int FIRSTNAME = AttributesEnum.FirstName.index();
    public static final int LASTNAME = AttributesEnum.LastName.index();
    public static final int EMAIL = AttributesEnum.Email.index();
    public static final int PHONENUMBER = AttributesEnum.PhoneNumber.index();
    public static final int HIREDATE = AttributesEnum.HireDate.index();
    public static final int JOBID = AttributesEnum.JobId.index();
    public static final int SALARY = AttributesEnum.Salary.index();
    public static final int COMMISSIONPCT = AttributesEnum.CommissionPct.index();
    public static final int MANAGERID = AttributesEnum.ManagerId.index();
    public static final int DEPARTMENTID = AttributesEnum.DepartmentId.index();
    public static final int DEPARTMENTSMANAGED = AttributesEnum.DepartmentsManaged.index();
    public static final int EMPLOYEEDEPARTMENT = AttributesEnum.EmployeeDepartment.index();
    public static final int EMPLOYEESMANAGED = AttributesEnum.EmployeesManaged.index();
    public static final int EMPLOYEEMANAGER = AttributesEnum.EmployeeManager.index();
    public static final int EMPLOYEECOUNT = AttributesEnum.EmployeeCount.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EmployeeImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.packt.jdeveloper.cookbook.hr.components.model.entity.Employee");
    }

    /**
     * Gets the attribute value for EmployeeId, using the alias name EmployeeId.
     * @return the EmployeeId
     */
    public Number getEmployeeId() {
        return (Number)getAttributeInternal(EMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for EmployeeId.
     * @param value value to set the EmployeeId
     */
    public void setEmployeeId(Number value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for FirstName, using the alias name FirstName.
     * @return the FirstName
     */
    public String getFirstName() {
        return (String)getAttributeInternal(FIRSTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for FirstName.
     * @param value value to set the FirstName
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**
     * Gets the attribute value for LastName, using the alias name LastName.
     * @return the LastName
     */
    public String getLastName() {
        return (String)getAttributeInternal(LASTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastName.
     * @param value value to set the LastName
     */
    public void setLastName(String value) {
        setAttributeInternal(LASTNAME, value);
    }

    /**
     * Gets the attribute value for Email, using the alias name Email.
     * @return the Email
     */
    public String getEmail() {
        return (String)getAttributeInternal(EMAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Email.
     * @param value value to set the Email
     */
    public void setEmail(String value) {
        setAttributeInternal(EMAIL, value);
    }

    /**
     * Gets the attribute value for PhoneNumber, using the alias name PhoneNumber.
     * @return the PhoneNumber
     */
    public String getPhoneNumber() {
        return (String)getAttributeInternal(PHONENUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for PhoneNumber.
     * @param value value to set the PhoneNumber
     */
    public void setPhoneNumber(String value) {
        setAttributeInternal(PHONENUMBER, value);
    }

    /**
     * Gets the attribute value for HireDate, using the alias name HireDate.
     * @return the HireDate
     */
    public Date getHireDate() {
        return (Date)getAttributeInternal(HIREDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HireDate.
     * @param value value to set the HireDate
     */
    public void setHireDate(Date value) {
        setAttributeInternal(HIREDATE, value);
    }

    /**
     * Gets the attribute value for JobId, using the alias name JobId.
     * @return the JobId
     */
    public String getJobId() {
        return (String)getAttributeInternal(JOBID);
    }

    /**
     * Sets <code>value</code> as the attribute value for JobId.
     * @param value value to set the JobId
     */
    public void setJobId(String value) {
        setAttributeInternal(JOBID, value);
    }

    /**
     * Gets the attribute value for Salary, using the alias name Salary.
     * @return the Salary
     */
    public Number getSalary() {
        return (Number)getAttributeInternal(SALARY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Salary.
     * @param value value to set the Salary
     */
    public void setSalary(Number value) {
        setAttributeInternal(SALARY, value);
    }

    /**
     * Gets the attribute value for CommissionPct, using the alias name CommissionPct.
     * @return the CommissionPct
     */
    public Number getCommissionPct() {
        return (Number)getAttributeInternal(COMMISSIONPCT);
    }

    /**
     * Sets <code>value</code> as the attribute value for CommissionPct.
     * @param value value to set the CommissionPct
     */
    public void setCommissionPct(Number value) {
        setAttributeInternal(COMMISSIONPCT, value);
    }

    /**
     * Gets the attribute value for ManagerId, using the alias name ManagerId.
     * @return the ManagerId
     */
    public Number getManagerId() {
        return (Number)getAttributeInternal(MANAGERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ManagerId.
     * @param value value to set the ManagerId
     */
    public void setManagerId(Number value) {
        setAttributeInternal(MANAGERID, value);
    }

    /**
     * Gets the attribute value for DepartmentId, using the alias name DepartmentId.
     * @return the DepartmentId
     */
    public Number getDepartmentId() {
        return (Number)getAttributeInternal(DEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DepartmentId.
     * @param value value to set the DepartmentId
     */
    public void setDepartmentId(Number value) {
        setAttributeInternal(DEPARTMENTID, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getDepartmentsManaged() {
        return (RowIterator)getAttributeInternal(DEPARTMENTSMANAGED);
    }

    /**
     * @return the associated entity DepartmentImpl.
     */
    public DepartmentImpl getEmployeeDepartment() {
        return (DepartmentImpl)getAttributeInternal(EMPLOYEEDEPARTMENT);
    }

    /**
     * Sets <code>value</code> as the associated entity DepartmentImpl.
     */
    public void setEmployeeDepartment(DepartmentImpl value) {
        setAttributeInternal(EMPLOYEEDEPARTMENT, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getEmployeesManaged() {
        return (RowIterator)getAttributeInternal(EMPLOYEESMANAGED);
    }

    /**
     * @return the associated entity EmployeeImpl.
     */
    public EmployeeImpl getEmployeeManager() {
        return (EmployeeImpl)getAttributeInternal(EMPLOYEEMANAGER);
    }

    /**
     * Sets <code>value</code> as the associated entity EmployeeImpl.
     */
    public void setEmployeeManager(EmployeeImpl value) {
        setAttributeInternal(EMPLOYEEMANAGER, value);
    }


    /**
     * Gets the view accessor <code>RowSet</code> EmployeeCount.
     */
    public RowSet getEmployeeCount() {
        return (RowSet)getAttributeInternal(EMPLOYEECOUNT);
    }


    /**
     * @param employeeId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number employeeId) {
        return new Key(new Object[]{employeeId});
    }

    /**
     * Validation method for Employee.
     */
    public boolean validateDepartmentEmployeeCount() {
        final int MAX_DEPARTMENT_EMPLOYEES = 9999;
        // get the EmployeeCount view accessor
        RowSet employeeCount = this.getEmployeeCount();
        // setup the DepartmentId bind variable
        employeeCount.setNamedWhereClauseParam("DepartmentId",
                                               this.getDepartmentId());
        // run the View Object query
        employeeCount.executeQuery();
        // check results
        if (employeeCount.hasNext()) {
            // get the EmployeeCount row
            EmployeeCountRowImpl employeeCountRow =
                (EmployeeCountRowImpl)employeeCount.next();
            // get the deparment employee count
            Number departmentEmployees = employeeCountRow.getEmployeeCount();
            if (departmentEmployees.compareTo(MAX_DEPARTMENT_EMPLOYEES) >
                0) {
                return false;
            }
        }
        return true;
    }


}
