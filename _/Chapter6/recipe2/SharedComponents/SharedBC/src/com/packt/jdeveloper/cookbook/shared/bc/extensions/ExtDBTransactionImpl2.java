package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.server.DBTransactionImpl2;

public class ExtDBTransactionImpl2 extends DBTransactionImpl2 {

    // create an ADFLogger
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ExtDBTransactionImpl2.class);

    /**
     * Recipe: Using a custom database transaction.
     *
     */
    public void commit() {
        // log a trace
        LOGGER.info("Commit was called on the transaction");
        super.commit();
    }

    /**
     * Recipe: Using a custom database transaction.
     *
     */
    public void rollback() {
        // log a trace
        LOGGER.info("Rollback was called on the transaction");
        super.rollback();
    }
}
