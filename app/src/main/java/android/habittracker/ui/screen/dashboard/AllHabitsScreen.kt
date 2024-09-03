package android.habittracker.ui.screen.dashboard

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.dashboard.component.CustomDateScroller
import android.habittracker.ui.screen.dashboard.component.CustomHabitsGrid
import android.habittracker.ui.screen.dashboard.component.CustomLatestActivityList
import android.habittracker.ui.screen.dashboard.component.CustomModalDrawer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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

@Composable
fun AllHabitsScreen(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel,
    navController: NavController
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
            AllHabitsContent(dashBoardViewModel = dashBoardViewModel, drawerState = drawerState, navController = navController )
        }

    )
}

@Composable
fun AllHabitsContent(
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
            topAppBarForSection = SectionData().dashboard.allHabitsScreen
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding( horizontal = 15.dp)
        ) {
            Spacer(modifier = modifier.height(30.dp))

            CustomHabitsGrid(dashBoardViewModel = dashBoardViewModel, showAllHabits = true, navController = navController )


        }
    }
}


