Video Player plugin for Cordova/PhoneGap
========================================

A Codova plugin that simply allows you to immediately play a video in fullscreen mode.


# Installation

This plugin use the Cordova CLI's plugin command. To install it to your application, simply execute the following command.

```
cordova plugin add https://github.com/comrat/CordovaVideoPlayerActivity.git
```


# Using

Just call the  `play` method with a video file path as argument. The video player will close itself when the video will be completed.

```
CordovaVideoPlayerActivity.play(path, [completeCallback], [errorCallback]);
```

The plugin is able to play file-path or http/rtsp URL.

You can also add an success callback function to handle completed playback.
You can also add an error callback function to handle unexpected playback errors.

## Example

```javascript
CordovaVideoPlayerActivity.play("file:///android_asset/www/movie.mp4");
```

```javascript
CordovaVideoPlayerActivity.play(
    "file:///android_asset/www/movie.mp4",
    function () {
        console.log("video completed");
    },
    function (err) {
        console.log(err);
    }
);
```
