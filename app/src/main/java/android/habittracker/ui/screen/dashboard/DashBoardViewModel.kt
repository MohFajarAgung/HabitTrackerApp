package android.habittracker.ui.screen.dashboard

import android.habittracker.R
import android.habittracker.model.firebase.auth.FirebaseAuthClient
import android.habittracker.model.firebase.auth.SignInState
import android.habittracker.model.firebase.auth.UserData
import android.habittracker.model.firebase.data.ActivityProgressData
import android.habittracker.model.firebase.data.ActivityProgressList
import android.habittracker.model.firebase.data.HabitDataList
import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.model.firebase.data.LatestActivityData
import android.habittracker.model.firebase.data.LatestActivityList
import android.habittracker.model.firebase.data.TodayTargetData
import android.habittracker.model.firebase.data.TodayTargetList
import android.habittracker.model.firebase.dbs_realtime.FirebaseDatabaseRealtimeClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashBoardViewModel(
    private val firebaseAuthClient: FirebaseAuthClient,
    private val firebaseDatabaseRealtimeClient: FirebaseDatabaseRealtimeClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    private val _habitList = MutableStateFlow(HabitDataList())
    val habitList = _habitList.asStateFlow()

    private val _todayTargets = MutableStateFlow(TodayTargetList())
    val todayTargets = _todayTargets.asStateFlow()

    private val _activityProgress = MutableStateFlow(ActivityProgressList())
    val activityProgress = _activityProgress.asStateFlow()

    private val _latestActivityList = MutableStateFlow(LatestActivityList())
    val latestActivityList = _latestActivityList.asStateFlow()

    init {
        getHabitData()
        setHabitsData()
        setTodayTargets()
        setActivityProgress()
        setLatestActivity()
    }

    private fun getHabitData() {
        viewModelScope.launch {
                val result = firebaseDatabaseRealtimeClient.getHabitData(firebaseAuthClient.getSignInUser()?.userId.toString())
                _habitList.value = result
            }


    }

    private fun setHabitsData() {
        viewModelScope.launch {
                firebaseDatabaseRealtimeClient.setHabitData(
                    HabitsData(
                    habitId = "5",
                    name = "Walking",
                    progress = 100,
                    icon = R.drawable.walking_icon

                ),
                    userId = firebaseAuthClient.getSignInUser()?.userId.toString()
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
            _todayTargets.value = TodayTargetList(
                listOf(
                    TodayTargetData(
                        target = "8L",
                        toDo = "Water Intake",
                        icon = R.drawable.water_intake_today_target
                    ),
                    TodayTargetData(
                        target = "2400",
                        toDo = "Foot Steps",
                        icon = R.drawable.foot_steps_today_target
                    )
                )
            )
        }
    }

    private fun setActivityProgress() {
        viewModelScope.launch {
            _activityProgress.value = ActivityProgressList(
                listOf(
                    ActivityProgressData(
                        day = "Sunday",
                        progress = 70,
                    ),
                    ActivityProgressData(
                        day = "Monday",
                        progress = 90,
                    ),
                    ActivityProgressData(
                        day = "Tuesday",
                        progress = 50,
                    ),
                    ActivityProgressData(
                        day = "Wednesday",
                        progress = 80,
                    ),
                    ActivityProgressData(
                        day = "Thursday",
                        progress = 100,
                    ),
                    ActivityProgressData(
                        day = "Friday",
                        progress = 30,
                    ),
                    ActivityProgressData(
                        day = "Saturday",
                        progress = 70,
                    )
                )
            )
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