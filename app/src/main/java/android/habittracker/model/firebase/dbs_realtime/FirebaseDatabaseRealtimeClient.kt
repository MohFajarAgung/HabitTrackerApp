package android.habittracker.model.firebase.dbs_realtime

import android.habittracker.model.firebase.data.AllHabitDataList
import android.habittracker.model.firebase.data.TodayHabitDataList
import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.model.firebase.data.TodayTargetData
import android.habittracker.model.firebase.data.TodayTargetList
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class FirebaseDatabaseRealtimeClient {
    private val database = Firebase.database.reference


    //    Function yang akan dijalankan ketika user baru membuat akun, yang bertujuan untuk membuat data pada Firebase Database Realtime
    fun createDataForNewAccount(
        userId: String,
        username: String,
        email: String,
        callback: (String) -> Unit,
    ) {
        try {
            val userData = mapOf(
                "email" to email,
                "username" to username
            )
            database.child("users").child(userId).setValue(userData)
                .addOnSuccessListener { callback("Berhasil") }
                .addOnFailureListener { callback("Gagal") }

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun setTodayHabit(
        habits: HabitsData,
        userId : String,
        date : String,
        callback: (String) -> Unit,
    ) {
        try {
             habits?.let {
                 val newHabits = mapOf(
                     "habitId" to it.habitId,
                     "name" to it.name,
                     "progress" to it.progress,
                     "icon" to it.icon,
                 )
                 database
                     .child("users")
                     .child(userId)
                     .child("habit")
                     .child(date)
                     .child(it.habitId.toString())
                     .setValue(newHabits)
                     .addOnSuccessListener { callback("Set Habit Berhasil") }
                     .addOnFailureListener { callback("Set Habit Gagal") }
             }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllHabitDate(
        userId : String,
    ) : AllHabitDataList {
        return try {
            val snapshot = database
                .child("users")
                .child(userId)
                .child("habit")
                .get().await()
            val habitMap = snapshot.children.associate { dateSnapshot ->
                val date = dateSnapshot.key ?: ""
                val habitList = dateSnapshot.children.mapNotNull { habitSnapshot ->
                    habitSnapshot.getValue(HabitsData::class.java)
                }
                date to habitList
            }
            AllHabitDataList(
                habitMap
            )
        }catch (e : Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            AllHabitDataList(null)
        }
    }
    suspend fun getTodayHabitData(
        userId: String,
        date : String,
    ): TodayHabitDataList {
        return try {
            val snapshot = database
                .child("users")
                .child(userId)
                .child("habit")
                .child(date)
                .get().await()
            val habitList = snapshot.children.mapNotNull { dataSnapshot ->
                dataSnapshot.getValue(HabitsData::class.java)
            }
            TodayHabitDataList(
             habitList
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            TodayHabitDataList(null)
        }
    }

//    Untuk set today target dalam dalam Firebase Database Realtime
    fun setTodayTarget(
        todayTargets : TodayTargetData,
        userId : String,
        callback : (String) -> Unit
    ){
        try {
            todayTargets.let {
                val _todayTargets = mapOf(
                    "target" to it.target,
                    "toDo" to it.toDo,
                    "icon" to it.icon

                )
                database
                    .child("users")
                    .child(userId)
                    .child("todayTarget")
                    .child(it.todayTargetId.toString())
                    .setValue(_todayTargets)
                    .addOnSuccessListener { callback("Set Habit Berhasil") }
                    .addOnFailureListener { callback("Set Habit Gagal") }
            }
        }catch (e : Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
        }

    }

//    Untuk get Today Target data dari Firebase Database Realtime

    suspend fun getTodayTarget(
        userId : String,
    ) : TodayTargetList{
         return try {
             val snapshot = database
                 .child("users")
                 .child(userId)
                 .child("todayTarget")
                 .get().await()

             val todayTargetList = snapshot.children.mapNotNull { dataSnapshot ->
                 dataSnapshot.getValue(TodayTargetData::class.java)
             }

             TodayTargetList(todayTargetList)

         }catch (e : Exception){
             e.printStackTrace()
             if(e is CancellationException) throw e
             TodayTargetList(null)
         }
    }



}

