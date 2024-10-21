package c03148.zoltan.ui.screen.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import c03148.zoltan.R

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.settings),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview()
@Composable
fun SettingsScreenPreview() {
    MainScreen(Modifier.fillMaxSize())
}