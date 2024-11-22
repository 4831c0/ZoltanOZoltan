package c03148.zoltan.ui.screen.impl

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SetupScreen(modifier: Modifier = Modifier, callback: () -> Unit) {
    val scope = rememberCoroutineScope()
    val permissions = rememberMultiplePermissionsState(listOf(Manifest.permission.ACCESS_FINE_LOCATION))

    LaunchedEffect(permissions.allPermissionsGranted) {
        if (permissions.allPermissionsGranted) {
            callback()
        }
    }

    Box(modifier.fillMaxSize()) {
        if (!permissions.allPermissionsGranted) {
            Button(onClick = {
                scope.launch {
                    permissions.launchMultiplePermissionRequest()
                }
            }) {
                Text("Grant Permissions")
            }
        } else {
            callback()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    SetupScreen(Modifier.fillMaxSize()) {

    }
}