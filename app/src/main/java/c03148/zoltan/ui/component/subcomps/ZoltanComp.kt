package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import c03148.zoltan.data.Zoltan
import c03148.zoltan.data.stubZoltan

@Composable
fun ZoltanComp(zoltan: Zoltan, stub: Boolean, modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageURL(zoltan.url, stub, Modifier.size(96.dp))
            Text(
                text = zoltan.name,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ZoltanPreview() {
    ZoltanComp(stubZoltan, true, Modifier)
}