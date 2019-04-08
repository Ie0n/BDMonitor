package com.daily.jcy.bdmonitor.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AppInfo implements Parcelable {
    private String state;
    private String type;
    private int count;

    public AppInfo(String state, String type, int count) {
        this.state = state;
        this.type = type;
        this.count = count;
    }


    protected AppInfo(Parcel in) {
        state = in.readString();
        type = in.readString();
        count = in.readInt();
    }

    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel in) {
            return new AppInfo(in);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(state);
        dest.writeString(type);
        dest.writeInt(count);
    }
}
