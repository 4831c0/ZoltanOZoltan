package c03148.zoltan.ui.component.subcomps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
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
fun ProfileComponent(modifier: Modifier = Modifier, stub: Boolean, user: User) {
    Column {
        Text(
            text = stringResource(R.string.my_profile),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 24.sp
        )
        Spacer(Modifier.size(14.dp))
        ImageURL(user.picture, stub, Modifier.size(370.dp))

        Text(
            text = stubUser.username,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.level, stubUser.level),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileComponentPreview() {
    ProfileComponent(Modifier, true, stubUser)
}