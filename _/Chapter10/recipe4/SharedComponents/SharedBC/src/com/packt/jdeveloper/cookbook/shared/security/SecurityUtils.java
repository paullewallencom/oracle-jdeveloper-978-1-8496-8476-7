package com.packt.jdeveloper.cookbook.shared.security;

import oracle.adf.share.logging.ADFLogger;

import oracle.security.idm.IdentityStore;
import oracle.security.idm.SearchParameters;
import oracle.security.idm.SearchResponse;
import oracle.security.idm.SimpleSearchFilter;
import oracle.security.idm.User;
import oracle.security.idm.UserProfile;
import oracle.security.jps.JpsContext;
import oracle.security.jps.JpsContextFactory;
import oracle.security.jps.JpsException;
import oracle.security.jps.service.idstore.IdentityStoreService;


public class SecurityUtils {

    private static ADFLogger LOGGER = ADFLogger.createADFLogger(SecurityUtils.class);

    /**
     * Recipe: Using OPSS to retrieve the authenticated user's profile from the identity store.
     * 
     * Returns the specific user's profile from the identity store.
     *
     * @param username, the user name.
     * @return the user profile.
     */
    public static UserProfile getUserIdentityStoreProfile(String username) {
        UserProfile userProfile = null;
        try {
            // get the identity store
            IdentityStore idStore = getIdentityStore();
            // create a search filter based on the specific user name
            SimpleSearchFilter filter =
                idStore.getSimpleSearchFilter(UserProfile.NAME,
                                              SimpleSearchFilter.TYPE_EQUAL,
                                              username);
            SearchParameters sp =
                new SearchParameters(filter, SearchParameters.SEARCH_USERS_ONLY);
            // search identity store
            SearchResponse response = idStore.search(sp);
            // check for search results
            if (response.hasNext()) {
                User user = (User)response.next();
                if (user != null) {
                    // retrieve the user profile
                    userProfile = user.getUserProfile();
                }
            }
        } catch (Exception e) {
            LOGGER.severe(e);
        }
        
        // return the user profile
        return userProfile;
    }

    /**
     * Recipe: Using OPSS to retrieve the authenticated user's profile from the identity store.
     *
     * Returns the identity store.
     * 
     * @return IdentityStore, the identity store.
     * @throws JpsException
     */
    private static IdentityStore getIdentityStore() throws JpsException {
        // get the JPS context
        JpsContext jpsCtx = JpsContextFactory.getContextFactory().getContext();
        // return the identity store
        IdentityStoreService service =
            jpsCtx.getServiceInstance(IdentityStoreService.class);
        return service.getIdmStore();
    }
}
