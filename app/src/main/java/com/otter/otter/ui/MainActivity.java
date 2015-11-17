package com.otter.otter.ui;

import com.otter.otter.R;
import com.otter.otter.ui.fragment.Fragment_CrowFunding;
import com.otter.otter.ui.fragment.Fragment_Discover;
import com.otter.otter.ui.fragment.Fragment_Friends;
import com.otter.otter.ui.fragment.Fragment_Profile;
import com.otter.otterlibs.utils.Utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wilbur on 15/10/22.
 */
public class MainActivity extends FragmentActivity {
    private TextView txt_title;
    private TextView unreaMsgdLabel;// 未读消息textview
    private TextView unreadAddressLable;// 未读通讯录textview
    private TextView unreadFindLable;// 发现
    private Fragment[] fragments;
    public Fragment_CrowFunding fragmentCrowFunding;
    private Fragment_Friends contactlistfragment;
    private Fragment_Discover findfragment;
    private Fragment_Profile profilefragment;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private int index;
    private int currentTabIndex;// 当前fragment的index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabView();
    }

    private void initTabView() {
        txt_title = (TextView) findViewById(R.id.navigationBar_title);

        txt_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goNext(getBaseContext(), AboutUsActivity.class);
            }
        });

        fragmentCrowFunding = new Fragment_CrowFunding();
        contactlistfragment = new Fragment_Friends();
        findfragment = new Fragment_Discover();
        profilefragment = new Fragment_Profile();
        fragments = new Fragment[] {fragmentCrowFunding, contactlistfragment,
                findfragment, profilefragment};
        imagebuttons = new ImageView[4];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_weixin);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_contact_list);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_find);
        imagebuttons[3] = (ImageView) findViewById(R.id.ib_profile);

        imagebuttons[0].setSelected(true);
        textviews = new TextView[4];
        textviews[0] = (TextView) findViewById(R.id.tv_weixin);
        textviews[1] = (TextView) findViewById(R.id.tv_contact_list);
        textviews[2] = (TextView) findViewById(R.id.tv_find);
        textviews[3] = (TextView) findViewById(R.id.tv_profile);
        textviews[0].setTextColor(0xFF45C01A);
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragmentCrowFunding)
                .add(R.id.fragment_container, contactlistfragment)
                .add(R.id.fragment_container, profilefragment)
                .add(R.id.fragment_container, findfragment)
                .hide(contactlistfragment).hide(profilefragment)
                .hide(findfragment).show(fragmentCrowFunding).commit();
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.re_weixin:
                index = 0;
                txt_title.setText("众筹");
                break;
            case R.id.re_contact_list:
                index = 1;
                txt_title.setText("通讯录");
                break;
            case R.id.re_find:
                index = 2;
                txt_title.setText("践行");
                break;
            case R.id.re_profile:
                index = 3;
                txt_title.setText("我");
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
    }

}

