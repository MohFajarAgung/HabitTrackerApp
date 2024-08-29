package android.habittracker.model.firebase.data

data class LatestActivityList(
    val data : List<LatestActivityData>? = null
)

data class LatestActivityData(
    val title : String?,
    val timeAgo : String?,
    val image : Int?,
)