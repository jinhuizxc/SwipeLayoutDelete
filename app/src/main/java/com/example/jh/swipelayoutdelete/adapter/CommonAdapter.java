package com.example.jh.swipelayoutdelete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2017/11/13.
 *
 * 父类CommonAdapter
 */

public abstract class CommonAdapter <T> extends BaseAdapter{

    protected Context mContext;
    private List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    // 默认构造
//    public CommonAdapter(Context mContext, List<T> mDatas, LayoutInflater mInflater, int layoutId) {
//        this.mContext = mContext;
//        this.mDatas = mDatas;
//        this.mInflater = LayoutInflater.from(mContext);
//        this.layoutId = layoutId;
//    }

    public CommonAdapter(Context mContext, List<T> datas, int layoutId) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    // 抽象方法
    public abstract void convert(ViewHolder holder, T t);

}
