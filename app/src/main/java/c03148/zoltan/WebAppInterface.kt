package c03148.zoltan

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.JavascriptInterface
import c03148.zoltan.MainActivity.Companion.locationHelper

@SuppressLint("MissingPermission")
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun getLocation(): String {
        if (locationHelper.currentLocation == null) {
            return "0 0"
        }

        return "${locationHelper.currentLocation!!.latitude} ${locationHelper.currentLocation!!.longitude}"
    }
}