package android.habittracker.ui.screen.registration_section

import android.habittracker.ui.component.CustomTopAppBar
import android.habittracker.ui.screen.SectionData
import android.habittracker.ui.screen.registration_section.component.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
    val isLoading by authViewModel.isLoading.collectAsState()

    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent
        )
    }

    Box{
        SignInAndSingUpBg()
        Column {
            CustomTopAppBar(onClick = {navController.popBackStack()},topAppBarForSection = SectionData().registration)

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
        if(isLoading){
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
    }
}

