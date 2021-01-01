package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.AttributeDef;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.server.ViewAttributeDefImpl;
import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.server.ViewRowImpl;

public class ExtViewObjectImpl extends ViewObjectImpl {

    private static final String NEW_ROW_AT_END = "NewRowAtEnd";
    private static final String ROW_COUNT_LIMIT = "RowCountLimit";

    private Key currentRowKeyBeforeRollback = null;
    private int rangeStartBeforeRollback = 0;
    private int rangePosOfCurrentRowBeforeRollback = 0;

    public ExtViewObjectImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public ExtViewObjectImpl() {
        super();
    }

    /**
     * Recipe: Setting a View object attribute's Queryable property programmatically.
     * 
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
     * Recipe: Conditionally inserting new rows at the end of the RowSet.
     * 
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

    /**
     * Recipe: Using findAndSetCurrentRowByKey() to set the View object currency.
     * Recipe: Restoring the current row after a transaction rollback.
     * 
     * Overriden in order to allow managing read-only View objects by key.
     */
    protected void create() {
        super.create();
        // allow read-only View objects to use findByKey() methods
        this.setManageRowsByKey(true);
    }

    /**
     * Recipe: Using findAndSetCurrentRowByKey() to set the View object currency.
     * 
     * Helper to refresh the view.
     */
    public void refreshView() {
        Key curRowKey = null;
        int rangePosOfCurRow = -1;
        int rangeStart = -1;

        // get and save the current row
        Row currentRow = getCurrentRow();
        // do this only if we have a current row
        if (currentRow != null) {
            // get the row information
            curRowKey = currentRow.getKey();
            rangePosOfCurRow = getRangeIndexOf(currentRow);
            rangeStart = getRangeStart();
        }

        // execute the View object query
        executeQuery();

        // if we have a current row, restore it
        if (currentRow != null) {
            setRangeStart(rangeStart);
            findAndSetCurrentRowByKey(curRowKey, rangePosOfCurRow);
        }
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     * 
     * Overriden in order to restore current row after a rollback operation.
     *
     * @param TransactionEvent, the transaction event.
     */
    public void beforeRollback(TransactionEvent TransactionEvent) {
        if (isExecuted()) {
            // get the current row
            ViewRowImpl currentRow = (ViewRowImpl)getCurrentRow();
            if (currentRow != null) {
                // save the current row's key
                currentRowKeyBeforeRollback = currentRow.getKey();
                // save range start
                rangeStartBeforeRollback = getRangeStart();
                // get index of current row in range
                rangePosOfCurrentRowBeforeRollback = getRangeIndexOf(currentRow);
            }
        }
        super.beforeRollback(TransactionEvent);
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     * 
     * Overriden in order to restore current row after a rollback operation.
     *
     * @param TransactionEvent, the transaction event.
     */
    public void afterRollback(TransactionEvent TransactionEvent) {
        super.afterRollback(TransactionEvent);
        // check for current row key to restore
        if (currentRowKeyBeforeRollback != null) {
            // execute View object's query
            executeQuery();
            // set start range
            setRangeStart(rangeStartBeforeRollback);
            // set current row in range
            findAndSetCurrentRowByKey(currentRowKeyBeforeRollback, rangePosOfCurrentRowBeforeRollback);
        }

        // reset
        currentRowKeyBeforeRollback = null;
    }

    /**
     * Recipe: Dynamically changing the View object's query WHERE clause.
     * 
     * Overriden in order to alter the query's WHERE clause.
     * 
     * @param sqlBuffer, the query SQL buffer
     * @param noUserParams, the number of bind variables in the query
     * @return
     */
    protected boolean buildWhereClause(StringBuffer sqlBuffer, int noUserParams) {

        // framework processing
        boolean appended = super.buildWhereClause(sqlBuffer, noUserParams);

        // check for a row count limit
        String rowCountLimit = (String)this.getProperty(ROW_COUNT_LIMIT);
        // if a row count limit exists, limit the query
        if (rowCountLimit != null) {
            // check to see if a WHERE clause was appended; if not, we will append it
            if (!appended) {
                // append WHERE clause
                sqlBuffer.append(" WHERE ");
                // indicate that a where clause was added
                appended = true;
            }
            // a WHERE clause was appended by the framework; just ammend it
            else {
                sqlBuffer.append(" AND ");
            }

            // add ROWNUM limit based on the pre-defined custom property
            sqlBuffer.append("(ROWNUM <= " + rowCountLimit + ")");
        }

        // a true/false indicator whether a WHERE clause was appended 
        // is returned to the framework
        return appended;
    }

}
