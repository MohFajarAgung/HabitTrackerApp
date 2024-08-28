package android.habittracker.ui.screen.dashboard

import android.habittracker.R
import android.habittracker.model.firebase.data.ActivityProgressData
import android.habittracker.model.firebase.data.ActivityProgressList
import android.habittracker.model.firebase.data.HabitDataList
import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.model.firebase.data.TodayTargetData
import android.habittracker.model.firebase.data.TodayTargetList
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel : ViewModel() {

    private val _habitList = MutableStateFlow(HabitDataList())
    val habitList = _habitList.asStateFlow()

    private val _todayTargets = MutableStateFlow(TodayTargetList())
    val todayTargets = _todayTargets.asStateFlow()

    private val _activityProgress = MutableStateFlow(ActivityProgressList())
    val activityProgress = _activityProgress.asStateFlow()

    init {
        setHabitsData()
        setTodayTargets()
        setActivityProgress()
    }
    private fun setHabitsData(){
        viewModelScope.launch {
            _habitList.value = HabitDataList(listOf(
                HabitsData(
                    habitId = "1",
                    habit = "Drinking Water",
                    finish = 25,
                    icon = R.drawable.drinking_water_icon
                ),
                HabitsData(
                    habitId = "2",
                    habit = "Cycling",
                    finish = 50,
                    icon = R.drawable.cycling_icon

                ),
                HabitsData(
                    habitId = "3",
                    habit = "Water",
                    finish = 75,
                    icon = R.drawable.water_icon

                ),
                HabitsData(
                    habitId = "4",
                    habit = "Walking",
                    finish = 100,
                    icon = R.drawable.walking_icon

                ),
            ))
        }
    }

    private fun setTodayTargets(){
        viewModelScope.launch {
            _todayTargets.value = TodayTargetList(listOf(
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
            ))
        }
    }

    private fun setActivityProgress(){
        viewModelScope.launch {
            _activityProgress.value = ActivityProgressList(listOf(
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
            ))
        }
    }
}