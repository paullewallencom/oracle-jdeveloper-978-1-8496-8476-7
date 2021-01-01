package com.packt.jdeveloper.cookbook.shared.bc.exceptions;


import com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.BundleUtils;

import java.util.ResourceBundle;

import oracle.jbo.AttrValException;


/**
 * Recipe: Overriding attribute validation exceptions.
 */
public class ExtAttrValException extends AttrValException {
    /**
     * Constructor to create an exception using a standard error code and
     * error message parameters
     * @param errorCode, the error message code
     * @param errorParameters, the error message parameters
     */
    public ExtAttrValException(String errorCode, Object[] errorParameters) {
        super(ResourceBundle.class, errorCode, errorParameters);
    }

    /**
     * Constructor to create an exception using a standard error code.
     * @param errorCode, the error message code
     */
    public ExtAttrValException(final String errorCode) {
        super(ResourceBundle.class, errorCode, null);
    }

    /**
     * Constructs the exception message.
     * @return, the exception message
     */
    @Override
    public String getMessage() {
        return BundleUtils.loadMessage(this.getErrorCode(),
                                       this.getErrorParameters());
    }
}
