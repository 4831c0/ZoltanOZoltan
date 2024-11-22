package c03148.zoltan.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import c03148.zoltan.permissionGranted
import c03148.zoltan.settings.Settings.Misc.rememberLastScreen
import c03148.zoltan.ui.screen.Screen
import c03148.zoltan.ui.screen.impl.GameScreen
import c03148.zoltan.ui.screen.impl.MainScreen
import c03148.zoltan.ui.screen.impl.SettingsScreen
import android.Manifest
import c03148.zoltan.MainActivity.Companion.locationHelper
import c03148.zoltan.ui.screen.impl.SetupScreen

@Composable
fun NavigationComp(
    navController: NavHostController,
    paddingValues: PaddingValues,
    bottomBarState: MutableState<Boolean>,
    systemBarFollowThemeState: MutableState<Boolean>,
    toggleRotate: () -> Unit,
    isScrolling: MutableState<Boolean>
) {
    val bottomNavEntries = rememberNavigationItems()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navPipe = hiltViewModel<ChanneledViewModel>()
    navPipe
        .initWithNav(navController, bottomBarState)
        .collectAsStateWithLifecycle(LocalLifecycleOwner.current)

    val context = LocalContext.current
    var permissionState by remember { mutableStateOf(context.permissionGranted(
        Manifest.permission.ACCESS_FINE_LOCATION
    )) }
    var lastStartScreen by rememberLastScreen()
    val startDest = remember(permissionState, lastStartScreen) {
        if (permissionState) {
            locationHelper.init()
            lastStartScreen
        } else Screen.SetupScreen()
    }
    val currentDest = remember(navController.currentDestination) {
        navController.currentDestination?.route ?: lastStartScreen
    }

    var lastShouldDisplay by rememberSaveable {
        mutableStateOf(bottomNavEntries.find { item -> item.route == currentDest } != null)
    }
    LaunchedEffect(navBackStackEntry) {
        navBackStackEntry?.destination?.route?.let {
            val shouldDisplayBottomBar =
                bottomNavEntries.find { item -> item.route == it } != null
            if (lastShouldDisplay != shouldDisplayBottomBar) {
                bottomBarState.value = shouldDisplayBottomBar
                lastShouldDisplay = shouldDisplayBottomBar
            }
            // TODO: Remove
            // systemBarFollowThemeState.value = !it.contains(Screen.MediaViewScreen.route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDest
    ) {
        composable(
            route = Screen.SetupScreen(),
        ) {
            navPipe.toggleNavbar(false)
            SetupScreen(Modifier.fillMaxSize().padding(paddingValues)) {
                navPipe.navigate(Screen.MainScreen())
                locationHelper.init()
            }
        }
        composable(
            route = Screen.MainScreen()
        ) {
            navPipe.toggleNavbar(true)
            MainScreen(Modifier.fillMaxSize().padding(paddingValues))
        }
        composable(
            route = Screen.GameScreen(),
        ) {
            navPipe.toggleNavbar(true)
            GameScreen(Modifier.fillMaxSize().padding(paddingValues))
        }
        composable(
            route = Screen.SettingsScreen()
        ) {
            navPipe.toggleNavbar(true)
            SettingsScreen(Modifier.fillMaxSize().padding(paddingValues))
        }
    }
}