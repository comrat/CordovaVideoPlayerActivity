package com.comrat.plugin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaVideoPlayerActivity extends CordovaPlugin {
    private CallbackContext callbackContext = null;
    private CordovaPlugin activityResultCallback;
    private boolean activityResultKeepRunning;
    private boolean keepRunning;
    protected static final String LOG_TAG = "VideoPlayer";

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        this.callbackContext = callbackContext;
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
                Log.d(LOG_TAG, "Run URL: " + videoUrl);
                cordova.setActivityResultCallback(CordovaVideoPlayerActivity.this);
                cordova.getActivity().startActivityForResult(intent, 0);
            }

            private Runnable init(String url){
                videoUrl = url;
                return this;
            }
        }.init(url) );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(LOG_TAG, "onActivityResult: " + requestCode + " resCode: " + resultCode);
        super.onActivityResult(requestCode, resultCode, intent);

        PluginResult result = new PluginResult(requestCode == 0 ? PluginResult.Status.OK : PluginResult.Status.NO_RESULT);
        result.setKeepCallback(false);
        this.callbackContext.sendPluginResult(result);
    }
}
