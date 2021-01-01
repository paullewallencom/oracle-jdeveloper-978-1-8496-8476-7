package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.jbo.server.DBTransactionImpl2;
import oracle.jbo.server.DatabaseTransactionFactory;

public class ExtDatabaseTransactionFactory extends DatabaseTransactionFactory {

    /**
     * Recipe: Using a custom database transaction.
     *
     * @return, the custom transaction framework extension implementation
     */
    public DBTransactionImpl2 create() {
        // return custom transaction framework extension implementation
        return new ExtDBTransactionImpl2();
    }
}
