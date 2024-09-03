package android.habittracker.ui.screen

import android.habittracker.model.firebase.auth.FirebaseAuthClient
import android.habittracker.model.firebase.dbs_realtime.FirebaseDatabaseRealtimeClient
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import android.habittracker.ui.screen.registration_section.AuthViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val firebaseAuthClient: FirebaseAuthClient,
    private val firebaseDatabaseRealtimeClient: FirebaseDatabaseRealtimeClient
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(firebaseAuthClient, firebaseDatabaseRealtimeClient) as T
        }
        if(modelClass.isAssignableFrom(DashBoardViewModel::class.java)){
            return DashBoardViewModel(firebaseAuthClient,firebaseDatabaseRealtimeClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}