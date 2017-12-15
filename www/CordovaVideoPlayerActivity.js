var exec = require('cordova/exec');


module.exports = {

    play: function(url, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "CordovaVideoPlayerActivity", "play", [url]);
    },

    close: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "CordovaVideoPlayerActivity", "close", []);
    }

};
