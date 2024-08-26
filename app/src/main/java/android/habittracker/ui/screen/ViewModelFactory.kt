package android.habittracker.ui.screen

import android.habittracker.model.auth.FirebaseAuthClient
import android.habittracker.ui.screen.registration_section.AuthViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val firebaseAuthClient: FirebaseAuthClient,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuthClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}