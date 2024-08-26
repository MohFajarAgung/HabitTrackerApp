package android.habittracker.ui.screen.registration_section

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.habittracker.model.auth.FirebaseAuthClient
import android.habittracker.model.auth.AuthResult
import android.habittracker.model.auth.SignInState
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val firebaseAuthClient: FirebaseAuthClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: AuthResult) {
        _state.update {
            it.copy(
                isSignSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }


    fun resetState() {
        _state.update { SignInState() }
    }

    fun signInWithGoogle(context: Context, onResult: (IntentSender?) -> Unit) {
        viewModelScope.launch {
            val signIntentSender = firebaseAuthClient.signInWithGoogle()
            onResult(signIntentSender)
        }
    }

    fun handleSignInResult(context: Context, navController: NavController, intent: Intent?) {
        viewModelScope.launch {
            val signInResult = firebaseAuthClient.signInWithIntent(intent = intent ?: return@launch)
            onSignInResult(signInResult)
            if(signInResult.errorMessage == null){
                Toast.makeText(context, "SignIn Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("onBoardingScreen") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }else{
                Toast.makeText(context, signInResult.errorMessage, Toast.LENGTH_SHORT ).show()
            }
        }
    }

//    SignUp dengan menggunakan email dan password
    fun signUpWithEmailAndPassword(navController: NavController,context: Context,email: String, password: String){
       viewModelScope.launch {
            val result = firebaseAuthClient.signUpWithEmailAndPassword(email, password)
           if(result.errorMessage == null){
               Toast.makeText(context, "Create Account Successfull", Toast.LENGTH_SHORT).show()
               navController.navigate("signInScreen")
           }else{
               Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
           }
        }
    }

//    SingIn dengan mengguankan email dan password
    fun signInWithEmailAndPassword(navController: NavController, context: Context, email: String, password: String){
        viewModelScope.launch {
            val result = firebaseAuthClient.signInWithEmailAndPassword(email, password)
            if(result.errorMessage == null){
                Toast.makeText(context, "SignIn Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("onBoardingScreen") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }else{
                Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun checkSignInStatus() {
        if (firebaseAuthClient.getSignInUser() != null) {
            _state.update { it.copy(isSignSuccessful = true) }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            firebaseAuthClient.signOut()
        }
    }


}
