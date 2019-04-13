package com.daily.jcy.bdmonitor.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.PublicData;
import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.clusterInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CpuFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private String content;
    private static final String INFO = "info";
    private TextView state,haState,hadoopVersion,resourceManagerVersion,hadoopVersionBuiltOn,id
        ,hadoopBuildVersion,startedOn,resourceManagerBuildVersion;
    private clusterInfo clusterInfo;
    private Handler handler;

    public static CpuFragment newInstance(String content) {
        CpuFragment fragment = new CpuFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        content = bundle != null ? bundle.getString(TAG) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cpu, container, false);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PublicData publicData = PublicData.getInstance();
        getData(publicData.con(INFO));
        handler = new Handler();

    }

    private void init(){
        state = view.findViewById(R.id.text_status);
        haState = view.findViewById(R.id.text_hastate);
        hadoopVersion = view.findViewById(R.id.text_hadoop_version);
        resourceManagerVersion = view.findViewById(R.id.text_resource_manager_version);
        hadoopVersionBuiltOn = view.findViewById(R.id.text_hadoop_version_built_on);
        id = view.findViewById(R.id.text_id);
        hadoopBuildVersion = view.findViewById(R.id.text_hadoopBuildVersion);
        startedOn = view.findViewById(R.id.text_startedOn);
        resourceManagerBuildVersion = view.findViewById(R.id.text_resourceManagerBuildVersion);
    }

    private void getData(String url) {
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

                    JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
                    JsonObject s = jsonObject.get("clusterInfo").getAsJsonObject();

                    Gson gson = new Gson();

                    clusterInfo = gson.fromJson(s,new TypeToken<clusterInfo>() {}.getType());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            state.setText(clusterInfo.getState());
                            hadoopVersion.setText(clusterInfo.getHadoopVersion());
                            resourceManagerVersion.setText(clusterInfo.getResourceManagerBuildVersion());
                            hadoopVersionBuiltOn.setText(clusterInfo.getHadoopVersionBuiltOn());
                            haState.setText(clusterInfo.getHaState());
                            id.setText(String.valueOf(clusterInfo.getId()));
                            hadoopBuildVersion.setText(clusterInfo.getHadoopBuildVersion());
                            startedOn.setText(String.valueOf(clusterInfo.getStartedOn()));
                            resourceManagerBuildVersion.setText(clusterInfo.getResourceManagerBuildVersion());
                        }
                    });
                }
            }
        });
    }

}
