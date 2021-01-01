package com.packt.jdeveloper.cookbook.hr.components.model.view;

import com.packt.jdeveloper.cookbook.shared.bc.extensions.ExtViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat May 28 21:10:32 EEST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeeCountRowImpl extends ExtViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        EmployeeCount {
            public Object get(EmployeeCountRowImpl obj) {
                return obj.getEmployeeCount();
            }

            public void put(EmployeeCountRowImpl obj, Object value) {
                obj.setEmployeeCount((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(EmployeeCountRowImpl object);

        public abstract void put(EmployeeCountRowImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }

    public static final int EMPLOYEECOUNT = AttributesEnum.EmployeeCount.index();

    /**
     * This is the default constructor (do not remove).
     */
    public EmployeeCountRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute EmployeeCount.
     * @return the EmployeeCount
     */
    public Number getEmployeeCount() {
        return (Number) getAttributeInternal(EMPLOYEECOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute EmployeeCount.
     * @param value value to set the  EmployeeCount
     */
    public void setEmployeeCount(Number value) {
        setAttributeInternal(EMPLOYEECOUNT, value);
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
}
