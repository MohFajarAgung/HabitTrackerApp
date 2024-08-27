package android.habittracker.model.firebase.data

import android.habittracker.R
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

data class HabitDataList(
    val data: List<HabitsData>? = null,
)

data class HabitsData(
    val habitId : String?,
    val habit : String?,
    val finish : Int?,
    val icon : Int?,
)
