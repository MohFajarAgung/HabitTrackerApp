package android.habittracker.ui.screen.registration_section


import android.habittracker.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val systemUiController = rememberSystemUiController()

    // Set the status bar color to transparent
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
        )
    }

    val pagerState = rememberPagerState()
    val corotineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            count = 4,
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> OnBoardingPage(
                    painter = painterResource(id = R.drawable.onboarding1),
                    title = "Track Your Goal",
                    desc = "Don't worry if you have trouble determining your goals. We can help you determine your goals and track your goals"
                )

                1 -> OnBoardingPage(
                    painter = painterResource(id = R.drawable.onboarding2),
                    title = "Get Burn",
                    desc = "Let's keep burning, to achive yours goals, it hurts only temporarily, if you give up now you wil be in pain forever"
                )

                2 -> OnBoardingPage(
                    painter = painterResource(id = R.drawable.onboarding3),
                    title = "Eat Well",
                    desc = "Let's start a healthy lifestyle with us, we can determine your diet every day. healthy eating is fun"
                )

                3 -> OnBoardingPage(
                    painter = painterResource(id = R.drawable.onboarding4),
                    title = "Morning Yoga",
                    desc = "Let's start a healthy lifestyle with us, we can determine your diet every day. healthy eating is fun"
                )
            }


        }

        NextPageButton(onClick = {
            corotineScope.launch {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                   pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }else{
//                    ke screen selanjutnya
                }
            }
        })
    }

}

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    desc: String
) {
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painter,
            contentDescription = "Icon Main Habits",
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxWidth()
        )
        Column(modifier = modifier.padding(top = 50.dp, start = 30.dp, end = 80.dp)) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1617),
                )
            )
            Spacer(modifier.height(15.dp))
            Text(
                text = desc,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF7B6F72),
                )
            )
        }
    }


}

@Composable
fun NextPageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 70.dp, end = 30.dp)
    ) {
        OutlinedButton(
            onClick = {
                onClick()
            },
            modifier = Modifier
                .size(55.dp)
                .align(Alignment.BottomEnd),  //avoid the oval shape
            shape = CircleShape,
            border = BorderStroke(1.dp, Color(0xFFEBEAEC)),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFF92A3FD),
                contentColor = Color.White
            )
        ) {
            Icon(
                modifier = modifier.size(25.dp),
                painter = painterResource(id = R.drawable.next_button_icon),
                contentDescription = "tombol next"
            )
        }


    }
}