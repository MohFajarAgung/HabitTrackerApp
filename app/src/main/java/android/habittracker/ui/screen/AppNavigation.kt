package android.habittracker.ui.screen

import android.habittracker.ui.screen.dashboard.AllHabitsScreen
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import android.habittracker.ui.screen.dashboard.DashboardScreen
import android.habittracker.ui.screen.dashboard.HabitDetail
import android.habittracker.ui.screen.dashboard.HomeScreen
import android.habittracker.ui.screen.paymentandprofile.ProfileScreen
import android.habittracker.ui.screen.registration_section.AuthViewModel
import android.habittracker.ui.screen.registration_section.OnBoardingScreen
import android.habittracker.ui.screen.registration_section.SignInScreen
import android.habittracker.ui.screen.registration_section.SignUpSreen
import android.habittracker.ui.screen.registration_section.WelcomeScreen
import android.widget.Toast
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
    dashBoardViewModel: DashBoardViewModel
) {

    val context = LocalContext.current
    val state by authViewModel.state.collectAsState()


    NavHost(navController = navHostController, startDestination = "welcomeScreen") {


//     Registration_section

        composable("welcomeScreen") {
//            Ketika state berubah dan bernilai true maka pindah ke dashboardScreen
            LaunchedEffect(state.isSignSuccessful) {
                if (state.isSignSuccessful) {
                    Toast.makeText(context, "Signed Successful", Toast.LENGTH_SHORT).show()
                    navHostController.navigate("homeScreen") {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }
//            Cek signIn status
            LaunchedEffect(key1 = Unit) {
                authViewModel.checkSignInStatus()
            }
            WelcomeScreen(navController = navHostController, authViewModel = authViewModel)
        }
        composable("signInScreen") {
            SignInScreen(
                navController = navHostController, authViewModel = authViewModel
            )
        }
        composable("signUpScreen") {
            SignUpSreen(navController = navHostController, authViewModel = authViewModel)
        }

        composable("onBoardingScreen") {
            OnBoardingScreen(navController = navHostController)
        }


//   Dashboard

        composable("dashboardScreen") {

            DashboardScreen(
                logOut = {
                    authViewModel.logOut(context = context, navController = navHostController)
                },
            )
        }

        composable("homeScreen") {
            HomeScreen(navController = navHostController, dashBoardViewModel = dashBoardViewModel, signInState = state, authViewModel = authViewModel)
        }

        composable("allHabitsScreen"){
            AllHabitsScreen(dashBoardViewModel = dashBoardViewModel, navController = navHostController, authViewModel = authViewModel)
        }

        composable("detailHabitScreen") {
            HabitDetail(dashBoardViewModel = dashBoardViewModel, navController = navHostController)
        }


//        Profile

        composable("profileScreen"){
            ProfileScreen(navController = navHostController, authViewModel = authViewModel )
        }


    }


}

