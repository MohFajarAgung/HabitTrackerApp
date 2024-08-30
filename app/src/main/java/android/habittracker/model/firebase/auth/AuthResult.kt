package android.habittracker.model.firebase.auth

// data class untuk menyimpan data yang diambil dari hasil firebase auth
data class AuthResult(
    val data : UserData?,
    val errorMessage : String?
)

data class UserData(
    val userId : String?,
    val username : String?,
    val profilePictureUrl : String?
)



