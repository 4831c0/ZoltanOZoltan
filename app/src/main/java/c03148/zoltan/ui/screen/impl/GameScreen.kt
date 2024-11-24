package c03148.zoltan.ui.screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import c03148.zoltan.MAP
import c03148.zoltan.MainActivity.Companion.client
import c03148.zoltan.MainActivity.Companion.locationHelper
import c03148.zoltan.ui.component.subcomps.WebView

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    if (locationHelper.currentLocation != null) {
        val location = locationHelper.currentLocation!!
        var hasZoltanNearBy = false

        val zoltanRegistry = client.zoltanRegistry.get()
        for (zoltan in zoltanRegistry) {
            for (zLocation in zoltan.value.locations) {
                
            }
        }
    }
    WebView(MAP, modifier)
    // ARComponent(modifier)
}