package android.habittracker.ui.screen.dashboard

import android.habittracker.R
import android.habittracker.model.firebase.data.HabitDataList
import android.habittracker.model.firebase.data.HabitsData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel : ViewModel() {

    private val _habitList = MutableStateFlow(HabitDataList())
    val habitList = _habitList.asStateFlow()

    init {
        setHabitsData()
    }
    fun setHabitsData(){
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
}