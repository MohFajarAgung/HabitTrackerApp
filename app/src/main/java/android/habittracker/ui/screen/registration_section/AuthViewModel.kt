package android.habittracker.ui.screen.registration_section

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.habittracker.model.auth.GoogleAuthUiClient
import android.habittracker.model.auth.SignInResult
import android.habittracker.model.auth.SignInState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
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
            val signIntentSender = googleAuthUiClient.signInWithGoogle()
            onResult(signIntentSender)
        }
    }

    fun handleSignInResult(intent: Intent?) {
        viewModelScope.launch {
            val signInResult = googleAuthUiClient.signInWithIntent(intent = intent ?: return@launch)
            onSignInResult(signInResult)
        }
    }

    fun checkSignInStatus() {
        if (googleAuthUiClient.getSignInUser() != null) {
            _state.update { it.copy(isSignSuccessful = true) }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            googleAuthUiClient.signOut()
        }
    }


}
