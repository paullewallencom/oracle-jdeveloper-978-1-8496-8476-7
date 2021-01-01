package com.packt.jdeveloper.cookbook.shared.view.taskflows;

import java.util.Map;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.controller.TaskFlowTrainModel;
import oracle.adf.controller.metadata.MetadataService;
import oracle.adf.controller.metadata.model.NamedParameter;
import oracle.adf.controller.metadata.model.TaskFlowDefinition;
import oracle.adf.controller.metadata.model.TaskFlowInputParameter;
import oracle.adf.share.logging.ADFLogger;


/**
 * Recipe: Retrieving the task flow definition programmatically using MetadataService.
 */
public abstract class TaskFlowBaseBean {

    public static ADFLogger LOGGER = ADFLogger.createADFLogger(TaskFlowBaseBean.class);

    public TaskFlowBaseBean() {
        super();
    }

    /**
     * Task flow initializer method.
     */
    public void initialize() {
        // get task flow parameters
        Map<String, TaskFlowInputParameter> taskFlowParameters = getTaskFlowParameters();
        // log parameters
        logParameters(taskFlowParameters);
    }

    /**
     * Task flow finalizer method.
     */
    public void finalize() {
        // get task flow return values
        Map<String, NamedParameter> taskFlowReturnValues = getReturnValues();
        // log return values
        logParameters(taskFlowReturnValues);
    }

    /**
     * Helper, returns the TaskFlowId from the current ViewPort.
     *
     * @return the current ViewPort TaskFlowId.
     */
    protected TaskFlowId getTaskFlowId() {
        // get task flow context from the current view port
        TaskFlowContext taskFlowContext = ControllerContext.getInstance().getCurrentViewPort().getTaskFlowContext();
        // return the task flow id
        return taskFlowContext.getTaskFlowId();
    }

    /**
     * Helper, returns the current task flow definition.
     *
     * @return the current TaskFlowDefinition.
     */
    protected TaskFlowDefinition getTaskFlowDefinition() {
        // use MetadataService to return the task flow definition based on the task flow id
        return MetadataService.getInstance().getTaskFlowDefinition(getTaskFlowId());
    }

    /**
     * Helper, returns the current task flow parameters.
     *
     * @return Map of current task flow's prameters.
     */
    protected Map<String, TaskFlowInputParameter> getTaskFlowParameters() {
        // get task flow definition
        TaskFlowDefinition taskFlowDefinition = getTaskFlowDefinition();
        // return the task flow input parameters
        return taskFlowDefinition.getInputParameters();
    }

    /**
     * Helper, returns the current task flow return values.
     *
     * @return Map of the current task flow's return values.
     */
    protected Map<String, NamedParameter> getReturnValues() {
        // get task flow definition
        TaskFlowDefinition taskFlowDefinition = getTaskFlowDefinition();
        // return the task flow return values
        return taskFlowDefinition.getReturnValues();
    }

    /**
     * Helper, logs task flow parameters and return values.
     * 
     * @param taskFlowParameters, the task flow parameters and return values.
     */
    protected void logParameters(Map taskFlowParameters) {
//#{controllerContext.currentViewPort.taskFlowContext.trainModel}
        TaskFlowTrainModel flowTrainModel = ControllerContext.getInstance().getCurrentViewPort().getTaskFlowContext().getTaskFlowTrainModel();
    }

}
