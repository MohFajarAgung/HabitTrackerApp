package android.habittracker.ui.screen.dashboard.component

import android.habittracker.R
import android.habittracker.model.firebase.data.TodayTargetData
import android.habittracker.model.firebase.data.TodayTargetList
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTodayTargetGrid(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel
) {

    val todayTargets by dashBoardViewModel.todayTargets.collectAsState()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .padding(20.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Today Target",
                    fontWeight = FontWeight.SemiBold,
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = Color(0xFF4D57C8),
                    )
                )

                Box(
                    modifier = Modifier.size(32.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF9DCEFFF),  // Start color
                                    Color(0xFF92A3FD), // End color
                                )
                            )
                        ).clickable {

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
            Spacer(modifier = modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(todayTargets.listTodayTarget!!) { data ->
                    TodayTargetBox(todayTargets = data)
                }
            }
        }

    }

}

@Composable
fun TodayTargetBox(
    modifier: Modifier = Modifier,
    todayTargets: TodayTargetData
) {
    todayTargets?.let {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF4D57C8))
                .clickable {

                }
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = modifier.size(40.dp),
                    painter = painterResource(id = it.icon!!),
                    contentDescription = "Today Target Image"
                )
                Spacer(modifier = modifier.width(10.dp))
                Column {
                    Text(
                        text = it.target!!,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Medium,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    )
                    Text(
                        text = it.toDo!!,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White,
                        )
                    )
                }
            }
        }

    }
}


