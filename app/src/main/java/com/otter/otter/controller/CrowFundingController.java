package com.otter.otter.controller;

import com.otter.otter.R;
import com.otter.otter.ui.CrowFundingDetailActivity;
import com.otter.otter.widget.SuperListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.otter.otterlibs.utils.Utils;

/**
 * Created by Wilbur on 15/10/5.
 */
public class CrowFundingController extends IBaseAdapter {

    private Context context;

    public CrowFundingController(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 50;
    }

    public void addAll() {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setOnClick(viewHolder, position);

        return convertView;
    }

    public void setOnClick(ViewHolder holder, int position) {
        holder.headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goNext(context, CrowFundingDetailActivity.class);
            }
        });
    }

    class ViewHolder {
        private SuperListView superListView;
        private CommentListController commentLiastAdapter;
        private View headerView;

        public ViewHolder(View view) {
            superListView = (SuperListView) view.findViewById(R.id.listView);
            commentLiastAdapter = new CommentListController(view.getContext());
            headerView = inflater.inflate(R.layout.item_home_header, null);
            superListView.addHeaderView(headerView);
            superListView.setAdapter(commentLiastAdapter);

        }
    }
}
