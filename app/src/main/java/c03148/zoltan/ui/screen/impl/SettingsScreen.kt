package c03148.zoltan.ui.screen.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import c03148.zoltan.R
import c03148.zoltan.settings.Settings
import c03148.zoltan.ui.component.PadLeft
import com.alorma.compose.settings.ui.SettingsSwitch

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(R.string.settings_title),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            PadLeft(Modifier.size(8.dp)) {
                var forceTheme by Settings.Misc.rememberForceTheme()
                var isDark by Settings.Misc.rememberIsDarkMode()

                LazyColumn {
                    item { Spacer(Modifier.size(8.dp)) }
                    item {
                        SettingsSwitch(
                            state = forceTheme,
                            title = { Text(text = stringResource(R.string.force_theme)) },
                            subtitle = { Text(text = stringResource(R.string.force_theme_desc)) },
                            modifier = Modifier,
                            enabled = true,
                            onCheckedChange = { forceTheme = it },
                        )
                    }
                    if (forceTheme) {
                        item {
                            SettingsSwitch(
                                state = isDark,
                                title = {
                                    if (isDark) {
                                        Text(text = stringResource(R.string.dark_theme))
                                    } else {
                                        Text(text = stringResource(R.string.light_theme))
                                    }
                                },
                                subtitle = {
                                    val theme = if (isDark) {
                                        stringResource(R.string.dark)
                                    } else {
                                        stringResource(R.string.light)
                                    }
                                    Text(text = stringResource(R.string.current_theme, theme))
                                },
                                modifier = Modifier,
                                enabled = true,
                                onCheckedChange = { isDark = it },
                            )
                        }
                    }
                    item { Spacer(Modifier.size(8.dp)) }
                    item { Spacer(Modifier.size(8.dp)) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(Modifier.fillMaxSize())
}