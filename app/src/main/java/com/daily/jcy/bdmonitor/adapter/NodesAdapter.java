package com.daily.jcy.bdmonitor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.node;

import java.util.List;

public class NodesAdapter extends RecyclerView.Adapter<NodesAdapter.ViewHolder>{

    private Context mContext;
    private List<node> mList;

    public NodesAdapter(Context context,List<node> list){
        this.mContext = context;
        this.mList = list;
    }

    //绑定当前Item的View
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_node,viewGroup,false);
        return new ViewHolder(view);
    }

    //给对应控件设置数据及监听
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        node node = mList.get(position);
        viewHolder.id.setText(node.getId());
        viewHolder.hostName.setText(node.getNodeHostName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //初始化对应控件
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView hostName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_id);
            hostName = itemView.findViewById(R.id.text_host_name);
        }
    }
}
