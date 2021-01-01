package com.packt.jdeveloper.cookbook.shared.bc.database;

import java.sql.Types;

/**
 * Defines a parameter to be used by the SQLProcedure class to call a database
 * stored procedure or function.
 */
public class SQLParameter {

    // the type of parameters
    public static class TYPE {
        public static final int NULL = -1;
        public static final int IN = 1;
        public static final int OUT = 2;
        public static final int INOUT = 3;
        public static final int RETURN = 4;
    }

    // the parameter type (any of IN, OUT, INOUT, RETURN, NULL)
    private int type = TYPE.NULL;
    // the parameter number in the callable staement
    private int parameterNumber = -1;
    // the data associated with an IN, INOUT parameter
    private Object inputData;
    // the data associated with an OUT, INOUT, RETURN parameter
    private Object outputData;
    // the parameter return type
    private int returnType = Types.NULL;

    /**
     * Constructs a parameter.
     * @param parameterNumber, the parameter number
     * @param type, the parameter type
     * @param inputData, the parameter input data (for IN, INOUT parameters)
     * @param returnType, the returned parameter SQL type
     */
    public SQLParameter(final int parameterNumber, final int type,
                        final Object inputData, final int returnType) {
        this.parameterNumber = parameterNumber;
        this.type = type;
        this.inputData = inputData;
        this.returnType = returnType;
    }

    public void setInputData(final Object inputData) {
        this.inputData = inputData;
    }

    public Object getInputData() {
        return inputData;
    }

    public void setOutputData(final Object outputData) {
        this.outputData = outputData;
    }

    public Object getOutputData() {
        return outputData;
    }

    public void setParameterNumber(final int parameterNumber) {
        this.parameterNumber = parameterNumber;
    }

    public int getParameterNumber() {
        return parameterNumber;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setReturnType(final int returnType) {
        this.returnType = returnType;
    }

    public int getReturnType() {
        return returnType;
    }

}
