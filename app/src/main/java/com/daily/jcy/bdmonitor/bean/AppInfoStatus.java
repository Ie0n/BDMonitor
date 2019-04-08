package com.daily.jcy.bdmonitor.bean;

public class AppInfoStatus {

    private int appsRunning,appsFailed,appsKilled,totalNodes,activeNodes;

    public AppInfoStatus(int appsFailed,int appsKilled,int appsRunning,int totalNodes,int activeNodes){
        this.appsFailed = appsFailed;
        this.appsKilled = appsKilled;
        this.appsRunning = appsRunning;
        this.totalNodes = totalNodes;
        this.activeNodes = activeNodes;
    }

    public int getAppsRunning() {
        return appsRunning;
    }

    public void setAppsRunning(int appsRunning) {
        this.appsRunning = appsRunning;
    }

    public int getAppsFailed() {
        return appsFailed;
    }

    public void setAppsFailed(int appsFailed) {
        this.appsFailed = appsFailed;
    }

    public int getAppsKilled() {
        return appsKilled;
    }

    public void setAppsKilled(int appsKilled) {
        this.appsKilled = appsKilled;
    }

    public int getActiveNodes() {
        return activeNodes;
    }

    public void setActiveNodes(int activeNodes) {
        this.activeNodes = activeNodes;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public void setTotalNodes(int totalNodes) {
        this.totalNodes = totalNodes;
    }
}
