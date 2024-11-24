package c03148.zoltan.client

import c03148.zoltan.data.Location
import c03148.zoltan.data.Zoltan
import c03148.zoltan.zoltanList
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference
import kotlin.String
import kotlin.collections.Map

class Client {

    private val client = OkHttpClient()
    val initialized = AtomicBoolean(false)
    var zoltanRegistry = AtomicReference<Map<String, Zoltan>>(null)

    init {
        Thread {
            val req = Request.Builder()
                .url(zoltanList)
                .build()

            val resp = client.newCall(req).execute()
            val zoltansObj = JSONObject(resp.body!!.string())

            val zoltanMap = mutableMapOf<String, Zoltan>()
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

                zoltanMap[name] = Zoltan(name, zoltanObj.getString("url"), locations)
            }
            zoltanRegistry.set(zoltanMap)

            initialized.set(true)
        }.start()
    }

}