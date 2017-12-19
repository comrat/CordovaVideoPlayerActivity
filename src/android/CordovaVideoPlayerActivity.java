package com.example.sample.plugin;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaVideoPlayerActivity extends CordovaPlugin {

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        if (action.equals("play")) {
            String url = args.getString(0);
            this.openPlayer(context, url);
            return true;
        }
        return false;
    }

    private void openPlayer(Context context, String url) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            private String videoUrl;

            @Override
            public void run() {
                Context context = cordova.getActivity().getApplicationContext();
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("VIDEO_URL", videoUrl);
                cordova.getActivity().startActivity(intent);
            }

            private Runnable init(String url){
                videoUrl = url;
                return this;
            }
        }.init(url) );
    }
}
