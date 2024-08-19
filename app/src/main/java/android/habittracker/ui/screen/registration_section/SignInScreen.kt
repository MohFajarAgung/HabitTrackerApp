package android.habittracker.ui.screen.registration_section

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.component.LoginOrCreateOption
import android.habittracker.ui.component.LoginOrSignUpWithEmail
import android.habittracker.ui.component.LoginOrSignUpWithEmailOption
import android.habittracker.ui.component.SignInAndSingUpBg
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent
        )
    }

    Box{
        SignInAndSingUpBg()
        Column {
            CustomTopAppBar(onClick = {navController.popBackStack()})

            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = "Welcome Back!",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F414E),
                ),
                textAlign = TextAlign.Center
            )

            LoginOrCreateOption()
            
            LoginOrSignUpWithEmail(textHead = LoginOrSignUpWithEmailOption().logIn, navController =  navController)


        }
    }



}
