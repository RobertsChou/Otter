package com.otter.otter.ui;

import com.otter.otter.R;
import com.otter.otter.ui.fragment.Fragment_CrowFunding;
import com.otter.otter.utils.Utils;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

public class LoginActivity extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer
        .OnCompletionListener, MediaPlayer.OnErrorListener {

    private VideoView mVV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mVV = (VideoView) findViewById(R.id.video_view);
        String uri = "android.resource://com.otter.otter" + "/" + R.raw.landscapes;
        mVV.setVideoPath(uri);
        mVV.start();
        mVV.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Utils.goNext(this, Fragment_CrowFunding.class);
        this.finish();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
