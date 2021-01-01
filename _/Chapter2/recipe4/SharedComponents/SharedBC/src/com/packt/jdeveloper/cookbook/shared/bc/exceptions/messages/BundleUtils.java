package com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages;

import com.packt.jdeveloper.cookbook.shared.bc.exceptions.ExtJboException;

import java.util.Locale;
import java.util.ResourceBundle;

import oracle.adf.share.logging.ADFLogger;

public class BundleUtils {

    private static final String ERRORS_BUNDLE =
        "com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.ErrorMessages";
    private static final String PARAMETERS_BUNDLE =
        "com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.ErrorParams";
    private static final String MESSAGE_PREFIX = "message.";
    private static final String PARAMETER_PREFIX = "parameter.";

    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ExtJboException.class);

    public static String loadMessage(final String code) {
        return loadMessage(code, new Object[] { });
    }

    public static String loadMessage(final String code,
                                     final Object[] parameters) {
        // default message
        String errorMessage = "";
        try {
            // get access to the error messages bundle
            final ResourceBundle messagesBundle =
                ResourceBundle.getBundle(ERRORS_BUNDLE, Locale.getDefault());
            // construct the error message
            errorMessage =
                    code + " - " + messagesBundle.getString(MESSAGE_PREFIX +
                                                            code);

            // get access to the error message parameters bundle
            final ResourceBundle parametersBundle =
                ResourceBundle.getBundle(PARAMETERS_BUNDLE,
                                         Locale.getDefault());
            // loop for all parameters
            for (int i = 0; i < parameters.length; i++) {
                // get parameter value
                final String parameterValue =
                    parametersBundle.getString(PARAMETER_PREFIX +
                                               (String)parameters[i]);
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
