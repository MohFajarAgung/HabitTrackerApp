package android.habittracker.ui.screen.dashboard

import android.habittracker.model.firebase.auth.SignInState
import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.dashboard.component.CustomDateScroller
import android.habittracker.ui.screen.dashboard.component.CustomHabitsGrid
import android.habittracker.ui.screen.dashboard.component.CustomLatestActivityList
import android.habittracker.ui.screen.dashboard.component.CustomModalDrawer
import android.habittracker.ui.screen.registration_section.AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    dashBoardViewModel: DashBoardViewModel,
    signInState: SignInState,
    authViewModel: AuthViewModel,
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
            HomeScreenContent(dashBoardViewModel = dashBoardViewModel, drawerState = drawerState, navController = navController)
        },
        navController = navController,
        authViewModel = authViewModel

    )


}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel,
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
            topAppBarForSection = SectionData().dashboard.homeScreen
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = "TODAY",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,

                    )
            )
            Spacer(modifier = modifier.height(20.dp))
            CustomDateScroller()
            Spacer(modifier = modifier.height(40.dp))

            LazyColumn() {

                item {
                    CustomHabitsGrid(dashBoardViewModel = dashBoardViewModel, navController = navController)
                    CustomLatestActivityList(dashBoardViewModel = dashBoardViewModel)

                }
            }


        }
    }
}