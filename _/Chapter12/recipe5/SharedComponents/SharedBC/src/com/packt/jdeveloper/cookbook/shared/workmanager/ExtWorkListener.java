package com.packt.jdeveloper.cookbook.shared.workmanager;


import commonj.work.WorkEvent;
import commonj.work.WorkListener;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import oracle.adf.share.logging.ADFLogger;


public class ExtWorkListener implements WorkListener {
    private final static ADFLogger LOGGER = ADFLogger.createADFLogger(ExtWorkListener.class);

    private ExtWorkManager manager;

    public ExtWorkListener(ExtWorkManager manager) {
        super();
        this.manager = manager;
    }

    public void workAccepted(WorkEvent workEvent) {
        LOGGER.info("Work accepted for work manager '" + manager.getManagerName() + "' at " + getTime());
    }

    public void workRejected(WorkEvent workEvent) {
        LOGGER.info("Work rejected for work manager '" + manager.getManagerName() + "'");
    }

    public void workStarted(WorkEvent workEvent) {
        LOGGER.info("Work started for work manager '" + manager.getManagerName() + "' at " + getTime());
    }

    public void workCompleted(WorkEvent workEvent) {
        LOGGER.info("Work completed for work manager '" + manager.getManagerName() + "' at " + getTime());
    }

    private String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
