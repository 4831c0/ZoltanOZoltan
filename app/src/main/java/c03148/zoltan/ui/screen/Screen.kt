package c03148.zoltan.ui.screen

sealed class Screen(val route: String) {

    data object SetupScreen : Screen("setup_screen")
    data object MainScreen : Screen("main_screen")
    data object GameScreen : Screen("game_screen")
    data object SettingsScreen : Screen("settings_screen")

    operator fun invoke() = route
}
