package com.example.sample.plugin;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {
    protected static final String LOG_TAG = "VideoPlayer";

    ProgressDialog pDialog;
    VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                url = null;
            } else {
                url = extras.getString("VIDEO_URL");
            }
        } else {
            url = (String)savedInstanceState.getSerializable("VIDEO_URL");
        }

        Log.d(LOG_TAG, "PlayUrl: " + url);

        super.onCreate(savedInstanceState);
        setContentView(this.getResources().getIdentifier("videoview_main", "layout", this.getPackageName()));
        videoview = (VideoView)findViewById(this.getResources().getIdentifier("VideoView", "id", this.getPackageName()));

        pDialog = new ProgressDialog(VideoPlayerActivity.this);
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        try {
            MediaController mediacontroller = new MediaController(VideoPlayerActivity.this);
            mediacontroller.setAnchorView(videoview);
            Uri video = Uri.parse(url);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });
    }
}
