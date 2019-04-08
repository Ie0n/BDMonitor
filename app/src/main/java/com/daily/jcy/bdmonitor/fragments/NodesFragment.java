package com.daily.jcy.bdmonitor.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daily.jcy.bdmonitor.NodeDetails;
import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.adapter.NodesAdapter;
import com.daily.jcy.bdmonitor.bean.Node;
import com.daily.jcy.bdmonitor.callback.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NodesFragment extends Fragment implements OnItemClickListener {

    public static final String TAG = "NodesFragment-11";
    public static final String NODE_URL = "http://172.23.27.193:8088/ws/v1/cluster/nodes";
    public static final int UPDATE_ADAPTER_DATA = 10001;
    public static final String RESPONSE_DATE_NODE = "RESPONSE_DATE_NODE";
    public static final String TARGET_NODE = "TARGET_NODE";
    private View view;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<Node> nodeList;
    private OkHttpClient client;
    private MyHandler handler;
    private NodesAdapter adapter;

    public static NodesFragment newInstance(String content) {
        NodesFragment fragment = new NodesFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nodes, container, false);
        initView();
        return view;
    }
    private void initView(){
        recyclerView = view.findViewById(R.id.rv_nodes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NodesAdapter(mContext, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData(NODE_URL);
        handler = new MyHandler(this);
    }
    public void getData(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData;
                    if (response.body() != null) {
                        responseData = response.body().string();
                        ArrayList<Node> nodes = handleJsonData(responseData);
                        Message message = Message.obtain();
                        message.what = UPDATE_ADAPTER_DATA;
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(RESPONSE_DATE_NODE, nodes);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private ArrayList<Node> handleJsonData(String responseData) {
        JsonObject jsonObject = new JsonParser().parse(responseData).getAsJsonObject();
        JsonArray jsonElements = jsonObject.getAsJsonObject("nodes").getAsJsonArray("node");

        Gson gson = new Gson();
        ArrayList<Node> nodesList = new ArrayList<>();
        for (JsonElement element : jsonElements) {
            Node node = gson.fromJson(element, new TypeToken<Node>() {}.getType());
            nodesList.add(node);
        }
        return nodesList;
    }

    /**
     * RecycleView的点击时间
     * @param node 点击的Node
     */
    @Override
    public void onItemClick(Node node) {
        // TODO: 2019/4/8 跳转后的显示的Node其他信息
        Intent intent = new Intent(mContext, NodeDetails.class);
        intent.putExtra(TARGET_NODE, node);
        startActivity(intent);
    }

    static class MyHandler extends Handler {
        WeakReference<NodesFragment> weakReference;

        public MyHandler(NodesFragment fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NodesFragment fragment = weakReference.get();
            switch (msg.what) {
                case UPDATE_ADAPTER_DATA:
                    ArrayList<Node> nodes = msg.getData().getParcelableArrayList(RESPONSE_DATE_NODE);
                    if (nodes != null) {
                        fragment.adapter.setmList(nodes);
                        fragment.adapter.notifyDataSetChanged();
                    }
            }
        }
    }

}
