package android.habittracker.ui.screen.registration_section

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.registration_section.section.LoginOrCreateOption
import android.habittracker.ui.screen.registration_section.section.LoginOrCreateOptionData
import android.habittracker.ui.screen.registration_section.section.LoginOrSignUpWithEmail
import android.habittracker.ui.screen.registration_section.section.LoginOrSignUpWithEmailOption
import android.habittracker.ui.screen.registration_section.section.SignInAndSingUpBg
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    navController: NavController,
    onClick : () -> Unit
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

            LoginOrCreateOption(onClick = {onClick()})
            
            LoginOrSignUpWithEmail(textHead = LoginOrSignUpWithEmailOption().logIn, navController =  navController)


        }
    }



}
