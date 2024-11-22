package c03148.zoltan

import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import c03148.zoltan.client.Client
import c03148.zoltan.settings.Settings
import c03148.zoltan.ui.component.AppBarContainer
import c03148.zoltan.ui.component.NavigationComp
import c03148.zoltan.ui.theme.ZoltanTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    companion object {
        val client = Client()
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        while (!client.initialized.get()) {
            Thread.sleep(100)
        }

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            ZoltanTheme {
                val navController = rememberNavController()
                val isScrolling = remember { mutableStateOf(false) }
                val bottomBarState = rememberSaveable { mutableStateOf(true) }
                val systemBarFollowThemeState = rememberSaveable { mutableStateOf(true) }
                val forcedTheme by Settings.Misc.rememberForceTheme()
                val localDarkTheme by Settings.Misc.rememberIsDarkMode()
                val systemDarkTheme = isSystemInDarkTheme()
                val darkTheme = remember(forcedTheme, localDarkTheme, systemDarkTheme) {
                    if (forcedTheme) localDarkTheme else systemDarkTheme
                }

                DisposableEffect(darkTheme, systemBarFollowThemeState.value) {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(
                            Color.TRANSPARENT,
                            Color.TRANSPARENT,
                        ) { darkTheme || !systemBarFollowThemeState.value },
                        navigationBarStyle = SystemBarStyle.auto(
                            Color.TRANSPARENT,
                            Color.TRANSPARENT,
                        ) { darkTheme || !systemBarFollowThemeState.value }
                    )
                    onDispose {}
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppBarContainer(
                        navController = navController,
                        paddingValues = innerPadding,
                        bottomBarState = bottomBarState,
                        windowSizeClass = windowSizeClass,
                        isScrolling = isScrolling
                    ) {
                        NavigationComp(
                            navController = navController,
                            paddingValues = innerPadding,
                            bottomBarState = bottomBarState,
                            systemBarFollowThemeState = systemBarFollowThemeState,
                            toggleRotate = ::toggleOrientation,
                            isScrolling = isScrolling
                        )
                    }

                }
            }
        }
    }
}

fun Activity.toggleOrientation() {
    requestedOrientation =
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED ||
            requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        ) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}