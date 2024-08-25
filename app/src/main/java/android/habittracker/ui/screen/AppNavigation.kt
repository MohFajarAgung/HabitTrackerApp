package android.habittracker.ui.screen

import android.app.Activity
import android.habittracker.model.auth.GoogleAuthUiClient
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current

    NavHost(navController = navHostController, startDestination ="welcomeScreen") {

//     Registration_section
        composable("welcomeScreen") {
            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignInUser() != null) {
                    navHostController.navigate("dashboardScreen")
                }
            }
            WelcomeScreen(navController = navHostController)
        }
        composable("signInScreen") {
            val state by authViewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            authViewModel.onSignInResult(signResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignSuccessful) {
                if (state.isSignSuccessful) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navHostController.navigate("onBoardingScreen")
                    authViewModel.resetState()
                }
            }



            Box {

                Column {


                    SignInScreen(navController = navHostController, onClick = {
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    })
                }
            }
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
                lifecycleScope.launch {
                    googleAuthUiClient.signOut()
                }

                Toast.makeText(context, "Signed Out" , Toast.LENGTH_SHORT).show()

                navHostController.navigate("welcomeScreen")
            },)
        }

    }


}