package com.daily.jcy.bdmonitor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.BDActivity;
import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.InterBean;


public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private InterBean data;
    private Context context;

    public AppInfoAdapter(InterBean infoList) {
        data = infoList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textState,textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textState = itemView.findViewById(R.id.text_state);
            textType = itemView.findViewById(R.id.text_type);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_app_info, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (data != null && data.getApps().getApp().size() != 0) {
            viewHolder.textState.setText(data.getApps().getApp().get(i).getState());
            viewHolder.textType.setText(data.getApps().getApp().get(i).getApplicationType());
            viewHolder.itemView.setOnClickListener((v)->{
                Intent intent = new Intent(context, BDActivity.class);
                intent.putExtra("idnex", i);
                intent.putExtra("data",data.getApps().getApp().get(i));
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.getApps().getApp().size();
    }

    public void setData(InterBean data) {
        this.data = data;
    }
}
