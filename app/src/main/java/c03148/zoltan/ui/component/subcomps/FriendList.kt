package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c03148.zoltan.R
import c03148.zoltan.data.User
import c03148.zoltan.data.stubUser

@Composable
fun FriendList(user: User, stub: Boolean, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(R.string.friends),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 24.sp
        )
        Spacer(Modifier.size(4.dp))
        LazyRow {
            itemsIndexed(user.friends) { i, user ->
                FriendComp(user, stub)

                if (i < user.friends.size) {
                    Spacer(Modifier.size(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FriendListPreview() {
    FriendList(stubUser, true)
}