package android.habittracker.model.firebase.data

data class HabitDataList(
    val data: List<HabitsData>? = null,
)

data class HabitsData(
    val habitId : String?,
    val habit : String?,
    val progress : Int?,
    val icon : Int?,
)
