package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import c03148.zoltan.data.User
import c03148.zoltan.data.stubUser

@Composable
fun FriendComp(user: User, stub: Boolean = false, modifier: Modifier = Modifier) {
    Box(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageURL(user.picture, stub, Modifier.size(96.dp))
            Text(
                text = user.username,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FriendPreviewPreview() {
    FriendComp(stubUser, true)
}