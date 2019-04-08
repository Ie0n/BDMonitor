package com.daily.jcy.bdmonitor;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.bean.Node;
import com.daily.jcy.bdmonitor.fragments.NodesFragment;

public class NodeDetails extends AppCompatActivity {

    private Node mNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_details);
        getBeforeIntent();
        initView();
        setCustomActionBar();
    }

    private void initView() {
        TextView textRack = findViewById(R.id.text_rack);
        TextView textState = findViewById(R.id.text_state);
        TextView textId = findViewById(R.id.text_id);
        TextView textNodeHostName = findViewById(R.id.text_nodeHostName);
        TextView textVersion = findViewById(R.id.text_version);
        TextView textAvailMemoryMB = findViewById(R.id.text_availMemoryMB);
        TextView textAvailableVirtualCores = findViewById(R.id.text_availableVirtualCores);
        TextView textUsedVirtualCores = findViewById(R.id.text_usedVirtualCores);
        if (mNode != null) {
            textRack.setText(mNode.getRack());
            textState.setText(mNode.getState());
            textId.setText(mNode.getId());
            textNodeHostName.setText(mNode.getNodeHostName());
            textVersion.setText(mNode.getVersion());
            textAvailMemoryMB.setText(mNode.getAvailMemoryMB());
            textAvailableVirtualCores.setText(mNode.getAvailableVirtualCores());
            textUsedVirtualCores.setText(mNode.getUsedVirtualCores());
        }

    }

    private void getBeforeIntent() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            Node node = intent.getParcelableExtra(NodesFragment.TARGET_NODE);
            if (node != null) {
                mNode = node;
            }
        }
    }
    private void setCustomActionBar() {
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar, null);
        getSupportActionBar().setCustomView(mActionBarView, lp);
        TextView text = mActionBarView.findViewById(R.id.title);
        text.setText("节点详情");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView back = mActionBarView.findViewById(R.id.pic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeDetails.this.finish();
            }
        });
    }


}
