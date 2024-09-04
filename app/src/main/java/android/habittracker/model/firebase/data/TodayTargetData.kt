package android.habittracker.model.firebase.data

data class TodayTargetList(
    val listTodayTarget : List<TodayTargetData>? = null
)

data class TodayTargetData(
    val todayTargetId: String? = null,
    val target : String? = null,
    val toDo : String? = null,
    val icon : Int? = null,
)


