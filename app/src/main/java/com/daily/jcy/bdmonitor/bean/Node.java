package com.daily.jcy.bdmonitor.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Node implements Parcelable {

    private String rack, state, id, nodeHostName,nodeHTTPAddress,lastHealthUpdate, version, numContainers,usedMemoryMB,availMemoryMB, availableVirtualCores, usedVirtualCores;


    public Node(String rack, String state, String id, String nodeHostName, String nodeHTTPAddress,
                String lastHealthUpdate, String version, String numContainers, String usedMemoryMB,
                String availMemoryMB, String availableVirtualCores, String usedVirtualCores) {
        this.rack = rack;
        this.state = state;
        this.id = id;
        this.nodeHostName = nodeHostName;
        this.nodeHTTPAddress = nodeHTTPAddress;
        this.lastHealthUpdate = lastHealthUpdate;
        this.version = version;
        this.numContainers = numContainers;
        this.usedMemoryMB = usedMemoryMB;
        this.availMemoryMB = availMemoryMB;
        this.availableVirtualCores = availableVirtualCores;
        this.usedVirtualCores = usedVirtualCores;
    }


    protected Node(Parcel in) {
        rack = in.readString();
        state = in.readString();
        id = in.readString();
        nodeHostName = in.readString();
        nodeHTTPAddress = in.readString();
        lastHealthUpdate = in.readString();
        version = in.readString();
        numContainers = in.readString();
        usedMemoryMB = in.readString();
        availMemoryMB = in.readString();
        availableVirtualCores = in.readString();
        usedVirtualCores = in.readString();
    }

    public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

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

    @NonNull
    @Override
    public String toString() {
        return "[ rack: " + getRack() +
                " state: " + getState() +
                " id: " + getId() +
                " NodeHostName: " + getNodeHostName() +
                " Version: " + getVersion() +
                " AvailMemoryMB: " + getAvailMemoryMB() +
                " ]";
    }



    public String getNodeHTTPAddress() {
        return nodeHTTPAddress;
    }

    public void setNodeHTTPAddress(String nodeHTTPAddress) {
        this.nodeHTTPAddress = nodeHTTPAddress;
    }

    public String getLastHealthUpdate() {
        return lastHealthUpdate;
    }

    public void setLastHealthUpdate(String lastHealthUpdate) {
        this.lastHealthUpdate = lastHealthUpdate;
    }

    public String getNumContainers() {
        return numContainers;
    }

    public void setNumContainers(String numContainers) {
        this.numContainers = numContainers;
    }

    public String getUsedMemoryMB() {
        return usedMemoryMB;
    }

    public void setUsedMemoryMB(String usedMemoryMB) {
        this.usedMemoryMB = usedMemoryMB;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rack);
        dest.writeString(state);
        dest.writeString(id);
        dest.writeString(nodeHostName);
        dest.writeString(nodeHTTPAddress);
        dest.writeString(lastHealthUpdate);
        dest.writeString(version);
        dest.writeString(numContainers);
        dest.writeString(usedMemoryMB);
        dest.writeString(availMemoryMB);
        dest.writeString(availableVirtualCores);
        dest.writeString(usedVirtualCores);
    }
}
