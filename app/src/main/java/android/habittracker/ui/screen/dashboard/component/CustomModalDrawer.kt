package android.habittracker.ui.screen.dashboard.component

import android.habittracker.ui.screen.registration_section.AuthViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomModalDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = modifier
                    .background(Color(0xFFFBFBFB))
                    .width(300.dp)
            )
            {
                Column {

                    CurrentStreak()
                    Column(
                        modifier
                            .padding(start = 10.dp, end = 20.dp)
                    ) {


                        ListMenuBox(text = "Profile") {navController.navigate("profileScreen")}
                        ListMenuBox(text = "Today") {navController.navigate("homeScreen"){
                            popUpTo("homeScreen"){
                                inclusive =  true
                            }
                        } }
                        ListMenuBox(text = "Your States") {}
                        ListMenuBox(text = "Challenges") {}
                        ListMenuBox(text = "All habits") {navController.navigate("allHabitsScreen")}
                        ListMenuBox(text = "Notification") {}
                        ListMenuBox(text = "Setting") {}
                        ListMenuBox(text = "Log Out") { authViewModel.logOut(context, navController)}


                    }
                }
            }
        },
        content = {
            content()
        },
        gesturesEnabled = drawerState.isOpen
    )
}

@Composable
fun CurrentStreak(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(Color(0xFFA6ADFB)),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = modifier.padding(start = 20.dp, top = 40.dp, bottom = 10.dp)
        ) {
            Text(
                text = "1 Day",
                style = TextStyle(
                    color = Color(0xFF4A4A4A),
                    fontWeight = FontWeight.Medium,
                    fontSize = 21.sp
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "Your current streak",
                style = TextStyle(
                    color = Color(0xFF868181),
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp
                )
            )
        }
    }
}

@Composable
fun ListMenuBox(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            modifier = modifier.padding(20.dp),
            style = TextStyle(
                color = Color(0xFF6C6C6C),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        )
    }
}