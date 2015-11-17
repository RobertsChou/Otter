package com.otter.otter.ui.fragment;

import com.otter.otter.R;
import com.otter.otter.controller.CrowFundingController;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 *
 */
public class Fragment_Profile extends Fragment implements View.OnClickListener {

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
