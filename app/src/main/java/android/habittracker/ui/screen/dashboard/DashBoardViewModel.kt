package android.habittracker.ui.screen.dashboard

import android.content.Context
import android.habittracker.R
import android.habittracker.model.firebase.auth.FirebaseAuthClient
import android.habittracker.model.firebase.auth.SignInState
import android.habittracker.model.firebase.data.ActivityProgressData
import android.habittracker.model.firebase.data.ActivityProgressList
import android.habittracker.model.firebase.data.AllHabitDataList
import android.habittracker.model.firebase.data.TodayHabitDataList
import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.model.firebase.data.LatestActivityData
import android.habittracker.model.firebase.data.LatestActivityList
import android.habittracker.model.firebase.data.TodayTargetData
import android.habittracker.model.firebase.data.TodayTargetList
import android.habittracker.model.firebase.dbs_realtime.FirebaseDatabaseRealtimeClient
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class DashBoardViewModel(
    private val firebaseAuthClient: FirebaseAuthClient,
    private val firebaseDatabaseRealtimeClient: FirebaseDatabaseRealtimeClient
) : ViewModel() {

    private val _todayHabitList = MutableStateFlow(TodayHabitDataList())
    val todayHabitList = _todayHabitList.asStateFlow()
    private val _allHabitList = MutableStateFlow(AllHabitDataList())

    private val _todayTargets = MutableStateFlow(TodayTargetList())
    val todayTargets = _todayTargets.asStateFlow()

    private val _activityProgress = MutableStateFlow(ActivityProgressList())
    val activityProgress = _activityProgress.asStateFlow()

    private val _latestActivityList = MutableStateFlow(LatestActivityList())
    val latestActivityList = _latestActivityList.asStateFlow()

    val currentDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    val formattedDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        currentDate.format(DateTimeFormatter.ofPattern("yyyy MM dd"))
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    init {
        getTodayHabitData()
        getAllHabitData()
        getTodayTargetData()
        setTodayHabitsData()
        setTodayTargets()
        setLatestActivity()
    }


    //    function untuk getTodayHabitData dari Firebase Database Realtime
    private fun getTodayHabitData() {
        viewModelScope.launch {
            val result =
                firebaseDatabaseRealtimeClient.getTodayHabitData(
                    firebaseAuthClient.getSignInUser()?.userId.toString(),
                    formattedDate
                )
            _todayHabitList.value = result
        }


    }

    private fun getAllHabitData() {
        viewModelScope.launch {
            val result = firebaseDatabaseRealtimeClient
                .getAllHabitDate(firebaseAuthClient.getSignInUser()?.userId.toString())

            _allHabitList.value = result

            _allHabitList.value.let {
                setActivityProgress(it)
            }

        }
    }


    //    function untuk getTodayTargetData dari Firebase Database Realtime
    private fun getTodayTargetData() {
        viewModelScope.launch {
            val result =
                firebaseDatabaseRealtimeClient.getTodayTarget(firebaseAuthClient.getSignInUser()?.userId.toString())
            _todayTargets.value = result
        }
    }


    private fun setTodayHabitsData() {
        viewModelScope.launch {

//            firebaseDatabaseRealtimeClient.setTodayHabit(
//                HabitsData(
//                    habitId = "7",
//                    name = "Water",
//                    progress = 30,
//                    icon = R.drawable.water_icon
//
//                ),
//                userId = firebaseAuthClient.getSignInUser()?.userId.toString(),
//                date = "2024 09 15"
//            )
//            {
////                  Handle Result di sini
//            }


//            _habitList.value = HabitDataList(listOf(
//                HabitsData(
//                    habitId = "1",
//                    name = "Drinking Water",
//                    progress = 25,
//                    icon = R.drawable.drinking_water_icon
//                )
//            ))

//                HabitsData(
//                    habitId = "2",
//                    habit = "Cycling",
//                    progress = 50,
//                    icon = R.drawable.cycling_icon
//
//                ),
//                HabitsData(
//                    habitId = "3",
//                    habit = "Water",
//                    progress = 75,
//                    icon = R.drawable.water_icon
//
//                ),
//                HabitsData(
//                    habitId = "4",
//                    habit = "Walking",
//                    progress = 100,
//                    icon = R.drawable.walking_icon
//
//                ),
//            ))
        }
    }

    private fun setTodayTargets() {
        viewModelScope.launch {
            firebaseDatabaseRealtimeClient.setTodayTarget(
                todayTargets = TodayTargetData(
                    todayTargetId = "0",
                    target = "8L",
                    toDo = "Water Intake",
                    icon = R.drawable.water_intake_today_target
                ),
                userId = firebaseAuthClient.getSignInUser()?.userId.toString()
            ) {
//                Handle Result di sini

            }
//            _todayTargets.value = TodayTargetList(
//                listOf(
//                    TodayTargetData(
//                        target = "8L",
//                        toDo = "Water Intake",
//                        icon = R.drawable.water_intake_today_target
//                    ),
//                    TodayTargetData(
//                        target = "2400",
//                        toDo = "Foot Steps",
//                        icon = R.drawable.foot_steps_today_target
//                    )
//                )
//            )
        }
    }


    fun setActivityProgress(
        allHabitsMap: AllHabitDataList = _allHabitList.value,
        dropDownValue: String = "Weekly"
    ) {
        var progress = 0
        var progressMonthly = 0

        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy MM dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        _activityProgress.value = ActivityProgressList(null)

        when (dropDownValue) {
            "Weekly" -> {
                allHabitsMap.date?.forEach { date, habits ->
                    var totalPercentage = 0
                    habits.forEach { data ->
                        data.progress?.let {
                            totalPercentage += it
                        }
                    }
                    progress = totalPercentage / habits.size
                    Log.d("persentasi seminggu", progress.toString())

                    val dateFormatted = LocalDate.parse(date, formatter)
                    val dayOfWeek: String = dateFormatted.dayOfWeek.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

                    val updateActivityProgress = _activityProgress.value.data.orEmpty() +
                            ActivityProgressData(
                                day = dayOfWeek,
                                progress = progress,
                            )
                    _activityProgress.value = ActivityProgressList(updateActivityProgress)

                }
            }

            "Monthly" -> {
                // Membuat peta untuk menyimpan total progress per bulan
                val monthlyProgressMap =
                    mutableMapOf<String, Pair<Int, Int>>() // (totalProgress, totalHabits)

                allHabitsMap.date?.forEach { date, habits ->
                    val dateFormatted = LocalDate.parse(date, formatter)
                    val month: String = dateFormatted.month.name.lowercase()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

                    var totalPercentage = 0
                    habits.forEach { data ->
                        data.progress?.let {
                            totalPercentage += it
                        }
                    }

                    // Menambahkan data progress dan habit untuk bulan yang sedang diproses
                    val (currentProgress, currentHabitCount) = monthlyProgressMap[month] ?: Pair(
                        0,
                        0
                    )

                    Log.d("bulan ${month}", totalPercentage.toString())
                    monthlyProgressMap[month] = Pair(
                        currentProgress + totalPercentage, // update total progress
                        currentHabitCount + habits.size   // update total jumlah habit
                    )
                }

                // Setelah semua data terkumpul, hitung dan update progress bulanan
                monthlyProgressMap.forEach { (month, progressData) ->
                    val (totalProgress, totalHabits) = progressData
                    progressMonthly = totalProgress / totalHabits

                    Log.d("persentasi perbulan", "$totalProgress / $totalHabits = $progressMonthly")

                    val updateActivityProgress = _activityProgress.value.data.orEmpty() +
                            ActivityProgressData(
                                day = month, // Menyimpan nama bulan
                                progress = progressMonthly,
                            )
                    _activityProgress.value = ActivityProgressList(updateActivityProgress)
                }
            }
        }
    }

    private fun setLatestActivity() {
        viewModelScope.launch {
            _latestActivityList.value = LatestActivityList(
                listOf(
                    LatestActivityData(
                        title = "Drinking 300ml Water",
                        timeAgo = "About 3 minutes ago",
                        image = R.drawable.drinking_water_icon
                    ),
                    LatestActivityData(
                        title = "Eat Snack (Fitbar)",
                        timeAgo = "About 10 minutes ago",
                        image = R.drawable.drinking_water_icon
                    ),
                    LatestActivityData(
                        title = "Eat Snack (Fitbar)",
                        timeAgo = "About 10 minutes ago",
                        image = R.drawable.drinking_water_icon
                    ),
                    LatestActivityData(
                        title = "Eat Snack (Fitbar)",
                        timeAgo = "About 10 minutes ago",
                        image = R.drawable.drinking_water_icon
                    )

                )
            )
        }
    }


}