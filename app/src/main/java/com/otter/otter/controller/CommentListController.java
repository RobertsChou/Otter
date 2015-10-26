package com.otter.otter.controller;

import com.otter.otter.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wilbur on 15/9/4.
 */
public class CommentListController extends IBaseAdapter {

    public CommentListController(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_comment, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setData(viewHolder);
        return convertView;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void setData(ViewHolder viewHolder) {

    }

    class ViewHolder {
        ViewHolder(View view) {

        }
    }

}
