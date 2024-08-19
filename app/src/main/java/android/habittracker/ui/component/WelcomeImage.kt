package android.habittracker.ui.component

import android.habittracker.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun WelcomeImage(
    modifier: Modifier = Modifier
) {

    Column {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentSize() // Membuat Box seukuran konten (WelcomeImage)
        ) {
            Canvas(
                modifier = Modifier.matchParentSize() // Memastikan Canvas seukuran dengan Box ini
            ) {
                // Gambar setengah lingkaran atas dengan ukuran yang lebih besar
                val circleDiameter1 = size.minDimension * 1.5f // Mengatur diameter lingkaran lebih besar dari Box
                val circleDiameter2 = size.minDimension * 1.3f
                val circleDiameter3 = size.minDimension * 1.1f
                val circleDiameter4 = size.minDimension * 0.9f

                val offsetY = size.height * 0.30f
                drawArc(
                    color = Color(0xFF949DFF),
                    startAngle = -180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(circleDiameter1, circleDiameter1),
                    topLeft = Offset(
                        (size.width - circleDiameter1) / 2,
                        (size.height - circleDiameter1) / 2 + offsetY
                    )
                )
                drawArc(
                    color = Color(0xFF99A1FF),
                    startAngle = -180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(circleDiameter2, circleDiameter2),
                    topLeft = Offset(
                        (size.width - circleDiameter2) / 2,
                        (size.height - circleDiameter2) / 2 + offsetY
                    )
                )
                drawArc(
                    color = Color(0xFF9EA6FF),
                    startAngle = -180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(circleDiameter3, circleDiameter3),
                    topLeft = Offset(
                        (size.width - circleDiameter3) / 2,
                        (size.height - circleDiameter3) / 2 + offsetY
                    )
                )
                drawArc(
                    color = Color(0xFFA3ABFF),
                    startAngle = -180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    size = Size(circleDiameter4, circleDiameter4),
                    topLeft = Offset(
                        (size.width - circleDiameter4) / 2,
                        (size.height - circleDiameter4) / 2 + offsetY
                    )
                )

            }
            Image(
                painter = painterResource(id = R.drawable.welcome_image),
                contentDescription = "Gambar untuk Welcome screen",
                modifier = modifier
                    .size(397.dp)
            )
        }
    }

}