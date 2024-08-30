package android.habittracker.ui.screen.registration_section

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.habittracker.model.firebase.auth.FirebaseAuthClient
import android.habittracker.model.firebase.auth.AuthResult
import android.habittracker.model.firebase.auth.SignInState
import android.habittracker.model.firebase.dbs_realtime.FirebaseDatabaseRealtimeClient
import android.habittracker.model.firebase.dbs_realtime.TestData
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val firebaseAuthClient: FirebaseAuthClient,
    private val firebaseDatabaseRealtimeClient: FirebaseDatabaseRealtimeClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()


    private fun onSignInResult(result: AuthResult) {
        _state.update {
            it.copy(
                isSignSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }


    private fun resetState() {
        _state.update { SignInState() }
    }

    fun signInWithGoogle(context: Context, onResult: (IntentSender?) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            val signIntentSender = firebaseAuthClient.signInWithGoogle()
            onResult(signIntentSender)
            _isLoading.value = false
        }
    }

    fun handleSignInResult(context: Context, navController: NavController, intent: Intent?) {
        _isLoading.value = true
        viewModelScope.launch {
            val signInResult = firebaseAuthClient.signInWithIntent(intent = intent ?: return@launch)
            onSignInResult(signInResult)
            if(signInResult.errorMessage == null){
                _isLoading.value = false
                Toast.makeText(context, "SignIn Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("onBoardingScreen")
            }else{
                _isLoading.value = false
                Toast.makeText(context, signInResult.errorMessage, Toast.LENGTH_SHORT ).show()
            }
        }
    }

//    SignUp dengan menggunakan email dan password
    fun signUpWithEmailAndPassword(navController: NavController,context: Context,username : String, email: String, password: String){
       _isLoading.value = true
        viewModelScope.launch {
            val result = firebaseAuthClient.signUpWithEmailAndPassword(username, email, password)
           if(result.errorMessage == null){
               _isLoading.value = false
               Toast.makeText(context, "Create Account Successfull", Toast.LENGTH_SHORT).show()
               navController.navigate("signInScreen")
           }else{
               _isLoading.value = false
               Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
           }
        }
    }

//    SingIn dengan mengguankan email dan password
    fun signInWithEmailAndPassword(navController: NavController, context: Context, email: String, password: String){
        _isLoading.value = true
        viewModelScope.launch {
            val result = firebaseAuthClient.signInWithEmailAndPassword(email, password)
            if(result.errorMessage == null){
                _isLoading.value = false
                Toast.makeText(context, "SignIn Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("onBoardingScreen") {
                    popUpTo("welcomeScreen") {
                        inclusive = true
                    }
                }
            }else{
                _isLoading.value = false
                Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

//  Fungsi untuk mengecek status user apakah sudah login atau belum.
    fun checkSignInStatus() {
        if (firebaseAuthClient.getSignInUser() != null) {
            _state.update { it.copy(isSignSuccessful = true) }
        }
    }

//    Funsi untuk logout
    fun logOut(context: Context, navController: NavController) {
    _isLoading.value
        viewModelScope.launch {
            val result = firebaseAuthClient.signOut()
            if(result.errorMessage == null){
                Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT).show()
                resetState()
                !_isLoading.value
                navController.navigate("welcomeScreen"){
                    popUpTo("dashboardScreen"){
                        inclusive = true
                    }
                }
            }else{
                !_isLoading.value
                Toast.makeText(context, result.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }



}
