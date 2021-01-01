package com.packt.jdeveloper.cookbook.shared.workmanager;


import com.packt.jdeveloper.cookbook.shared.bc.exceptions.ExtJboException;

import commonj.work.WorkItem;
import commonj.work.WorkListener;
import commonj.work.WorkManager;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import oracle.adf.share.logging.ADFLogger;


public class ExtWorkManager {
    private final static ADFLogger LOGGER = ADFLogger.createADFLogger(ExtWorkManager.class);

    private static final String DEFAULT_MANAGER_NAME = "MyWorkManager";
    private String managerName = DEFAULT_MANAGER_NAME;
    private WorkManager workManager;
    private WorkListener workListener;
    private List<ExtWork> works = new ArrayList<ExtWork>();
    List<WorkItem> workList = new ArrayList<WorkItem>();
    // run the Work Manager serially by default
    private long waitType = WorkManager.INDEFINITE;

    public ExtWorkManager() {
    }

    /**
     * Constructor using a Work Manager name.
     * 
     * @param managerName, the Work Manager name.
     */
    public ExtWorkManager(String managerName) {
        // check for valid name; used default name otherwise
        if (managerName == null || "".equals(managerName)) {
            this.managerName = DEFAULT_MANAGER_NAME;
        }
    }

    /**
     * Adds some work to the Work Manager.
     * 
     * @param work, the ExtWork to add.
     */
    public void addWork(ExtWork work) {
        works.add(work);
    }

    /**
     * Runs the Work Manager.
     */
    public void run() {
        LOGGER.info("WorkManager.run()");

        try {
            // get the Work Manager from the context
            InitialContext ctx = new InitialContext();
            workManager =
                    (WorkManager)ctx.lookup("java:comp/env/" + managerName);

            // create a listener
            if (workListener == null) {
                workListener = new ExtWorkListener(this);
            }

            // schedule work items in a work list
            workList = new ArrayList<WorkItem>();
            for (ExtWork work : works) {
                WorkItem workItem = workManager.schedule(work, workListener);
                workList.add(workItem);
            }
            
            // run the Work Manager work list
            workManager.waitForAll(workList, waitType);
        } catch (Exception e) {
            LOGGER.severe(e);
            throw new ExtJboException(e);
        }

    }

    /**
     * Returns the results from all work items.
     * 
     * @return, the results list.
     */
    public List<ExtWork> getResult() {
        List<ExtWork> resultList = new ArrayList<ExtWork>();
        try {
            // iterate all work items and add their results to the
            // results list
            for (WorkItem workItem : workList) {
                resultList.add((ExtWork)workItem.getResult());
            }
        } catch (Exception e) {
            throw new ExtJboException(e);
        }
        
        // return the results list
        return resultList;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setWorkListener(WorkListener workListener) {
        this.workListener = workListener;
    }

    public WorkListener getWorkListener() {
        return workListener;
    }

    public void setWaitType(long waitType) {
        this.waitType = waitType;
    }

    public long getWaitType() {
        return waitType;
    }
}
