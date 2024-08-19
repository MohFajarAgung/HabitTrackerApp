package android.habittracker.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    textButton : String,
    onClick : () -> Unit
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(63.dp)
            .padding(horizontal = 20.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFEBEAEC)),
        onClick = {
        onClick()
    }) {
        Text(
            text = textButton,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F414E),
                shadow = Shadow(
                    color = Color(0xFF000000).copy(alpha = 0.25f),
                    offset = Offset(0f,4f),
                    blurRadius = 4f
                )
            )
        )
    }
}