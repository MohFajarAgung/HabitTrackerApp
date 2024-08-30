package android.habittracker.model.firebase.dbs_realtime

import android.habittracker.model.firebase.auth.UserData
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.security.auth.callback.Callback

class FirebaseDatabaseRealtimeClient {

    private val database = Firebase.database.reference

}

