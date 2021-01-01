package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Session;
import oracle.jbo.server.ApplicationModuleImpl;

public class ExtApplicationModuleImpl extends ApplicationModuleImpl implements ExtApplicationModule {

    // create an ADFLogger
    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(ExtApplicationModuleImpl.class);

    private static int AUTHORITY_LEVEL_MINIMAL = 1;
    private static int AUTHORITY_LEVEL_NORMAL = 2;
    private static int AUTHORITY_LEVEL_HIGH = 3;

    public ExtApplicationModuleImpl() {
        super();
        // log a trace
        LOGGER.info("ExtApplicationModuleImpl was constructed");
    }

    /**
     * Recipe: Restoring the current row after a transaction rollback.
     *
     * @param session, the oracle.jbo.Session.
     */
    protected void prepareSession(Session session) {
        // framework processing
        super.prepareSession(session);
        // do not clear the cache after a rollback
        getDBTransaction().setClearCacheOnRollback(false);
    }

    /**
     * Recipe: Overriding bindParametersForCollection() to set a View object bind variable.
     *
     * Used by derived Application Modules to return some custom data.
     *
     * @param key, some key to locate the custom data
     * @return
     */
    public Object getCustomData(String key) {
        // base class returns no custom data
        return null;
    }

    /**
     * Recipe: Creating and using generic extension interfaces.
     *
     * @return, the user language code.
     */
    public int getUserAuthorityLevel() {
        // return some user authority level, based on
        // the user's name
        return ("anonymous".equalsIgnoreCase(this.getUserPrincipalName())) ?
               AUTHORITY_LEVEL_MINIMAL : AUTHORITY_LEVEL_NORMAL;
    }

}
