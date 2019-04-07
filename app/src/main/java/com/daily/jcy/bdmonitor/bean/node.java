package com.daily.jcy.bdmonitor.bean;

public class node {
    private String rack,state,id,nodeHostName,version,availMemoryMB,availableVirtualCores,usedVirtualCores;

    public node(String rack, String state, String id, String nodeHostName, String version, String availMemoryMB, String availableVirtualCores, String usedVirtualCores) {
        this.rack = rack;
        this.state = state;
        this.id = id;
        this.nodeHostName = nodeHostName;
        this.version = version;
        this.availMemoryMB = availMemoryMB;
        this.availableVirtualCores = availableVirtualCores;
        this.usedVirtualCores = usedVirtualCores;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeHostName() {
        return nodeHostName;
    }

    public void setNodeHostName(String nodeHostName) {
        this.nodeHostName = nodeHostName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAvailMemoryMB() {
        return availMemoryMB;
    }

    public void setAvailMemoryMB(String availMemoryMB) {
        this.availMemoryMB = availMemoryMB;
    }

    public String getAvailableVirtualCores() {
        return availableVirtualCores;
    }

    public void setAvailableVirtualCores(String availableVirtualCores) {
        this.availableVirtualCores = availableVirtualCores;
    }

    public String getUsedVirtualCores() {
        return usedVirtualCores;
    }

    public void setUsedVirtualCores(String usedVirtualCores) {
        this.usedVirtualCores = usedVirtualCores;
    }
}
