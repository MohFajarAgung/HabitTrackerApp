package android.habittracker.ui.screen.paymentandprofile

import android.habittracker.R
import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.dashboard.HomeScreenContent
import android.habittracker.ui.screen.dashboard.component.CustomHabitsGrid
import android.habittracker.ui.screen.dashboard.component.CustomModalDrawer
import android.habittracker.ui.screen.registration_section.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent
        )
    }
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    CustomModalDrawer(
        drawerState = drawerState,
        content = {
            ProfileContent(drawerState = drawerState, navController = navController)
        },
        navController = navController,
        authViewModel = authViewModel

    )

}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF4D57C8))
    ) {
        CustomTopAppBar(
            onClick = { scope.launch { drawerState.open() } },
            topAppBarForSection = SectionData().profileAndPayment.profileScreen
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = modifier.height(30.dp))

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                ProfileNameAndImage()
                TaskAndTime()
                TrackingHabits()
            }
            Spacer(modifier = modifier.height(20.dp))
            Box(modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White),)
            {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = ""
                        )
                        Spacer(modifier = modifier.width(10.dp))
                        Text(text = "Longest Streak")

                    }
                    Text(text = "20 Days")
                }

            }
        }
    }
}


@Composable
fun ProfileNameAndImage(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(40.dp),
            painter = painterResource(id = R.drawable.google_icon),
            contentDescription = "Profile Image"
        )
        Spacer(modifier = modifier.width(10.dp))
        Column {
            Text(text = "Mohamad Fajar Agung")
            Text(text = "Name")
        }
    }
}

@Composable
fun TaskAndTime(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Total Hours")
            Text(text = "18")
        }
        Image(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.water_icon),
            contentDescription = "Clock Image"
        )
        Column {
            Text(text = "Task Completed")
            Text(text = "12")
        }
        Image(
            modifier = modifier.size(20.dp),
            painter = painterResource(id = R.drawable.drinking_water_icon),
            contentDescription = "Task Completed Image"
        )
    }
}

@Composable
fun TrackingHabits(
    modifier: Modifier = Modifier
) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }
            Box(
                modifier = modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray), contentAlignment = Alignment.Center
            ) {
                Text(text = "7")
            }

        }
}