package com.packt.jdeveloper.cookbook.shared.bc.database;


import com.packt.jdeveloper.cookbook.shared.bc.exceptions.ExtJboException;

import java.sql.CallableStatement;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import oracle.jbo.JboException;
import oracle.jbo.server.DBTransaction;


public class SQLProcedure {
    // the transaction associated with procedure call
    private DBTransaction transaction;
    // a list of procedure parameters
    private List<SQLParameter> parameters = new ArrayList<SQLParameter>();
    // the callable statement
    private CallableStatement statement = null;
    // the statement string
    private String statementString = null;
    // the procedure parameter count
    private int parameterCount = 0;

    /**
     * The stored procedure/function wrapper.
     * @param procedure, the stored procedure/function
     * @param transaction, the oracle.jbo.server.DBTransaction associated with the
     * Application Module.
     */
    public SQLProcedure(final String procedure,
                        final DBTransaction transaction) {
        this.statementString = procedure;
        this.transaction = transaction;
    }

    /**
     * Sets an IN stored procedure/function parameter.
     * @param data, the IN data
     */
    public void setIN(final Object data) {
        ++parameterCount;
        parameters.add(new SQLParameter(parameterCount, SQLParameter.TYPE.IN,
                                        data, 0));
    }

    /**
     * Sets an OUT stored procedure/function parameter.
     * @param returnType, the return data type
     */
    public void setOUT(final int returnType) {
        ++parameterCount;
        parameters.add(new SQLParameter(parameterCount, SQLParameter.TYPE.OUT,
                                        null, returnType));
    }

    /**
     * Sets an IN OUT stored procedure/function parameter.
     * @param data the IN OUT data
     * @param returnType, the return data type
     */
    public void setINOUT(final Object data, final int returnType) {
        ++parameterCount;
        parameters.add(new SQLParameter(parameterCount,
                                        SQLParameter.TYPE.INOUT, data,
                                        returnType));
    }

    /**
     * Sets the stored function return parameter.
     * @param returnType, the return data type
     */
    public void setRETURN(final int returnType) {
        // do not allow multiple RETURNs
        SQLParameter returnParameter = hasRETURN();
        if (returnParameter != null) {
            // update RETURN parameter type
            returnParameter.setReturnType(returnType);
        } else {
            ++parameterCount;
            
            // check RETURN parameter count; should always be the 1
            if (parameterCount != 1) {
                shiftParameters();
            }

            // add RETURN parameter
            parameters.add(new SQLParameter(1, SQLParameter.TYPE.RETURN, null,
                                            returnType));
        }
    }

    /**
     * Shifts the parameter order;
     */
    private void shiftParameters() {
        for (SQLParameter parameter : parameters) {
            parameter.setParameterNumber(parameter.getParameterNumber() + 1);
        }
    }

    /**
     * Iterates parameters looking for a RETURN parameter type.
     * @return the RETURN parameter if any or null
     */
    private SQLParameter hasRETURN() {
        SQLParameter returnParameter = null;
        for (SQLParameter parameter : parameters) {
            if (SQLParameter.TYPE.RETURN == parameter.getType()) {
                returnParameter = parameter;
                break;
            }
        }
        return returnParameter;
    }

    /**
     * Creates the callable statement after first constructing the stored
     * procedure/function statement.
     */
    private void createStatementFromParameters() {
        String paramString = "";
        for (SQLParameter parameter : parameters) {
            switch (parameter.getType()) {
            case SQLParameter.TYPE.IN:
            case SQLParameter.TYPE.OUT:
            case SQLParameter.TYPE.INOUT:
                // add parameter placeholder in statement
                paramString += (paramString.length() > 0) ? ",?" : "?";
                break;
            case SQLParameter.TYPE.RETURN:
                // add return placeholder in statement
                statementString = "?:=" + statementString;
                break;
            }
        }

        // add complete parameter string to statement
        if (paramString.length() > 0) {
            statementString += "(" + paramString + ")";
        }

        // wrap statement with BEGIN/END
        statementString = "BEGIN " + statementString + "; END;";

        // create CallableStatement from statement
        statement = transaction.createCallableStatement(statementString, 0);
    }

    /**
     * Adds the input values to the callable statement.
     * @throws SQLException
     */
    private void addParameterValues() throws SQLException {
        for (SQLParameter parameter : parameters) {
            switch (parameter.getType()) {
            case SQLParameter.TYPE.IN:
                statement.setObject(parameter.getParameterNumber(),
                                    parameter.getInputData());
                break;
            case SQLParameter.TYPE.OUT:
                statement.registerOutParameter(parameter.getParameterNumber(),
                                               parameter.getReturnType());
                break;
            case SQLParameter.TYPE.INOUT:
                statement.setObject(parameter.getParameterNumber(),
                                    parameter.getInputData());
                statement.registerOutParameter(parameter.getParameterNumber(),
                                               parameter.getReturnType());
                break;
            case SQLParameter.TYPE.RETURN:
                statement.registerOutParameter(parameter.getParameterNumber(),
                                               parameter.getReturnType());
                break;
            }
        }
    }

    /**
     * Retrieves the data returned by the stored procedire/function.
     * @throws SQLException
     */
    private void retrieveData() throws SQLException {
        for (SQLParameter parameter : parameters) {
            if (SQLParameter.TYPE.INOUT == parameter.getType() ||
                SQLParameter.TYPE.OUT == parameter.getType() ||
                SQLParameter.TYPE.RETURN == parameter.getType()) {
                parameter.setOutputData(statement.getObject(parameter.getParameterNumber()));
            }
        }
    }

    /**
     * Executes the stored procedure/function.
     *
     */
    public void execute() {
        try {
            // create statement for parameters
            createStatementFromParameters();
            // add parameters to statement
            addParameterValues();
            // execute callable statement
            statement.execute();
            // retrieve returned data
            retrieveData();
            // close callable statement
            statement.close();

        } catch (SQLException e) {
            throw new ExtJboException(e);
        }
    }


    /**
     * Gets some output data.
     *
     * @param nParameter, the parameter number
     * @return the output data
     */
    public Object getOUT(final int nParameter) {
        Object outputData = null;
        for (SQLParameter parameter : parameters) {
            if (parameter.getParameterNumber() == nParameter &&
                (parameter.getType() == SQLParameter.TYPE.OUT ||
                 parameter.getType() == SQLParameter.TYPE.INOUT)) {
                outputData = parameter.getOutputData();
            }
        }
        return outputData;
    }

    /**
     * Gets the stored function return data
     * @return the return data
     */
    public Object getRETURN() {
        Object returnedData = null;
        for (SQLParameter parameter : parameters) {
            if (parameter.getType() == SQLParameter.TYPE.RETURN) {
                returnedData = parameter.getOutputData();
            }
        }
        return returnedData;
    }

}

