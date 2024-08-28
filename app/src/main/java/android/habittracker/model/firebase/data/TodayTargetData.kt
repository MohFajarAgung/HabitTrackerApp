package android.habittracker.model.firebase.data

data class TodayTargetList(
    val listTodayTarget : List<TodayTargetData>? = null
)

data class TodayTargetData(
    val target : String?,
    val toDo : String?,
    val icon : Int?,
)


