package com.daily.jcy.bdmonitor;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.daily.jcy.bdmonitor.bean.NodeCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NodeCheckService extends Service {
    NotificationManager notificationManager;
    Notification notification;
    RemoteViews remoteViews;


    private int unhealthy_node = 0;
    private int lost_node = 0;

    private final int UNHEALTHY_NODE = 1;
    private final int LOST_NODE = 2;

    int checkFrequency = 20;//设置检查节点频率（单位为秒）

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UNHEALTHY_NODE:
                    remoteViews.setTextViewText(R.id.text_unhealthy_node, "" + unhealthy_node);
                    notificationManager.notify(1,notification);
                    unhealthy_node = 0;
                    break;
                case LOST_NODE:
                    remoteViews.setTextViewText(R.id.text_node_loss, "" + lost_node);
                    notificationManager.notify(1,notification);
                    lost_node = 0;
                    break;
                default:
            }

        }
    };

    public NodeCheckService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initNotification();
        new Thread(() -> {
            while (true) {
                String data = sendRequeestWithOkhttp("http://172.23.27.193:8088/ws/v1/cluster/metrics");
                Gson gson = new Gson();
                NodeCheck nodeCheck = gson.fromJson(data, new TypeToken<NodeCheck>() {
                }.getType());
                if (nodeCheck != null) {
                    lost_node = nodeCheck.getClusterMetrics().getLostNodes();
                    unhealthy_node = nodeCheck.getClusterMetrics().getUnhealthyNodes();
                    if (unhealthy_node != 0) {
                        Message message = new Message();
                        message.what = UNHEALTHY_NODE;
                        handler.sendMessage(message);
                    } else if (lost_node != 0) {
                        Message message = new Message();
                        message.what = LOST_NODE;
                        handler.sendMessage(message);
                    }
                }
                try {
                    Thread.sleep(1000 * checkFrequency);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ONE_ID = "id";
        String CHANNEL_ONE_NAME = "Channel One";
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification_nodes_check);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), getPackageName())
                .setContent(remoteViews)
                .setChannelId("id")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("音乐播放器")
                .setContentText("音乐播放器");
        notification = builder.build();
        startForeground(1, builder.build());
    }

    public static String sendRequeestWithOkhttp(final String url) {
        String responseData = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    private int[] parseXML(String date) {
        String lostNodes = "0";
        String unhealthyNodes = "0";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(date));
            int evetType = xmlPullParser.getEventType();
            while (evetType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (evetType) {
                    case XmlPullParser.START_TAG:
                        if ("lostNodes".equals(nodeName)) {
                            lostNodes = xmlPullParser.nextText();
                        } else if ("unhealthyNodes".equals(nodeName)) {
                            unhealthyNodes = xmlPullParser.nextText();
                        }
                        break;
                }
                evetType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{Integer.parseInt(lostNodes),Integer.parseInt(unhealthyNodes)};
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
