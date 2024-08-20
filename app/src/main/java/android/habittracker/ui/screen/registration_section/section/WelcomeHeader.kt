package android.habittracker.ui.screen.registration_section.section

import android.habittracker.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeHeader(
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier.padding(horizontal = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_habits_icon),
            contentDescription = "Icon Main Habits",
            contentScale = ContentScale.Fit,
            modifier = modifier
                .size(150.dp)
        )
        Text(
            text = "Hi Fajar, Welcome",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFECCC),
                shadow = Shadow(
                    color = Color(0xFF000000).copy(alpha = 0.25f),
                    offset = Offset(0f,4f),
                    blurRadius = 4f
                )
            )
        )
        Text(
            text = "to Main Habits",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFFFECCC),
                shadow = Shadow(
                    color = Color(0xFF000000).copy(alpha = 0.25f),
                    offset = Offset(0f,4f),
                    blurRadius = 4f
                )
            )
        )
        Spacer(modifier = modifier.height(20.dp))
        Text(
            text = "Explore the Aapp. Find some piece of mind to achive good habits.",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFEBEAEC),
            ),
            textAlign = TextAlign.Center
        )



    }
}