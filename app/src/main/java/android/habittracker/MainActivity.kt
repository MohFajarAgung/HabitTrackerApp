package android.habittracker

import android.habittracker.model.firebase.auth.FirebaseAuthClient
import android.habittracker.ui.screen.AppNavigation
import android.habittracker.ui.screen.ViewModelFactory
import android.habittracker.ui.screen.registration_section.AuthViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.habittracker.ui.theme.HabitTrackerTheme
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    private val firebaseAuthClient by lazy{
        FirebaseAuthClient(
            context = applicationContext,
            onTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            HabitTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel : AuthViewModel = viewModel(factory = ViewModelFactory(firebaseAuthClient) )

                    AppNavigation(
                        navHostController = navController,
                        authViewModel = authViewModel,
                        )
                }
            }
        }





    }

}






