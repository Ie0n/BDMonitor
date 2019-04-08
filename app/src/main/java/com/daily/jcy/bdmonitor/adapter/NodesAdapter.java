package com.daily.jcy.bdmonitor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.jcy.bdmonitor.R;
import com.daily.jcy.bdmonitor.bean.Node;
import com.daily.jcy.bdmonitor.callback.OnItemClickListener;

import java.util.List;

public class NodesAdapter extends RecyclerView.Adapter<NodesAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<Node> mList;
    private OnItemClickListener onItemClickListener;

    public NodesAdapter(Context context,List<Node> list){
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
        if (mList != null && mList.size() != 0) {
            Node node = mList.get(position);
            viewHolder.id.setText(node.getId());
            viewHolder.hostName.setText(node.getNodeHostName());
            viewHolder.cardView.setOnClickListener(this);
            viewHolder.cardView.setTag(R.id.tag_position, position);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(mList.get((Integer) v.getTag(R.id.tag_position)));
        }
    }

    //初始化对应控件
    static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView id;
        TextView hostName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.text_id);
            hostName = itemView.findViewById(R.id.text_host_name);
            cardView = itemView.findViewById(R.id.content_layout);
        }
    }

    public void setmList(List<Node> mList) {
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
