package android.habittracker.model.firebase.auth

data class SignInState(
    val isSignSuccessful : Boolean = false,
    val signInError : String ? = null
)