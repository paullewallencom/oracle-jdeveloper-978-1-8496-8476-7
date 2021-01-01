package com.packt.jdeveloper.cookbook.hr.components.model.view;

import com.packt.jdeveloper.cookbook.hr.components.model.entity.EmployeeImpl;
import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewRowImpl;

import java.sql.SQLException;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jun 05 18:20:57 EEST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeesRowImpl extends ExtViewRowImpl {

    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(EmployeesRowImpl.class);
    private static final int SALES_DEPARTMENT_ID = 80;
    private static final double DEFAULT_COMMISSION = 0.10;

    @Override
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        // set the default hire date to today
        this.setHireDate((Date)Date.getCurrentDate());
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        EmployeeId {
            public Object get(EmployeesRowImpl obj) {
                return obj.getEmployeeId();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setEmployeeId((Number)value);
            }
        },
        FirstName {
            public Object get(EmployeesRowImpl obj) {
                return obj.getFirstName();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setFirstName((String)value);
            }
        },
        LastName {
            public Object get(EmployeesRowImpl obj) {
                return obj.getLastName();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setLastName((String)value);
            }
        },
        Email {
            public Object get(EmployeesRowImpl obj) {
                return obj.getEmail();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setEmail((String)value);
            }
        },
        PhoneNumber {
            public Object get(EmployeesRowImpl obj) {
                return obj.getPhoneNumber();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setPhoneNumber((String)value);
            }
        },
        HireDate {
            public Object get(EmployeesRowImpl obj) {
                return obj.getHireDate();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setHireDate((Date)value);
            }
        },
        JobId {
            public Object get(EmployeesRowImpl obj) {
                return obj.getJobId();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setJobId((String)value);
            }
        },
        Salary {
            public Object get(EmployeesRowImpl obj) {
                return obj.getSalary();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setSalary((Number)value);
            }
        },
        CommissionPct {
            public Object get(EmployeesRowImpl obj) {
                return obj.getCommissionPct();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setCommissionPct((Number)value);
            }
        },
        ManagerId {
            public Object get(EmployeesRowImpl obj) {
                return obj.getManagerId();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setManagerId((Number)value);
            }
        },
        DepartmentId {
            public Object get(EmployeesRowImpl obj) {
                return obj.getDepartmentId();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setDepartmentId((Number)value);
            }
        },
        Employees {
            public Object get(EmployeesRowImpl obj) {
                return obj.getEmployees();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        },
        Departmens {
            public Object get(EmployeesRowImpl obj) {
                return obj.getDepartmens();
            }

            public void put(EmployeesRowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        };
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(EmployeesRowImpl object);

        public abstract void put(EmployeesRowImpl object, Object value);

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
    public static final int COMMISSIONPCT =
        AttributesEnum.CommissionPct.index();
    public static final int MANAGERID = AttributesEnum.ManagerId.index();
    public static final int DEPARTMENTID = AttributesEnum.DepartmentId.index();
    public static final int EMPLOYEES = AttributesEnum.Employees.index();
    public static final int DEPARTMENS = AttributesEnum.Departmens.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EmployeesRowImpl() {
    }

    /**
     * Gets Employee entity object.
     * @return the Employee
     */
    public EmployeeImpl getEmployee() {
        return (EmployeeImpl)getEntity(0);
    }

    /**
     * Gets the attribute value for EMPLOYEE_ID using the alias name EmployeeId.
     * @return the EMPLOYEE_ID
     */
    public Number getEmployeeId() {
        return (Number)getAttributeInternal(EMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as attribute value for EMPLOYEE_ID using the alias name EmployeeId.
     * @param value value to set the EMPLOYEE_ID
     */
    public void setEmployeeId(Number value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for FIRST_NAME using the alias name FirstName.
     * @return the FIRST_NAME
     */
    public String getFirstName() {
        return (String)getAttributeInternal(FIRSTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for FIRST_NAME using the alias name FirstName.
     * @param value value to set the FIRST_NAME
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**
     * Gets the attribute value for LAST_NAME using the alias name LastName.
     * @return the LAST_NAME
     */
    public String getLastName() {
        return (String)getAttributeInternal(LASTNAME);
    }

    /**
     * Sets <code>value</code> as attribute value for LAST_NAME using the alias name LastName.
     * @param value value to set the LAST_NAME
     */
    public void setLastName(String value) {
        setAttributeInternal(LASTNAME, value);
    }

    /**
     * Gets the attribute value for EMAIL using the alias name Email.
     * @return the EMAIL
     */
    public String getEmail() {
        return (String)getAttributeInternal(EMAIL);
    }

    /**
     * Sets <code>value</code> as attribute value for EMAIL using the alias name Email.
     * @param value value to set the EMAIL
     */
    public void setEmail(String value) {
        setAttributeInternal(EMAIL, value);
    }

    /**
     * Gets the attribute value for PHONE_NUMBER using the alias name PhoneNumber.
     * @return the PHONE_NUMBER
     */
    public String getPhoneNumber() {
        return (String)getAttributeInternal(PHONENUMBER);
    }

    /**
     * Sets <code>value</code> as attribute value for PHONE_NUMBER using the alias name PhoneNumber.
     * @param value value to set the PHONE_NUMBER
     */
    public void setPhoneNumber(String value) {
        setAttributeInternal(PHONENUMBER, value);
    }

    /**
     * Gets the attribute value for HIRE_DATE using the alias name HireDate.
     * @return the HIRE_DATE
     */
    public Date getHireDate() {
        // get the HireDate attribute value
        Date hireDate = (Date)getAttributeInternal(HIREDATE);
        // check for null and return today's date if needed
        return (hireDate == null) ? (Date)Date.getCurrentDate() : hireDate;
    }

    /**
     * Sets <code>value</code> as attribute value for HIRE_DATE using the alias name HireDate.
     * @param value value to set the HIRE_DATE
     */
    public void setHireDate(Date value) {
        setAttributeInternal(HIREDATE, value);
    }

    /**
     * Gets the attribute value for JOB_ID using the alias name JobId.
     * @return the JOB_ID
     */
    public String getJobId() {
        return (String)getAttributeInternal(JOBID);
    }

    /**
     * Sets <code>value</code> as attribute value for JOB_ID using the alias name JobId.
     * @param value value to set the JOB_ID
     */
    public void setJobId(String value) {
        setAttributeInternal(JOBID, value);
    }

    /**
     * Gets the attribute value for SALARY using the alias name Salary.
     * @return the SALARY
     */
    public Number getSalary() {
        return (Number)getAttributeInternal(SALARY);
    }

    /**
     * Sets <code>value</code> as attribute value for SALARY using the alias name Salary.
     * @param value value to set the SALARY
     */
    public void setSalary(Number value) {
        setAttributeInternal(SALARY, value);
    }

    /**
     * Gets the attribute value for COMMISSION_PCT using the alias name CommissionPct.
     * @return the COMMISSION_PCT
     */
    public Number getCommissionPct() {
        return (Number)getAttributeInternal(COMMISSIONPCT);
    }

    /**
     * Sets <code>value</code> as attribute value for COMMISSION_PCT using the alias name CommissionPct.
     * @param value value to set the COMMISSION_PCT
     */
    public void setCommissionPct(Number value) {
        setAttributeInternal(COMMISSIONPCT, value);
    }

    /**
     * Gets the attribute value for MANAGER_ID using the alias name ManagerId.
     * @return the MANAGER_ID
     */
    public Number getManagerId() {
        return (Number)getAttributeInternal(MANAGERID);
    }

    /**
     * Sets <code>value</code> as attribute value for MANAGER_ID using the alias name ManagerId.
     * @param value value to set the MANAGER_ID
     */
    public void setManagerId(Number value) {
        setAttributeInternal(MANAGERID, value);
    }

    /**
     * Gets the attribute value for DEPARTMENT_ID using the alias name DepartmentId.
     * @return the DEPARTMENT_ID
     */
    public Number getDepartmentId() {
        return (Number)getAttributeInternal(DEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as attribute value for DEPARTMENT_ID using the alias name DepartmentId.
     * @param value value to set the DEPARTMENT_ID
     */
    public void setDepartmentId(Number value) {
        // set the department identifier
        setAttributeInternal(DEPARTMENTID, value);
        // set employee's commission based on employee's department
        try {
            // check for Sales department
            if (value != null && SALES_DEPARTMENT_ID == value.intValue()) {
                // if the commission has not been set yet
                if (this.getCommissionPct() == null) {
                    // set commission to default
                    this.setCommissionPct(new Number(DEFAULT_COMMISSION));
                }
            } else {
                // clear commission for non Sales department
                this.setCommissionPct(null);
            }
        } catch (SQLException e) {
            // log the exception
            LOGGER.severe(e);
        }
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link Employees.
     */
    public RowIterator getEmployees() {
        return (RowIterator)getAttributeInternal(EMPLOYEES);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link Departmens.
     */
    public RowIterator getDepartmens() {
        return (RowIterator)getAttributeInternal(DEPARTMENS);
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
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].get(this);
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
        if ((index >= AttributesEnum.firstIndex()) &&
            (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index -
                AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
