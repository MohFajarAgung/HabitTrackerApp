package android.habittracker.ui.screen.dashboard.component

import android.habittracker.R
import android.habittracker.model.firebase.data.ActivityProgressData
import android.habittracker.ui.screen.DashBoardScreenData
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActivityProgress(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel
) {

    val activityProgress by dashBoardViewModel.activityProgress.collectAsState()
    Box(

    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Activity Progress",
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color.White,
                    )
                )

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF9DCEFFF),  // Start color
                                    Color(0xFF92A3FD), // End color
                                )
                            )
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center//avoid the oval shape
                ) {
                    Icon(
                        modifier = modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.baseline_add),
                        tint = Color.White,
                        contentDescription = "tombol kembali"

                    )
                }

            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(15.dp),
            ) {
                activityProgress.data?.let {

                    for (i in 0..it.size - 1) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(12.dp),  // Padding di sekitar Column jika diperlukan
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val height =
                                (activityProgress.data!![i].progress!!.toFloat() / 100f) * 150f
                            Row(
                                modifier.height(150.dp),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height.toInt().dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(
                                                    Color(0xFF72FFBB), // Start color
                                                    Color(0xFF3CFE38)  // End color
                                                )
                                            )
                                        )
                                )

                            }
                            Spacer(modifier = modifier.height(10.dp))
                            Text(
                                text = activityProgress.data!![i].day!!.take(3),
                                maxLines = 1,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                )
                            )
                        }
                    }
                }
            }


        }
    }
}

