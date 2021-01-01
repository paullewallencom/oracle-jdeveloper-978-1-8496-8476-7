package com.packt.jdeveloper.cookbook.shared.workmanager;


import commonj.work.Work;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.share.logging.ADFLogger;


public abstract class ExtWork implements Work {

    private final static ADFLogger LOGGER = ADFLogger.createADFLogger(ExtWork.class);
    // parameters list
    protected List<Object> parameters = new ArrayList<Object>();

    /**
     * Constructs some Work with given parameters.
     * 
     * @param parameters
     */
    public ExtWork(Object... parameters) {
        super();
        // add parameters to the parameter list
        for (Object parameter : parameters) {
            this.parameters.add(parameter);
        }
    }

    public ExtWork() {
        super();
    }

    public void release() {
        LOGGER.info("WorkManagerWork.release()");
    }

    public boolean isDaemon() {
        return false;
    }

    public abstract void run();

    public abstract Object getResult();

}
