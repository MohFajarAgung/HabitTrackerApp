package android.habittracker.ui.screen

import android.app.Activity
import android.habittracker.ui.screen.dashboard.DashboardScreen
import android.habittracker.ui.screen.registration_section.AuthViewModel
import android.habittracker.ui.screen.registration_section.OnBoardingScreen
import android.habittracker.ui.screen.registration_section.SignInScreen
import android.habittracker.ui.screen.registration_section.SignUpSreen
import android.habittracker.ui.screen.registration_section.WelcomeScreen
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
) {

    val context = LocalContext.current
    val state by authViewModel.state.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult() ,
        onResult = {result ->
            if(result.resultCode == Activity.RESULT_OK){
                authViewModel.handleSignInResult(result.data)
            }
        })



    NavHost(navController = navHostController, startDestination ="welcomeScreen") {

//     Registration_section
        composable("welcomeScreen") {
//            Ketika state berubah dan bernilai true maka pindah ke dashboardScreen
            LaunchedEffect(state.isSignSuccessful){
                if(state.isSignSuccessful){
                    Toast.makeText(context, "Signed Successful", Toast.LENGTH_SHORT).show()
                    navHostController.navigate("dashboardScreen"){
                        popUpTo(navHostController.graph.startDestinationId){
                            inclusive = true
                        }
                    }
                }
            }
            LaunchedEffect(key1 = Unit){
                authViewModel.checkSignInStatus()
            }
            WelcomeScreen(navController = navHostController)
        }
        composable("signInScreen") {
//            Ketika state berubah dan bernilai true maka pindah ke onBoardingScreen
           LaunchedEffect(state.isSignSuccessful){
               if(state.isSignSuccessful){
                   Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                   navHostController.navigate("onBoardingScreen"){
                       popUpTo(navHostController.graph.startDestinationId){
                           inclusive = true
                       }
                   }
               }
           }

                    SignInScreen(navController = navHostController, onClick = {
                        authViewModel.signInWithGoogle(context){ intentSender ->
                            intentSender?.let {
                                launcher.launch(IntentSenderRequest.Builder(it).build())
                            }
                        }
                    })
        }
        composable("signUpScreen") {
            SignUpSreen(navController = navHostController)
        }

        composable("onBoardingScreen") {
            OnBoardingScreen(navController = navHostController)
        }

//   Dashboard

        composable ("dashboardScreen"){
            DashboardScreen(logOut = {
              authViewModel.resetState()
                authViewModel.logOut()
                Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show()
                navHostController.navigate("welcomeScreen"){
                    popUpTo(navHostController.graph.startDestinationId){
                        inclusive = true
                    }
                }
            },)
        }

    }


}