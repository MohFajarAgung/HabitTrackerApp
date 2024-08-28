package android.habittracker.ui.screen.dashboard.component

import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomHabitsGrid(
    dashBoardViewModel: DashBoardViewModel
) {
    val habits by dashBoardViewModel.habitList.collectAsState()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(habits.data!!) { data ->
            HabitBox(data = data)
        }
    }


}

@Composable
fun HabitBox(
    modifier: Modifier = Modifier,
    data: HabitsData
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .clickable { }
    ) {
        Column(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                val sweepAngleBlue = remember {
                    mutableStateOf(100f)
                }
                val sweepAngle = (data.finish!!.toFloat() / 100) * 360f

//             Ketika data.finish berubah dan jika bernilai >= 40 maka tambah nilai sweepAngleBlue
                LaunchedEffect(data.finish) {
                    var add = 50
                    if (data.finish >= 40) {
                        for (i in 40..100 step 10) {
                            if (data.finish in i..i + 10) {
                                sweepAngleBlue.value += add
                                break
                            }
                            add += 50
                        }
                    }
                }
                Canvas(
                    modifier = Modifier.size(100.dp)
                ) {

                    // Draw the arc
                    drawArc(
                        color = Color(0xFF9AA2FD),
                        startAngle = -90f, // Start at the top
                        sweepAngle = 360f,
                        useCenter = false, // Don't draw a line to the center
                        style = Stroke(width = 15f)
                    )
                    drawArc(
                        color = Color(0xFF53B4FD),
                        startAngle = -50f, // Start at the top
                        sweepAngle = sweepAngleBlue.value,
                        useCenter = false, // Don't draw a line to the center
                        style = Stroke(width = 60f)
                    )
                    drawArc(
                        color = Color(0xFFFFF480),
                        startAngle = -90f, // Start at the top
                        sweepAngle = sweepAngle,
                        useCenter = false, // Don't draw a line to the center
                        style = Stroke(width = 40f)
                    )
                }
                Image(
                    modifier = modifier.size(40.dp),
                    painter = painterResource(id = data.icon!!),
                    contentDescription = "Habit Icon"
                )
            }
            Text(
                text = data.habit!!,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF4D57C8),
                    shadow = Shadow(
                        color = Color(0xFF000000).copy(alpha = 0.25f),
                        offset = Offset(0f, 4f),
                        blurRadius = 4f
                    )
                )
            )
            Text(
                text = data.finish!!.toString() + "%",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color(0xFF4D57C8),
                    shadow = Shadow(
                        color = Color(0xFF000000).copy(alpha = 0.25f),
                        offset = Offset(0f, 4f),
                        blurRadius = 4f
                    )
                )
            )
        }
    }
}
