package com.daily.jcy.bdmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.Trson;
import com.daily.jcy.bdmonitor.adapter.AppInfoAdapter;
import com.daily.jcy.bdmonitor.bean.AppInfo;
import com.daily.jcy.bdmonitor.bean.InterBean;
import com.daily.jcy.bdmonitor.bean.Node;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InternetFragment extends Fragment {
    public static final String TAG = "content";
    public static final int UPDATE_ADAPTER_DATA = 10001;
    public static final String RESPONSE_DATE_APP_INFO = "RESPONSE_DATE_APP_INFO";
    public static final String APP_INFO_URL = "http://172.23.27.193:8088/ws/v1/cluster/apps";
    private View view;
    private String content;
    private Context mContext;
    private MyHandler handler;
    private AppInfoAdapter adapter;
    private RecyclerView recyclerView;
    private OkHttpClient client;


    public static InternetFragment newInstance(String content) {
        InternetFragment fragment = new InternetFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler(this);
        Bundle bundle = getArguments();
        content = bundle != null ? bundle.getString(TAG) : "";
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_internet, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.internet_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new AppInfoAdapter(null);
        recyclerView.setAdapter(adapter);
        getData(APP_INFO_URL);
    }

    private void getData(final String url) {
        new Thread(() -> {
            client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                String responseData;
                if (response.body() != null) {
                    responseData = response.body().string();
                    Log.i(TAG, "run: " + responseData);
                    InterBean interBean = handleJsonData(responseData);
                    Message message = Message.obtain();
                    message.what = UPDATE_ADAPTER_DATA;
                    message.obj = interBean;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private InterBean handleJsonData(String responseData) {
        InterBean interBean = new InterBean();
        Trson.getTrson().factoryBean(responseData,interBean);
        return interBean;
    }

    static class MyHandler extends Handler {

        WeakReference<InternetFragment> weakReference;

        public MyHandler(InternetFragment fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InternetFragment fragment = weakReference.get();
            switch (msg.what) {
                case UPDATE_ADAPTER_DATA:
                    if (msg.obj != null) {
                        fragment.adapter.setData((InterBean)msg.obj);
                        fragment.adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

}
