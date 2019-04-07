package com.daily.jcy.bdmonitor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.adapter.NodesAdapter;
import com.daily.jcy.bdmonitor.bean.clusterInfo;
import com.daily.jcy.bdmonitor.bean.node;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NodesFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<node> nodeList;
    private Handler handler;

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
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData("http://172.23.27.193:8088/ws/v1/cluster/nodes");
        handler = new Handler();

    }
    public void getData(String url) {
        Request request = new Request.Builder().url(url).build();
        final OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("fail","获取数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null && response.isSuccessful()) {

                    String result = response.body().string();
                    Log.d("Result: ",result);



//                    JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
//                    JsonArray s = jsonObject.get("node").getAsJsonArray();
//                    Log.d("JsonArray: ",s.getAsString());
                    JsonArray s =  new JsonParser().parse(result).getAsJsonArray();

                    Gson gson = new Gson();
                    nodeList = new ArrayList<>();

                    //加强for循环遍历JsonArray
                    for (JsonElement Node : s) {
                        //使用GSON，直接转成Bean对象
                        node node = gson.fromJson(Node, node.class);
                        nodeList.add(node);
                        Log.d("node: ",node.toString());
                    }

                    //recyclerView.setAdapter(new NodesAdapter(mContext, nodeList));

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        });
    }
}
