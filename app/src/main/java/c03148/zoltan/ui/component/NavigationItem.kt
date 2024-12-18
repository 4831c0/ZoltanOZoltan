package c03148.zoltan.ui.component

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)