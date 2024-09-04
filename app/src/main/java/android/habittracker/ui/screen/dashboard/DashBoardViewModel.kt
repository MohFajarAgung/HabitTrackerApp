package android.habittracker.ui.screen.dashboard

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
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class DashBoardViewModel(
    private val firebaseAuthClient: FirebaseAuthClient,
    private val firebaseDatabaseRealtimeClient: FirebaseDatabaseRealtimeClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    private val _todayHabitList = MutableStateFlow(TodayHabitDataList())
    val todayHabitList = _todayHabitList.asStateFlow()
    private val _allHabitList = MutableStateFlow(AllHabitDataList())
    val allHabitList = _allHabitList.asStateFlow()

    private val _todayTargets = MutableStateFlow(TodayTargetList())
    val todayTargets = _todayTargets.asStateFlow()

    private val _activityProgress = MutableStateFlow(ActivityProgressList())
    val activityProgress = _activityProgress.asStateFlow()

    private val _latestActivityList = MutableStateFlow(LatestActivityList())
    val latestActivityList = _latestActivityList.asStateFlow()

    init {
        getTodayHabitData()
        getAllHabitData()
        getTodayTargetData()
        setTodayHabitsData()
        setTodayTargets()
//        setActivityProgress()
        setLatestActivity()
    }


    //    function untuk getTodayHabitData dari Firebase Database Realtime
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTodayHabitData() {
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
            val result =
                firebaseDatabaseRealtimeClient.getTodayHabitData(
                    firebaseAuthClient.getSignInUser()?.userId.toString(),
                    formattedDate
                )
            _todayHabitList.value = result
            _todayHabitList.value?.let {
//                setActivityProgress(it)
            }
        }


    }

    private fun getAllHabitData() {
        viewModelScope.launch {
            val result = firebaseDatabaseRealtimeClient
                .getAllHabitDate(firebaseAuthClient.getSignInUser()?.userId.toString())

            _allHabitList.value = result

            _allHabitList.value?.let {
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


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTodayHabitsData() {
        viewModelScope.launch {
            val currentDate = LocalDate.now()
            val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
            firebaseDatabaseRealtimeClient.setTodayHabit(
                HabitsData(
                    habitId = "2",
                    name = "Water",
                    progress = 100,
                    icon = R.drawable.water_icon,
                ),
                userId = firebaseAuthClient.getSignInUser()?.userId.toString(),
                date = formattedDate
            )
            {
//                  Handle Result di sini
            }


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

    private fun setActivityProgress(allHabitsMap: AllHabitDataList) {
        viewModelScope.launch {


            var progress = 0
//
            allHabitsMap.date?.forEach { date, habits ->
                var totalPercentage = 0
                habits.forEach { data ->
                    data.progress?.let {
                        totalPercentage += it
                    }
                }
                progress = totalPercentage / habits.size
                Log.d("tanggal", date)

                // Define the formatter according to the date format
                val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
                // Parse the date string into a LocalDate object
                val dateFormatted = LocalDate.parse(date, formatter)
                // Get the day of the week
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