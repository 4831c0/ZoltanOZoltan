package c03148.zoltan

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.webkit.JavascriptInterface
import androidx.core.content.ContextCompat.getSystemService
import java.util.Date

@SuppressLint("MissingPermission")
class WebAppInterface(private val mContext: Context) {

    private var currentLocation: Location? = null
    private var lastLocation = Date()
    private var locationManager: LocationManager =
        getSystemService(mContext, LocationManager::class.java) as LocationManager

    init {
        val gpsLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                currentLocation = location
                lastLocation = Date()

                println("gps location update")
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val networkLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (lastLocation.time + 60_000*1000 < Date().time) {
                    currentLocation = location
                    lastLocation = Date()

                    println("network location update")
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (hasGps) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                gpsLocationListener
            )
        }
        if (hasNetwork) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F,
                networkLocationListener
            )
        }
    }

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun getLocation(): String {
        if (currentLocation == null) {
            return "0 0"
        }

        return "${currentLocation!!.latitude} ${currentLocation!!.longitude}"
    }
}