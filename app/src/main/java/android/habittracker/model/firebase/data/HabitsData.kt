package android.habittracker.model.firebase.data

data class HabitDataList(
    val habits: List<HabitsData>? = null,
)

data class HabitsData (
    val habitId: String? = null,
    val icon: Int? = null,
    val name: String? = null,
    val progress: Int? = null,
)

