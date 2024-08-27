package android.habittracker.ui.screen.dashboard.component

import android.habittracker.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun CustomDateScroller(
    modifier: Modifier = Modifier
) {
    val dates = getUpComingDates(31)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        LazyRow(
            state = listState,
            modifier = modifier
                .width(300.dp)
                .padding( end = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(dates) { index, date ->
                if (index == 0) {
                    DateBox(date = date, isToday = true)
                } else {
                    DateBox(date = date)
                }
            }

        }
        Icon(
            modifier = modifier
                .size(25.dp)
                .clickable {
                    val itemsToScroll = 4
                    val targetIndex = (listState.firstVisibleItemIndex + itemsToScroll).coerceAtMost(dates.size - 1) // Ensure index is within bounds

                    coroutineScope.launch {
                     listState.animateScrollToItem(targetIndex)
                    }
                }
            ,
            tint = Color.White,
            painter = painterResource(id = R.drawable.side),
            contentDescription = "tombol kembali"
        )

    }
}

private fun getUpComingDates(count: Int): List<Pair<String, String>> {
    val dayName = SimpleDateFormat("EEE", Locale.getDefault())
    val day = SimpleDateFormat("dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    val dates = mutableListOf<Pair<String, String>>()

    for (i in 0 until count) {
        dates.add(Pair(dayName.format(calendar.time), day.format(calendar.time)))
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return dates
}

@Composable
fun DateBox(
    modifier: Modifier = Modifier,
    date: Pair<String, String>,
    isToday: Boolean = false
) {
    if (isToday) {
        Box(
            modifier = modifier
                .size(53.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF7472FF), // Start color
                            Color(0xFF7F45FF)  // End color
                        )
                    )
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(modifier =modifier.padding(vertical = 1.dp),
                    text = date.first,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                )
                Text(
                    text = date.second,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.White,

                    )
                )
            }
        }
    } else {
        Box(
            modifier = modifier
                .size(53.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(modifier =modifier.padding(vertical = 1.dp),
                    text = date.first,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                )
                Text(
                    text = date.second,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.White,

                        )
                )
            }
        }
    }
}