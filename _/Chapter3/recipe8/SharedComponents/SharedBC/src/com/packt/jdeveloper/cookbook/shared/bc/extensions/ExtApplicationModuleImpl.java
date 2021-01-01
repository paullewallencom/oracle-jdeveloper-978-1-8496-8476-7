package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Session;
import oracle.jbo.server.ApplicationModuleImpl;

public class ExtApplicationModuleImpl extends ApplicationModuleImpl {

    // create an ADFLogger
    private static final ADFLogger LOGGER =
        ADFLogger.createADFLogger(ExtApplicationModuleImpl.class);

    public ExtApplicationModuleImpl() {
        super();
        // log a trace
        LOGGER.info("ExtApplicationModuleImpl was constructed");
    }

    protected void prepareSession(Session session) {
        // framework processing
        super.prepareSession(session);
        // do not clear the cache after a rollback
        getDBTransaction().setClearCacheOnRollback(false);
    }
}
