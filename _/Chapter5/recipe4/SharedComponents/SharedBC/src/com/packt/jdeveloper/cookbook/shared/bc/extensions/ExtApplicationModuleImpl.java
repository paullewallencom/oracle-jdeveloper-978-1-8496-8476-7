package com.packt.jdeveloper.cookbook.shared.bc.extensions;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Session;
import oracle.jbo.server.ApplicationModuleImpl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It is should be overridden by the derived application module implementation
     * when there is a need to passivate application module specific attributes.
     * It should return a list of identifiers to passivate.
     *
     * @return String[] - a String array of identifiers to be passivated.
     */
    protected String[] onStartPassivation() {
        // default implementation: no passivation ids are defined
        return new String[] { };
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It should be overriden by the derived application module implementation
     * when there is a need to passivate application module specific attributes in
     * order to provide the passivation data for the specific passivation identifier.
     *
     * @param passivationId The passivation id for which the overriden application
     * module should provide some data to passivate.
     *
     * @return String - The passivation data.
     */
    protected String onPassivate(String passivationId) {
        // default implementation: passivates nothing
        return null;
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It should be overriden by the derived application module implementation
     * when there is a need to passivate application module specific attributes in
     * order to signal the end of the passivation process.
     */
    protected void onEndPassivation() {
        // default implementation: does nothing
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It is should be overridden by the derived application module implementation
     * when there is a need to activate application module specific attributes. Should
     * return a String array of identifiers expected to be activated.
     *
     * @return String[] - a String array of identifiers to be activated.
     */
    protected String[] onStartActivation() {
        // default implementation: no activation ids are defined
        return new String[] { };
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It should be overriden by the derived application module implementation
     * when there is a need to activate application module specific attributes. It
     * supplies the data being activated for the specific activation identifier.
     *
     * @param activationId The activation identifier.
     * @param activationData The activation data.
     */
    protected void onActivate(String activationId, String activationData) {
        // default implementation: activates nothing
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * It should be overriden by the derived application module implementation
     * when there is a need to activate application module specific attributes in
     * order to signal the end of the activation process.
     */
    protected void onEndActivation() {
        // default implementation: does nothing
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * Overridden framework method to passivate custom XML elements
     * into the pending state snapshot document.
     *
     * @param document
     * @param element
     */
    protected void passivateState(Document document, Element element) {

        LOGGER.info("ExtApplicationModuleImpl:passivateState was called");

        // framework processing
        super.passivateState(document, element);

        // begin custom data passivation: returns a list of the custom data
        // passivation identifiers
        String[] passivationIds = onStartPassivation();
        // process all passivation identifiers
        for (String passivationId : passivationIds) {
            // check for valid identifier
            if (passivationId != null && passivationId.trim().length() > 0) {
                // passivate custom data: returns the passivation data
                String passivationValue = onPassivate(passivationId);
                // check for valid passivation data
                if (passivationValue != null && passivationValue.length() > 0) {
                    // create a new text node in the passivation XML
                    Node node = document.createElement(passivationId);
                    Node cNode = document.createTextNode(passivationValue);
                    node.appendChild(cNode);
                    // add the passivation node to the parent element
                    element.appendChild(node);
                }
            }
        }

        // inform end of custom data passivation
        onEndPassivation();
    }

    /**
     * Recipe: A passivation/activation framework for custom session-specific data.
     *
     * Overridden framework method to activate custom XML elements
     * into the pending state snapshot document.
     */
    protected void activateState(Element element) {

        LOGGER.info("ExtApplicationModuleImpl:activateState was called");

        // framework processing
        super.activateState(element);

        // check for element to activate
        if (element != null) {
            // begin custom data activation: returns a list of the custom data
            // activation identifiers
            String[] activationIds = onStartActivation();
            // process all activation identifiers
            for (String activationId : activationIds) {
                // check for valid identifier
                if (activationId != null && activationId.trim().length() > 0) {
                    // get nodes from XML for the specific activation identifier
                    NodeList nl = element.getElementsByTagName(activationId);
                    // if it was found in the activation data
                    if (nl != null) {
                        // activate each node
                        for (int n = 0, length = nl.getLength(); n < length; n++) {
                            Node child = nl.item(n).getFirstChild();
                            if (child != null) {
                                // do the actual custom data activation
                                onActivate(activationId, child.getNodeValue().toString());
                                break;
                            }
                        }
                    }
                }
            }

            // inform end of custom data activation
            onEndActivation();
        }
    }

}
