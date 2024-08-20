package android.habittracker.ui.screen.registration_section

import android.habittracker.ui.component.CustomButton
import android.habittracker.ui.screen.registration_section.section.WelcomeHeader
import android.habittracker.ui.screen.registration_section.section.WelcomeImage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()

    // Set the status bar color to transparent
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF8E97FD)),
        contentAlignment = Alignment.Center,

        ) {


        Column(
            modifier= modifier.padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            WelcomeHeader()
            WelcomeImage()
            CustomButton( modifier.padding(horizontal = 20.dp),textButton = "GET STARTED", onClick = {
                navController.navigate("signInScreen")
            } )

        }
    }



}
