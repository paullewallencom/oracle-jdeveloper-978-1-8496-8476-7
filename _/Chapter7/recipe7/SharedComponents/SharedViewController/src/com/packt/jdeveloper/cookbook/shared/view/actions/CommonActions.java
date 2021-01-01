package com.packt.jdeveloper.cookbook.shared.view.actions;


import com.packt.jdeveloper.cookbook.shared.bc.exceptions.messages.BundleUtils;
import com.packt.jdeveloper.cookbook.shared.view.util.ADFUtils;
import com.packt.jdeveloper.cookbook.shared.view.util.JSFUtils;

import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;


public class CommonActions {

    public static final class Operations {
        public static final String COMMIT = "Commit";
        public static final String ROLLBACK = "RollBack";
        public static final String INSERT = "CreateInsert";
        public static final String DELETE = "Remove";
    }

    /**
     * Common actionListener for all commit operations.
     *
     * @param actionEvent
     */
    public void commit(final ActionEvent actionEvent) {
        if (ADFUtils.hasChanges()) {
            // allow derived beans to handle before commit actions
            onBeforeCommit(actionEvent);
            // allow derived beans to handle commit actions
            onCommit(actionEvent);
            // allow derived beans to handle after commit actions
            onAfterCommit(actionEvent);
        } else {
            // display "No changes to commit" message
            JSFUtils.addFacesInformationMessage(BundleUtils.loadMessage("00002"));
        }
    }

    /**
     * Base class before commit implementation.
     *
     * @param actionEvent
     */
    protected void onBeforeCommit(final ActionEvent actionEvent) {
    }

    /**
     * Base class commit implementation.
     *
     * @param actionEvent
     */
    protected void onCommit(final ActionEvent actionEvent) {
        // execute commit
        ADFUtils.execOperation(Operations.COMMIT);
    }

    /**
     * Base class after commit implementation.
     *
     * @param actionEvent
     */
    protected void onAfterCommit(final ActionEvent actionEvent) {
        // display "Changes were committed successfully" message
        JSFUtils.addFacesInformationMessage(BundleUtils.loadMessage("00003"));
    }


    /**
     * Common actionListener for all rollback operations.
     *
     * @param actionEvent
     */
    public void rollback(final ActionEvent actionEvent) {
        if (ADFUtils.hasChanges()) {
            // allow derived beans to handle before rollback actions
            onBeforeRollback(actionEvent);
            // allow derived beans to handle rollback actions
            onRollback(actionEvent);
            // allow derived beans to handle after rollback actions
            onAfterRollback(actionEvent);
        } else {
            // display "No changes to rollback" message
            JSFUtils.addFacesInformationMessage(BundleUtils.loadMessage("00004"));
        }
    }

    /**
     * Base class before rollback implementation.
     *
     * @param actionEvent
     */
    protected void onBeforeRollback(final ActionEvent actionEvent) {
    }

    /**
     * Base class rollback implementation.
     *
     * @param actionEvent
     */
    protected void onRollback(final ActionEvent actionEvent) {
        ADFUtils.execOperation(Operations.ROLLBACK);
    }

    /**
     * Base class after rollback implementation.
     *
     * @param actionEvent
     */
    protected void onAfterRollback(final ActionEvent actionEvent) {
        // display "Changes were rolled back successfully" message
        JSFUtils.addFacesInformationMessage(BundleUtils.loadMessage("00005"));
    }

    /**
     * Common actionListener for all create operations.
     *
     * @param actionEvent
     */
    public void create(final ActionEvent actionEvent) {
        if (ADFUtils.hasChanges()) {
            onCreatePendingChanges(actionEvent);
        } else {
            onContinueCreate(actionEvent);
        }
    }

    /**
     * Base class before create implementation.
     *
     * @param actionEvent
     */
    protected void onBeforeCreate(final ActionEvent actionEvent) {
        // commit before creating a new record
        ADFUtils.execOperation(Operations.COMMIT);
    }

    /**
     * Base class create implementation.
     *
     * @param actionEvent
     */
    public void onCreate(final ActionEvent actionEvent) {
        ADFUtils.execOperation(Operations.INSERT);
    }

    /**
     * Base class after create implementation.
     *
     * @param actionEvent
     */
    protected void onAfterCreate(final ActionEvent actionEvent) {
    }

    /**
     * Handles changes before creating a new row.
     *
     * @param actionEvent
     */
    public void onCreatePendingChanges(final ActionEvent actionEvent) {
        ADFUtils.showPopup("CreatePendingChanges");
    }

    /**
     * Called from the "CreatePendingChanges" popup to continue
     * with the new record creation.
     *
     * @param actionEvent
     */
    public void onContinueCreate(final ActionEvent actionEvent) {
        CommonActions actions = getCommonActions();
        actions.onBeforeCreate(actionEvent);
        actions.onCreate(actionEvent);
        actions.onAfterCreate(actionEvent);
    }

    /**
     * Common actionListener for all delete operations.
     *
     * @param actionEvent
     */
    public void delete(final ActionEvent actionEvent) {
        onConfirmDelete(actionEvent);
    }

    /**
     * Base class before delete implementation.
     *
     * @param actionEvent
     */
    protected void onBeforeDelete(final ActionEvent actionEvent) {
    }

    /**
     * Base class delete implementation.
     *
     * @param actionEvent
     */
    public void onDelete(final ActionEvent actionEvent) {
        ADFUtils.execOperation(Operations.DELETE);
    }

    /**
     * Base class after delete implementation.
     *
     * @param actionEvent
     */
    protected void onAfterDelete(final ActionEvent actionEvent) {
        // commit before creating a new record
        ADFUtils.execOperation(Operations.COMMIT);
    }

    /**
     * Handles changes before deleting a row.
     *
     * @param actionEvent
     */
    public void onConfirmDelete(final ActionEvent actionEvent) {
        ADFUtils.showPopup("DeleteConfirmation");
    }

    /**
     * Called from the "DeleteConfirmation" popup to continue
     * with the record deletion.
     *
     * @param actionEvent
     */
    public void onContinueDelete(final ActionEvent actionEvent) {
        CommonActions actions = getCommonActions();
        actions.onBeforeDelete(actionEvent);
        actions.onDelete(actionEvent);
        actions.onAfterDelete(actionEvent);
    }
    
    /**
     * Helper to get the CommonActions for subclassed beans.
     * 
     * @return the CommonActions bean.
     */
    private CommonActions getCommonActions() {
        CommonActions actions = (CommonActions)JSFUtils.getExpressionObjectReference("#{" + getManagedBeanName() + "}");
        if (actions == null) {
            actions = this;
        }
        return actions;
    }
    
    /**
     * Helper to get the managed bean name from the page name.
     * 
     * @return the managed bean name.
     */
    private String getManagedBeanName() {
        return getPageId().replace("/", "").replace(".jspx", "");
    }
    
    /**
     * Helper to get the page name.
     * 
     * @return the page name
     */
    public String getPageId() {
        ControllerContext ctx = ControllerContext.getInstance();
        return ctx.getCurrentViewPort().getViewId().substring(ctx.getCurrentViewPort().getViewId().lastIndexOf("/"));
    }
    
}
