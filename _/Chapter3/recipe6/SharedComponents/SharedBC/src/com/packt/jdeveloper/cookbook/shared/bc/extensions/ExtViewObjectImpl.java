package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.AttributeDef;
import oracle.jbo.Row;
import oracle.jbo.server.ViewAttributeDefImpl;
import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class ExtViewObjectImpl extends ViewObjectImpl {

    private static final String NEW_ROW_AT_END = "NewRowAtEnd";

    public ExtViewObjectImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public ExtViewObjectImpl() {
        super();
    }

    /**
     * Helper to set/unset an attribute's queriable property.
     *
     * @param attribute the attribute index
     * @param condition the condition (true/false)
     */
    protected void setQueriable(int attribute, boolean condition) {
        // get the attribute definition
        AttributeDef def = getAttributeDef(attribute);
        // set/unset only if needed
        if (def != null && def.isQueriable() != condition) {
            // set/unset queriable
            ViewAttributeDefImpl attributeDef = (ViewAttributeDefImpl)def;
            attributeDef.setQueriable(condition);
        }
    }

    /**
     * Overriden insertRow() to conditionally insert new rows at the end of the
     * RowSet based on the custom property NEW_ROW_AT_END.
     *
     * @param row, the Row to insert.
     */
    public void insertRow(Row row) {
        // check for overriden behavior based on custom property
        if ("true".equalsIgnoreCase((String)this.getProperty(NEW_ROW_AT_END))) {
            // get the last row in the RowSet
            Row lastRow = this.last();
            if (lastRow != null) {
                // get index of last row
                int lastRowIdx = this.getRangeIndexOf(lastRow);
                // insert row after the last row
                this.insertRowAtRangeIndex(lastRowIdx+1, row);
                // set inserted row as the current row
                this.setCurrentRow(row);
            } else {
                super.insertRow(row);
            }
        } else {
            // default behavior: insert at current RowSet slot
            super.insertRow(row);
        }
    }
}
