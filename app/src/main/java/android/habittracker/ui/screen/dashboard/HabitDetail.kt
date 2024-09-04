package android.habittracker.ui.screen.dashboard

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.dashboard.component.ActivityProgress
import android.habittracker.ui.screen.dashboard.component.CustomDateScroller
import android.habittracker.ui.screen.dashboard.component.CustomHabitsGrid
import android.habittracker.ui.screen.dashboard.component.CustomLatestActivityList
import android.habittracker.ui.screen.dashboard.component.CustomTodayTargetGrid
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HabitDetail(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel,
    navController : NavController
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF4D57C8))
    ) {
        CustomTopAppBar(
            onClick = { navController.popBackStack() },
            topAppBarForSection = SectionData().dashboard.detailHabit
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
          CustomTodayTargetGrid(dashBoardViewModel = dashBoardViewModel)
            ActivityProgress(dashBoardViewModel =  dashBoardViewModel)
            
            CustomLatestActivityList(dashBoardViewModel = dashBoardViewModel)
        }
    }

}