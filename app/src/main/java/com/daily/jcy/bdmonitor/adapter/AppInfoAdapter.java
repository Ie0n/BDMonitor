package com.daily.jcy.bdmonitor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.AppInfo;

import java.util.List;

public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private List<AppInfo> data;
    private LayoutInflater inflater;

    public AppInfoAdapter(List<AppInfo> infoList, Context context) {
        data = infoList;
        inflater = LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textState,textType, textCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textState = itemView.findViewById(R.id.text_state);
            textType = itemView.findViewById(R.id.text_type);
            textCount = itemView.findViewById(R.id.text_count);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_app_info, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (data != null && data.size() != 0) {
            viewHolder.textState.setText(data.get(i).getState());
            viewHolder.textType.setText(data.get(i).getType());
            viewHolder.textCount.setText(String.valueOf(data.get(i).getCount()));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<AppInfo> data) {
        this.data = data;
    }
}
