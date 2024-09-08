package android.habittracker.model.firebase.data

data class ActivityProgressList(
    val data : List<ActivityProgressData>? = null,
)
data class ActivityProgressData(
    val day : String?,
    val progress : Int?,
    val isTodayOrCurrentMonthly : Boolean = false
)
