package c03148.zoltan.client

import c03148.zoltan.data.Location
import c03148.zoltan.data.Zoltan
import c03148.zoltan.zoltanList
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.atomic.AtomicBoolean

class Client {

    private val client = OkHttpClient()
    val initialized = AtomicBoolean(false)

    init {
        Thread {
            val req = Request.Builder()
                .url(zoltanList)
                .build()

            val resp = client.newCall(req).execute()
            val zoltansObj = JSONObject(resp.body!!.string())

            for (name in zoltansObj.keys()) {
                val zoltanObj = zoltansObj.getJSONObject(name)
                val locationsArr = zoltanObj.getJSONArray("locations")
                val locations = mutableListOf<Location>()

                for (i in 0..<locationsArr.length()) {
                    val locationObj = locationsArr.getJSONObject(i)

                    locations.add(
                        Location(locationObj.getDouble("latitude"), locationObj.getDouble("longitude"))
                    )
                }

                Zoltan(name, zoltanObj.getString("url"), locations)
            }

            initialized.set(true)
        }.start()
    }

}