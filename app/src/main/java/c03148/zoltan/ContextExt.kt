package c03148.zoltan

import android.content.Context
import android.content.pm.PackageManager

fun Context.permissionGranted(vararg permissions: String): Boolean {
    var granted = true
    for (permission in permissions) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) granted = false
    }
    return granted
}