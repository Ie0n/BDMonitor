package com.daily.jcy.bdmonitor.bean;

public class clusterInfo {
    private Long id;
    private String state,haState,resourceManagerVersion,resourceManagerBuildVersion,
    resourceManagerVersionBuiltOn,hadoopVersion,hadoopBuildVersion,hadoopVersionBuiltOn;

    public clusterInfo(Long id, String state, String haState, String resourceManagerVersion,
                       String resourceManagerBuildVersion, String resourceManagerVersionBuiltOn,
                       String hadoopVersion, String hadoopBuildVersion, String hadoopVersionBuiltOn){
        this.hadoopBuildVersion = hadoopBuildVersion;
        this.hadoopVersion = hadoopVersion;
        this.hadoopVersionBuiltOn = hadoopVersionBuiltOn;
        this.resourceManagerBuildVersion = resourceManagerBuildVersion;
        this.resourceManagerVersionBuiltOn = resourceManagerVersionBuiltOn;
        this.state = state;
        this.haState = haState;
        this.resourceManagerVersion = resourceManagerVersion;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHaState() {
        return haState;
    }

    public void setHaState(String haState) {
        this.haState = haState;
    }

    public String getResourceManagerVersion() {
        return resourceManagerVersion;
    }

    public void setResourceManagerVersion(String resourceManagerVersion) {
        this.resourceManagerVersion = resourceManagerVersion;
    }

    public String getResourceManagerBuildVersion() {
        return resourceManagerBuildVersion;
    }

    public void setResourceManagerBuildVersion(String resourceManagerBuildVersion) {
        this.resourceManagerBuildVersion = resourceManagerBuildVersion;
    }

    public String getHadoopBuildVersion() {
        return hadoopBuildVersion;
    }

    public void setHadoopBuildVersion(String hadoopBuildVersion) {
        this.hadoopBuildVersion = hadoopBuildVersion;
    }

    public String getHadoopVersionBuiltOn() {
        return hadoopVersionBuiltOn;
    }

    public void setHadoopVersionBuiltOn(String hadoopVersionBuiltOn) {
        this.hadoopVersionBuiltOn = hadoopVersionBuiltOn;
    }

    public String getHadoopVersion() {
        return hadoopVersion;
    }

    public void setHadoopVersion(String hadoopVersion) {
        this.hadoopVersion = hadoopVersion;
    }

    public String getResourceManagerVersionBuiltOn() {
        return resourceManagerVersionBuiltOn;
    }

    public void setResourceManagerVersionBuiltOn(String resourceManagerVersionBuiltOn) {
        this.resourceManagerVersionBuiltOn = resourceManagerVersionBuiltOn;
    }
}
