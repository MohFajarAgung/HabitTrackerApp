package android.habittracker.model.firebase.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.habittracker.R
import android.service.credentials.BeginCreateCredentialRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class FirebaseAuthClient(
    private val context: Context,
    private val onTapClient: SignInClient
) {

    private val auth = Firebase.auth
    private val database = Firebase.database.reference

    //    SignIn menggunakan akun facebook
//    suspend fun signInWithFacebook(): IntentSender? {
//        val result = try {
//            onTapClient.beginSignIn(
//                buildSignInFacebookRequest()
//            ).await()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            if (e is CancellationException) throw e
//            null
//        }
//        return result?.pendingIntent?.intentSender
//    }
//
//    private fun buildSignInFacebookRequest(): BeginSignInRequest {
//        return  BeginSignInRequest.Builder()
//    }


    //    SignIn menggunakan akun google
    suspend fun signInWithGoogle(): IntentSender? {
        val result = try {
            onTapClient.beginSignIn(
                buildSignInGoogleRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    //    Funsi untuk build sign in request
    private fun buildSignInGoogleRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }

    //    fungsi untuk login dengan google setelah mendapatkan credentials saat fungsi signInWithGoogle sudah dijalankan
    suspend fun signInWithIntent(intent: Intent): AuthResult {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
//            auth.setLanguageCode("id")
            val user = auth.signInWithCredential(googleCredentials).await().user
            AuthResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthResult(
                data = null,
                errorMessage = e.message
            )
        }
    }


    //    SignIn dengan menggunakan email dan password
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return try {
            val user = auth.signInWithEmailAndPassword(email, password).await().user
            AuthResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    //    SignUp dengan menggunakan email dan password
    suspend fun signUpWithEmailAndPassword(username: String,email: String, password: String): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await().user

            result?.run{
                val userData = mapOf(
                    "email" to email,
                    "username" to username
                )
                database.child("users").child(uid).setValue(userData)
                    .addOnSuccessListener {
                        auth.signOut()
                    }
            }

            AuthResult(
                data = null,
                errorMessage = null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthResult(
                data = null,
                errorMessage = e.message
            )

        }
    }


    suspend fun signOut(): AuthResult {
        return  try {
            onTapClient.signOut().await()
            auth.signOut()
            AuthResult(data = null, errorMessage = null)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthResult(data = null, errorMessage = e.message)
        }
    }

    fun getSignInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }


}
