package com.packt.jdeveloper.cookbook.shared.bc.exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;

public class ExtJboException extends JboException {

    private static final String ERRORS_BUNDLE =
        "com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.ErrorMessages";
    private static final String PARAMETERS_BUNDLE =
        "com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.ErrorParams";
    private static final String MESSAGE_PREFIX = "message.";
    private static final String PARAMETER_PREFIX = "parameter.";

    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ExtJboException.class);

    /**
     * For testing purposes; remove or comment if not needed
     * @param args
     */
    public static void main(String[] args) {
        // throw a custom exception with error code "00001" and two parameters
        throw new ExtJboException("00001",
                                  new String[] { "FirstParameter", "SecondParameter" });
    }

    /**
     * Constructor to create an exception using a standard error code and
     * error message parameters
     * @param errorCode, the error message code
     * @param errorParameters, the error message parameters
     */
    public ExtJboException(final String errorCode,
                           final Object[] errorParameters) {
        super(ResourceBundle.class, errorCode, errorParameters);
    }

    /**
     * Constructor to create an exception using a standard error code.
     * @param errorCode, the error message code
     */
    public ExtJboException(final String errorCode) {
        super(ResourceBundle.class, errorCode, null);
    }

    /**
     * Construct using exception.
     * @param e
     */
    public ExtJboException(Exception e) {
      super(e);
    }
    
    /**
     * Constructs the exception message.
     * @return, the exception message
     */
    @Override
    public String getMessage() {
        // default message
        String errorMessage = "";
        try {
            // get access to the error messages bundle
            final ResourceBundle messagesBundle =
                ResourceBundle.getBundle(ERRORS_BUNDLE, Locale.getDefault());
            // construct the error message
            errorMessage =
                    this.getErrorCode() + " - " + messagesBundle.getString(MESSAGE_PREFIX +
                                                                           this.getErrorCode());

            // get access to the error message parameters bundle
            final ResourceBundle parametersBundle =
                ResourceBundle.getBundle(PARAMETERS_BUNDLE,
                                         Locale.getDefault());
            // loop for all parameters
            for (int i = 0; i < this.getErrorParameters().length; i++) {
                // get parameter value
                final String parameterValue =
                    parametersBundle.getString(PARAMETER_PREFIX +
                                               (String)this.getErrorParameters()[i]);
                // replace parameter placeholder in the error message string
                errorMessage =
                        errorMessage.replaceAll("\\{" + (i + 1) + "}", parameterValue);
            }
        } catch (Exception e) {
            // log the exception
            LOGGER.warning(e);
        }

        return errorMessage;
    }
}
