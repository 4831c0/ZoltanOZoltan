package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ImageURL(url: String, stub: Boolean, modifier: Modifier = Modifier.size(24.dp)) {
    if (stub) {
        Box(
            modifier = modifier.clip(RectangleShape)
                .background(Color.Red)
        )
    } else {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = modifier
        )
    }
}