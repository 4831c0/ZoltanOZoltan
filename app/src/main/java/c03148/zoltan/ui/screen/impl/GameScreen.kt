package c03148.zoltan.ui.screen.impl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import c03148.zoltan.MAP
import c03148.zoltan.ui.component.subcomps.WebView

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    WebView(MAP, modifier)
    // ARComponent(modifier)
}