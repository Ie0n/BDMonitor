package com.daily.jcy.bdmonitor;

public class PublicData {
    private String head = "http://";
    private String domain = "/ws/v1/cluster/";
    private String ip = "172.23.27.193:8088";
    public String con(String url){
        return head+ip+domain+url;
    }
    public void editIp(String newIp){
        this.ip = newIp;
    }


    public String getIp(){
        return ip;
    }

    private static volatile PublicData instance=null;

    private PublicData (){

    }
    public static  PublicData getInstance(){
        if(instance==null){
            synchronized(PublicData .class){
                if(instance==null){
                    instance=new PublicData ();
                }
            }
        }
        return instance;
    }
}
