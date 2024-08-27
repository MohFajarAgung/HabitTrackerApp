package android.habittracker.ui.screen.dashboard

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.dashboard.component.CustomDateScroller
import android.habittracker.ui.screen.dashboard.component.CustomHabitsGrid
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    dashBoardViewModel: DashBoardViewModel
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
        CustomTopAppBar(onClick = { /*TODO*/ }, topAppBarForSection = SectionData().dashboard)
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
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
            CustomHabitsGrid(dashBoardViewModel = dashBoardViewModel)
        }
    }
}