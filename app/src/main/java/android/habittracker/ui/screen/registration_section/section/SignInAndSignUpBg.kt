package android.habittracker.ui.screen.registration_section.section

import android.habittracker.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun SignInAndSingUpBg(
    modifier: Modifier = Modifier
){
    val configuration = LocalConfiguration.current
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.drawable.group_6800)

    Canvas(modifier = modifier.fillMaxWidth()) {
        val screenWidth = configuration.screenWidthDp.dp.toPx()
        val screenHeight = configuration.screenHeightDp.dp.toPx()
        val dstSize = IntSize(
            width = (screenWidth * 1).toInt(), // 100% of the screen width
            height = (screenHeight * 0.5).toInt() // 50% of the screen height
        )
        drawImage(
            image = imageBitmap,
            dstSize = dstSize
        )
    }
}