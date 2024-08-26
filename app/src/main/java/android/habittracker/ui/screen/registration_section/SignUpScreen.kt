package android.habittracker.ui.screen.registration_section

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.registration_section.section.LoginOrCreateOption
import android.habittracker.ui.screen.registration_section.section.LoginOrSignUpWithEmail
import android.habittracker.ui.screen.registration_section.section.LoginOrSignUpWithEmailOption
import android.habittracker.ui.screen.registration_section.section.SignInAndSingUpBg
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
fun SignUpSreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
){
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
                text = "Create your account",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F414E),
                ),
                textAlign = TextAlign.Center
            )

            LoginOrCreateOption(authViewModel = authViewModel, navController = navController)

            LoginOrSignUpWithEmail(textHead = LoginOrSignUpWithEmailOption().SignUp, navController =  navController, authViewModel = authViewModel)


        }
    }
}
//@Composable
//fun SignUpSreenTest(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//){
//    val systemUiController = rememberSystemUiController()
//
//    SideEffect {
//        systemUiController.setStatusBarColor(
//            color = Color.Transparent
//        )
//    }
//
//    Box{
//        SignInAndSingUpBg()
//        Column {
//            CustomTopAppBar(onClick = {navController.popBackStack()})
//
//            Text(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(20.dp),
//                text = "Create your account",
//                style = TextStyle(
//                    fontSize = 28.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF3F414E),
//                ),
//                textAlign = TextAlign.Center
//            )
//
//            LoginOrCreateOption()
//
//            LoginOrSignUpWithEmail(textHead = LoginOrSignUpWithEmailOption().SignUp, navController =  navController)
//
//
//        }
//    }
//}
