package c03148.zoltan.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PadLeft(modifier: Modifier = Modifier, comp: @Composable () -> Unit) {
    Row {
        Spacer(modifier)
        comp()
    }
}

@Composable
fun PadTop(modifier: Modifier = Modifier, comp: @Composable () -> Unit) {
    Column {
        Spacer(modifier)
        comp()
    }
}