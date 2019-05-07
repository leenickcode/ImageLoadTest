package com.example.administrator.imageloadtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2019/5/6.
 *
 * @author Administrator
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> data;
    private Context mContext;
    ImageLoader imageLoader;
    int mImageWidth;
    public void setData(List<String> data) {
        this.data = data;
    }

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        imageLoader=ImageLoader.build(mContext);
        int screenWidth = MyUtils.getScreenMetrics(mContext).widthPixels;
        int space = (int)MyUtils.dp2px(mContext, 20f);
        mImageWidth = (screenWidth - space) / 3;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_image,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        imageLoader.bindBitmap(data.get(position),holder.imageView,mImageWidth,mImageWidth);
    }

    @Override
    public int getItemCount() {
        return data==null? 0: data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
