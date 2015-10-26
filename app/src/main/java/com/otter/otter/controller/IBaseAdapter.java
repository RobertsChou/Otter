package com.otter.otter.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class IBaseAdapter extends BaseAdapter {

    public List listData = new ArrayList();
    protected Context context;
    protected LayoutInflater inflater;

    public IBaseAdapter(Context context) {

        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (listData != null) {
            return listData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (listData != null) {
            return listData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List list, boolean clear) {
        if (list == null) {
            return;
        }
        if (clear) {
            listData.clear();
        }
        listData.addAll(list);
        notifyDataSetChanged();
    }

    public void clearlist() {
        if (listData != null) {

            listData.clear();
        }
    }

    public List getListData() {
        return listData;
    }
}
