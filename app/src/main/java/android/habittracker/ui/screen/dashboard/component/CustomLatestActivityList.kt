package android.habittracker.ui.screen.dashboard.component

import android.habittracker.model.firebase.data.LatestActivityData
import android.habittracker.ui.screen.dashboard.DashBoardViewModel
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomLatestActivityList(
    modifier: Modifier = Modifier,
    dashBoardViewModel: DashBoardViewModel
) {

    val latestActivity by dashBoardViewModel.latestActivityList.collectAsState()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .padding(top = 10.dp , bottom = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Latest Activity",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                "See more",
                modifier = modifier
                    .padding(end = 15.dp)
                    .clickable {

                },
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        LazyColumn(
            modifier = modifier.fillMaxWidth().height(550.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(latestActivity.data!!) {
                LatestActivityBox(data = it)
            }
        }
    }
}

@Composable
fun LatestActivityBox(
    modifier: Modifier = Modifier,
    data: LatestActivityData
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { }
    ) {
        Row(
            modifier = modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier.size(60.dp).padding(horizontal = 10.dp),
                painter = painterResource(id = data.image!!),
                contentDescription = "Latest Activity Image"
            )
            Spacer(modifier = modifier.width(10.dp))
            Column {
                Text(text = data.title!!)
                Text(text = data.timeAgo!!)
            }
        }
    }
}