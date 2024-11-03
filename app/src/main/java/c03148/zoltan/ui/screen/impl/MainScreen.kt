package c03148.zoltan.ui.screen.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c03148.zoltan.R
import c03148.zoltan.data.stubUser
import c03148.zoltan.ui.component.PadLeft
import c03148.zoltan.ui.component.subcomps.ProfileComponent
import c03148.zoltan.ui.component.subcomps.ZoltanList

@Composable
fun MainScreen(modifier: Modifier = Modifier, stub: Boolean = false) {
    Box(modifier.fillMaxSize()) {
        Column {
            Text(
                text = stringResource(R.string.zoltan_title),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            PadLeft(Modifier.size(8.dp)) {
                Column {
                    Spacer(Modifier.size(26.dp))
                    ProfileComponent(modifier, stub, stubUser)
                    Spacer(Modifier.size(16.dp))
                    ZoltanList(stubUser, stub, modifier)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(Modifier.fillMaxSize(), true)
}