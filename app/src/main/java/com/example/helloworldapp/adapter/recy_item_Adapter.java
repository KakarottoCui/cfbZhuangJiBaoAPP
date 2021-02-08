package com.example.helloworldapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworldapp.R;
import com.example.helloworldapp.activity.ContentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class recy_item_Adapter extends RecyclerView.Adapter<recy_item_Adapter.ViewHolder> {
    public List<Map<String,Object>> list=new ArrayList<>();
    public Context con;
    public LayoutInflater inflater;
    //定义点击事件
    private AdapterView.OnItemClickListener onItemClickListener;

    public  recy_item_Adapter(List<Map<String,Object>> list, Context con){
        this.con=con;
        this.list=list;
        inflater=LayoutInflater.from(con);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.recyclerview_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.recy_title.setText(list.get(position).get("title").toString());
        holder.recy_imageView.setImageDrawable((Drawable) list.get(position).get("images"));
        holder.recy_author.setText(list.get(position).get("authors").toString());

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("contents",list.get(position).get("contents").toString());
                Intent intent = new Intent(con, ContentActivity.class);
                intent.putExtras(bundle);
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView recy_title;
        public ImageView recy_imageView;
        public TextView recy_author;
        public ViewHolder(View itemView) {
            super(itemView);
            recy_title = itemView.findViewById(R.id.title_item);
            recy_imageView = itemView.findViewById(R.id.image_item);
            recy_author = itemView.findViewById(R.id.author_item);
        }
    }

    //定义点击接口
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    //点击方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = (AdapterView.OnItemClickListener) onItemClickListener;
    }

}