package com.daily.jcy.bdmonitor.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Node implements Parcelable {
    private String rack, state, id, nodeHostName, version, availMemoryMB, availableVirtualCores, usedVirtualCores;

    public Node(String rack, String state, String id, String nodeHostName, String version, String availMemoryMB, String availableVirtualCores, String usedVirtualCores) {
        this.rack = rack;
        this.state = state;
        this.id = id;
        this.nodeHostName = nodeHostName;
        this.version = version;
        this.availMemoryMB = availMemoryMB;
        this.availableVirtualCores = availableVirtualCores;
        this.usedVirtualCores = usedVirtualCores;
    }

    protected Node(Parcel in) {
        rack = in.readString();
        state = in.readString();
        id = in.readString();
        nodeHostName = in.readString();
        version = in.readString();
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
        dest.writeString(version);
        dest.writeString(availMemoryMB);
        dest.writeString(availableVirtualCores);
        dest.writeString(usedVirtualCores);
    }
}
