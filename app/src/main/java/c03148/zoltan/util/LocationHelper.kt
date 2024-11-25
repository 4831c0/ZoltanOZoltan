package c03148.zoltan.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.ContextCompat.getSystemService
import c03148.zoltan.MainActivity.Companion.client
import c03148.zoltan.MainActivity.Companion.locationHelper
import c03148.zoltan.data.GameScreen
import c03148.zoltan.ui.screen.impl.activeScreen
import java.util.Date
import kotlin.collections.iterator
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@SuppressLint("MissingPermission")
class LocationHelper(mContext: Context) {

    fun degreesToRadians(degrees: Double): Double {
        return degrees * Math.PI / 180
    }

    fun distanceInKmBetweenEarthCoordinates(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadiusKm = 6371.0

        val dLat = degreesToRadians(lat2 - lat1)
        val dLon = degreesToRadians(lon2 - lon1)

        val lat1Rad = degreesToRadians(lat1)
        val lat2Rad = degreesToRadians(lat2)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                sin(dLon / 2) * sin(dLon / 2) * cos(lat1Rad) * cos(lat2Rad)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadiusKm * c
    }

    var currentLocation: Location? = null
    private var lastLocation = Date()
    private var locationManager: LocationManager =
        getSystemService(mContext, LocationManager::class.java) as LocationManager

    private fun locationUpdate() {
        val location = locationHelper.currentLocation!!
        var hasZoltanNearBy = false

        val zoltanRegistry = client.zoltanRegistry.get()
        for (zoltan in zoltanRegistry) {
            for (zLocation in zoltan.value.locations) {
                val diffMeters = distanceInKmBetweenEarthCoordinates(location.latitude,
                    location.longitude, zLocation.latitude, zLocation.longitude) * 1000

                if (diffMeters <= 20) {
                    hasZoltanNearBy = true
                }
            }
        }

        if (hasZoltanNearBy) {
            activeScreen.value = GameScreen.GAME
        } else {
            activeScreen.value = GameScreen.MAP
        }
    }

    fun init() {
        val gpsLocationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                currentLocation = location
                lastLocation = Date()
                locationUpdate()

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
                    locationUpdate()

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

}