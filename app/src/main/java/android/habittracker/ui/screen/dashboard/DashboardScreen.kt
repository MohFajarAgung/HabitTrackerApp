package android.habittracker.ui.screen.dashboard

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    logOut: () -> Unit,
){

    Button(onClick = { logOut() }) {
        Text(text = "Logout")
    }
}