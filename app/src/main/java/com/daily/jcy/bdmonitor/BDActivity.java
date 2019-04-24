package com.daily.jcy.bdmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.bean.InterBean;

public class BDActivity extends AppCompatActivity {

    TextView id;
    TextView user;
    TextView name;
    TextView queue;
    TextView state;
    TextView finalStatus;
    TextView progress;
    TextView trackingUI;
    TextView trackingUrl;
    TextView diagnostics;
    TextView clusterId;
    TextView applicationType;
    TextView applicationTags;
    TextView startedTime;
    TextView finishedTime;
    TextView elapsedTime;
    TextView amContainerLogs;
    TextView amHostHttpAddress;
    TextView allocatedMB;
    TextView allocatedVCores;
    TextView runningContainers;

    InterBean.AppsBean.AppBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);
        Intent intent = getIntent();
        data = (InterBean.AppsBean.AppBean) intent.getSerializableExtra("data");
        initView();
    }

    private void initView() {
        id = (TextView) findViewById(R.id.id);
        id.setText(""+data.getId());
        user = (TextView) findViewById(R.id.user);
        user.setText(""+data.getUser());
        name = (TextView) findViewById(R.id.name);
        name.setText(""+data.getName());
        queue = (TextView) findViewById(R.id.queue);
        queue.setText(""+data.getQueue());
        state = (TextView) findViewById(R.id.state);
        state.setText(""+data.getState());
        finalStatus = (TextView) findViewById(R.id.finalStatus);
        finalStatus.setText(""+data.getFinalStatus());
        progress = (TextView) findViewById(R.id.progress);
        progress.setText(""+data.getProgress());
        trackingUI = (TextView) findViewById(R.id.trackingUI);
        trackingUI.setText(""+data.getTrackingUI());
        trackingUrl = (TextView) findViewById(R.id.trackingUrl);
        trackingUrl.setText(""+data.getTrackingUrl());
        diagnostics = (TextView) findViewById(R.id.diagnostics);
        diagnostics.setText(""+data.getDiagnostics());
        clusterId = (TextView) findViewById(R.id.clusterId);
        clusterId.setText(""+data.getClusterId());
        applicationType = (TextView) findViewById(R.id.applicationType);
        applicationType.setText(""+data.getApplicationType());
        applicationTags = (TextView) findViewById(R.id.applicationTags);
        applicationTags.setText(""+data.getApplicationTags());
        startedTime = (TextView) findViewById(R.id.startedTime);
        startedTime.setText(""+data.getStartedTime());
        finishedTime = (TextView) findViewById(R.id.finishedTime);
        finishedTime.setText(""+data.getFinishedTime());
        elapsedTime = (TextView) findViewById(R.id.elapsedTime);
        elapsedTime.setText(""+data.getElapsedTime());
        amContainerLogs = (TextView) findViewById(R.id.amContainerLogs);
        amContainerLogs.setText(""+data.getAmContainerLogs());
        amHostHttpAddress = (TextView) findViewById(R.id.amHostHttpAddress);
        amHostHttpAddress.setText(""+data.getAmHostHttpAddress());
        allocatedMB = (TextView) findViewById(R.id.allocatedMB);
        allocatedMB.setText(""+data.getAllocatedMB());
        allocatedVCores = (TextView) findViewById(R.id.allocatedVCores);
        allocatedVCores.setText(""+data.getAllocatedVCores());
        runningContainers = (TextView) findViewById(R.id.runningContainers);
        runningContainers.setText(""+data.getRunningContainers());
    }
}
