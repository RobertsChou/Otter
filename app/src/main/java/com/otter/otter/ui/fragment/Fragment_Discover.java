package com.otter.otter.ui.fragment;

import com.otter.otter.R;
import com.otter.otter.controller.CrowFundingController;
import com.otter.otter.ui.AboutUsActivity;
import com.otter.otter.ui.LoginActivity;
import com.otter.otter.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 *
 */
public class Fragment_Discover extends Fragment implements View.OnClickListener {

    private CrowFundingController crowFundingController;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Activity ctx;
    private View layout;

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            swipeRefreshLayout.setEnabled(listView.getFirstVisiblePosition() == 0);

        }
    };

    AbsListView.OnItemClickListener onItemClickListener = new AbsListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //            Intent intent = new Intent(ctx,
            //                    MediaRecorderActivity.class);
            //            ctx.startActivity(intent);
            //            ctx.overridePendingTransition(android.R.anim.slide_out_right,
            //                    android.R.anim.slide_in_left);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.activity_home, null);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    public void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);

        listView = (ListView) layout.findViewById(R.id.listView);
        listView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        listView.setOnItemClickListener(onItemClickListener);
        crowFundingController = new CrowFundingController(ctx);
        listView.setAdapter(crowFundingController);

    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setOnScrollListener(onScrollListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        listView.setOnScrollListener(null);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
