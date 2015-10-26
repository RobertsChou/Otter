package com.otter.otter.ui;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.otter.otter.R;
import com.otter.otter.ui.fragment.Fragment_CrowFunding;
import com.otter.otter.utils.Utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    private final long duration = 3000;
    private SharedPreferences preferences;
    SharedPreferences.Editor localEditor;
    int i;
    private TextView slogan;
    private TextView iconAnim;

    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Utils.goNextWithAnimation(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
        i = preferences.getInt("count", 0);
        slogan = (TextView) findViewById(R.id.slogan);
        iconAnim = (TextView) findViewById(R.id.icon);
        float sloganOriY = (float) slogan.getY();
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofFloat("translationY", sloganOriY + 400,
                sloganOriY);
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
        ObjectAnimator sloganAnimator = ObjectAnimator.ofPropertyValuesHolder(slogan, valuesHolder, valuesHolder1);
        sloganAnimator.setDuration(duration).start();
        sloganAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        float iconOrigY = (float) iconAnim.getY();
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("translationY", iconOrigY + 400,
                iconOrigY);
        ObjectAnimator.ofPropertyValuesHolder(iconAnim, valuesHolder1, valuesHolder2).setDuration(duration).start();

    }

}
