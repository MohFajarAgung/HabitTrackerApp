package android.habittracker.ui.screen

import android.habittracker.ui.screen.registration_section.OnBoardingScreen
import android.habittracker.ui.screen.registration_section.SignInScreen
import android.habittracker.ui.screen.registration_section.SignUpSreen
import android.habittracker.ui.screen.registration_section.WelcomeScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){

 NavHost(navController = navHostController, startDestination = "onBoardingScreen" ){

//     Registration_section
     composable("welcomeScreen"){
         WelcomeScreen(navController = navHostController)
     }
     composable("signInScreen"){
         Box {

             Column {


             SignInScreen(navController = navHostController)
             }
         }
     }
     composable("signUpScreen"){
         SignUpSreen(navController = navHostController)
     }

     composable("onBoardingScreen"){
         OnBoardingScreen(navController = navHostController)
     }

//

 }

    
}