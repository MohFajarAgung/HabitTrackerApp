package android.habittracker.model.auth

data class SignInState(
    val isSignSuccessful : Boolean = false,
    val signInError : String ? = null
)