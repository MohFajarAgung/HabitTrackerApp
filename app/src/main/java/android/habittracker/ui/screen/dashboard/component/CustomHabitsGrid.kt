package android.habittracker.ui.screen.dashboard.component

import android.habittracker.R
import android.habittracker.model.firebase.data.HabitsData
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
    val latestActivity by dashBoardViewModel.latestActivityList.collectAsState()


    habits.habits?.let {
        var height = 500
        if (it.size <= 2) {
            height = 250
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(height.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(it) { index, data ->
//                tampilkan cukup sampai 4 grid saja
                if (index < 4) {
//                    munculkan tombol pada grid terakhir jika habits lebih dari 4
                    if (index == 3 && it.size > 4) {
                        HabitBox(data = data, buttonAppear = true)
                    } else {
                        HabitBox(data = data)
                    }
                }
            }

        }

    }


}

@Composable
fun HabitBox(
    modifier: Modifier = Modifier,
    data: HabitsData,
    buttonAppear: Boolean = false
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
                val sweepAngle = (data.progress!!.toFloat() / 100) * 360f

//             Ketika data.finish berubah dan jika bernilai >= 40 maka tambah nilai sweepAngleBlue
                LaunchedEffect(data.progress) {
                    var add = 50
                    if (data.progress >= 40) {
                        for (i in 40..100 step 10) {
                            if (data.progress in i..i + 10) {
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
                text = data.name!!,
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
                text = data.progress!!.toString() + "%",
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

//    jika buttonAppear == true, maka tambil kan tombol
    if (buttonAppear) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(255.dp), contentAlignment = Alignment.BottomEnd
        ) {

            OutlinedButton(
                onClick = {
                },
                modifier = Modifier
                    .padding(end = 5.dp)
                    .size(60.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, Color(0xFF92A3FD)),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF92A3FD),
                    contentColor = Color.White
                )
            ) {
                Icon(
                    modifier = modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.baseline_add),
                    contentDescription = "tombol kembali"
                )
            }
        }
    }

}
