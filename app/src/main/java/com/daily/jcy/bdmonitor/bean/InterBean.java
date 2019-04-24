package com.daily.jcy.bdmonitor.bean;

import java.io.Serializable;
import java.util.List;

public class InterBean {

    private AppsBean apps;

    public AppsBean getApps() {
        return apps;
    }

    public void setApps(AppsBean apps) {
        this.apps = apps;
    }

    public static class AppsBean {
        private List<AppBean> app;

        public List<AppBean> getApp() {
            return app;
        }

        public void setApp(List<AppBean> app) {
            this.app = app;
        }

        public static class AppBean implements Serializable {
            /**
             * id : application_1545274250125_0174
             * user : gyj
             * name : hy
             * queue : default
             * state : FINISHED
             * finalStatus : SUCCEEDED
             * progress : 100.0
             * trackingUI : History
             * trackingUrl : http://cqupt-04:8088/proxy/application_1545274250125_0174/A
             * diagnostics :
             * clusterId : 1545274250125
             * applicationType : SPARK
             * applicationTags :
             * startedTime : 1551718518910
             * finishedTime : 1551718724631
             * elapsedTime : 205721
             * amContainerLogs : http://cqupt-01:8042/node/containerlogs/container_1545274250125_0174_02_000001/gyj
             * amHostHttpAddress : cqupt-01:8042
             * allocatedMB : -1
             * allocatedVCores : -1
             * runningContainers : -1
             */

            private String id;
            private String user;
            private String name;
            private String queue;
            private String state;
            private String finalStatus;
            private double progress;
            private String trackingUI;
            private String trackingUrl;
            private String diagnostics;
            private long clusterId;
            private String applicationType;
            private String applicationTags;
            private long startedTime;
            private long finishedTime;
            private int elapsedTime;
            private String amContainerLogs;
            private String amHostHttpAddress;
            private int allocatedMB;
            private int allocatedVCores;
            private int runningContainers;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getQueue() {
                return queue;
            }

            public void setQueue(String queue) {
                this.queue = queue;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getFinalStatus() {
                return finalStatus;
            }

            public void setFinalStatus(String finalStatus) {
                this.finalStatus = finalStatus;
            }

            public double getProgress() {
                return progress;
            }

            public void setProgress(double progress) {
                this.progress = progress;
            }

            public String getTrackingUI() {
                return trackingUI;
            }

            public void setTrackingUI(String trackingUI) {
                this.trackingUI = trackingUI;
            }

            public String getTrackingUrl() {
                return trackingUrl;
            }

            public void setTrackingUrl(String trackingUrl) {
                this.trackingUrl = trackingUrl;
            }

            public String getDiagnostics() {
                return diagnostics;
            }

            public void setDiagnostics(String diagnostics) {
                this.diagnostics = diagnostics;
            }

            public long getClusterId() {
                return clusterId;
            }

            public void setClusterId(long clusterId) {
                this.clusterId = clusterId;
            }

            public String getApplicationType() {
                return applicationType;
            }

            public void setApplicationType(String applicationType) {
                this.applicationType = applicationType;
            }

            public String getApplicationTags() {
                return applicationTags;
            }

            public void setApplicationTags(String applicationTags) {
                this.applicationTags = applicationTags;
            }

            public long getStartedTime() {
                return startedTime;
            }

            public void setStartedTime(long startedTime) {
                this.startedTime = startedTime;
            }

            public long getFinishedTime() {
                return finishedTime;
            }

            public void setFinishedTime(long finishedTime) {
                this.finishedTime = finishedTime;
            }

            public int getElapsedTime() {
                return elapsedTime;
            }

            public void setElapsedTime(int elapsedTime) {
                this.elapsedTime = elapsedTime;
            }

            public String getAmContainerLogs() {
                return amContainerLogs;
            }

            public void setAmContainerLogs(String amContainerLogs) {
                this.amContainerLogs = amContainerLogs;
            }

            public String getAmHostHttpAddress() {
                return amHostHttpAddress;
            }

            public void setAmHostHttpAddress(String amHostHttpAddress) {
                this.amHostHttpAddress = amHostHttpAddress;
            }

            public int getAllocatedMB() {
                return allocatedMB;
            }

            public void setAllocatedMB(int allocatedMB) {
                this.allocatedMB = allocatedMB;
            }

            public int getAllocatedVCores() {
                return allocatedVCores;
            }

            public void setAllocatedVCores(int allocatedVCores) {
                this.allocatedVCores = allocatedVCores;
            }

            public int getRunningContainers() {
                return runningContainers;
            }

            public void setRunningContainers(int runningContainers) {
                this.runningContainers = runningContainers;
            }
        }
    }
}
