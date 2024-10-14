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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import c03148.zoltan.settings.Settings.Misc.rememberLastScreen
import c03148.zoltan.ui.screen.Screen
import c03148.zoltan.ui.screen.impl.GameScreen
import c03148.zoltan.ui.screen.impl.MainScreen
import c03148.zoltan.ui.screen.impl.SettingsScreen

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

    var lastStartScreen by rememberLastScreen()
    val startDest = remember { lastStartScreen }
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