package android.habittracker.model.auth

data class AuthResult(
    val data : UserData?,
    val errorMessage : String?
)

data class UserData(
    val userId : String,
    val username : String?,
    val profilePictureUrl : String?
)



