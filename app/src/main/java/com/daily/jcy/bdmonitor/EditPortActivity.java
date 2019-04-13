package com.daily.jcy.bdmonitor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditPortActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editIp;
    private TextView curIp;
    private Button confirm;
    private String newIp;
    private PublicData publicData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_port);
        setCustomActionBar();
        initView();
    }
    private void initView(){
        editIp = findViewById(R.id.edit_ip);
        curIp = findViewById(R.id.text_cur_ip);
        confirm = findViewById(R.id.btn_confirm);
        confirm.setOnClickListener(this);
        publicData = PublicData.getInstance();
        curIp.setText(publicData.getIp());
    }
    private void setCustomActionBar() {
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.actionbar, null);
        getSupportActionBar().setCustomView(mActionBarView, lp);
        TextView text = mActionBarView.findViewById(R.id.title);
        text.setText("修改IP");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView back = mActionBarView.findViewById(R.id.pic);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPortActivity.this.finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                newIp = editIp.getText().toString();
                if (newIp.equals("")){
                    Toast.makeText(this, "请输入IP及端口", Toast.LENGTH_SHORT).show();
                    return;
                }
                publicData.editIp(newIp);
                curIp.setText(publicData.getIp());
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                EditPortActivity.this.finish();
                break;

        }
    }
}
