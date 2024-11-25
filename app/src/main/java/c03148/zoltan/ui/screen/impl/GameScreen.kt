package c03148.zoltan.ui.screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import c03148.zoltan.MAP
import c03148.zoltan.MainActivity.Companion.client
import c03148.zoltan.MainActivity.Companion.locationHelper
import c03148.zoltan.data.GameScreen
import c03148.zoltan.ui.component.ARComponent
import c03148.zoltan.ui.component.subcomps.WebView

var activeScreen = mutableStateOf<GameScreen>(GameScreen.MAP)

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    if (activeScreen.value == GameScreen.MAP) {
        WebView(MAP, modifier)
    } else {
        ARComponent(modifier)
    }
}