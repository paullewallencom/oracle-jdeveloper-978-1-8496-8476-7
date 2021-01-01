package com.packt.jdeveloper.cookbook.shared.bc.logging;

import oracle.adf.share.logging.ADFLogger;

public class GroovyLogger {
    private static ADFLogger LOGGER = ADFLogger.createADFLogger(GroovyLogger.class);

    public GroovyLogger() {
        super();
    }

    /**
     * Recipe: Logging Groovy expressions.
     * Example: com.packt.jdeveloper.cookbook.shared.bc.logging.GroovyLogger.log("adf.context.securityContext.userName", adf.context.securityContext.userName)
     * @param <T>
     * @param groovyExpression, the Groovy expression
     * @param data, the Groovy data
     * @return
     */
    public static <T> T log(String groovyExpression, T data) {
        LOGGER.info("GroovyLogger ==> Expression: " + groovyExpression + ", Data: " + data);
        return data;
    }
}
