package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c03148.zoltan.R
import c03148.zoltan.data.User
import c03148.zoltan.ui.component.PadLeft
import coil3.compose.AsyncImage

@Composable
fun ProfileComponent(modifier: Modifier = Modifier, stub: Boolean, stubUser: User) {
    Row {
        Spacer(Modifier.size(14.dp))
        Column {
            Text(
                text = stringResource(R.string.my_profile),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 24.sp
            )
            Spacer(Modifier.size(14.dp))
            if (stub) {
                Box(
                    modifier = Modifier.size(370.dp).clip(RectangleShape)
                        .background(Color.Red)
                )
            } else {
                AsyncImage(
                    model = "https://thispersondoesnotexist.com/",
                    contentDescription = null,
                )
            }
        }
    }

    PadLeft(Modifier.size(14.dp)) {
        Text(
            text = stubUser.username,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
    }
    PadLeft(Modifier.size(18.dp)) {
        Text(
            text = stringResource(R.string.level, stubUser.level),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
    }
}