package android.habittracker.ui.component

import android.habittracker.R
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LoginOptionData(
    val facebook : String = "CONTINUE WITH FACEBOOK",
    val google : String = "CONTINUE WITH GOOGLE",
)

@Composable
fun LoginOption(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column {
        CustomLoginOptionButton(
            textButton = LoginOptionData().facebook,
            iconPainter = painterResource(id = R.drawable.facebook_icon),
            bgColor = Color(0xFF7583CA),
        ) {
            Toast.makeText(context, "Facebook", Toast.LENGTH_SHORT).show()
        }
        CustomLoginOptionButton(
            textButton = LoginOptionData().google,
            iconPainter = painterResource(id = R.drawable.google_icon),
            bgColor = Color(0xFFEBEAEC),
        ) {
            Toast.makeText(context, "Google", Toast.LENGTH_SHORT).show()
        }


    }
}

@Composable
fun CustomLoginOptionButton(
    modifier: Modifier = Modifier,
    textButton: String,
    iconPainter: Painter,
    bgColor: Color,
    onClick: () -> Unit
) {
    Box(modifier = modifier.padding(vertical = 5.dp)) {

        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(63.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(bgColor),
            onClick = {
                onClick()
            }) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = modifier.width(10.dp))

                Image(
                    modifier = modifier.size(20.dp),
                    painter = iconPainter,
                    contentDescription = "Login Option Icon",
                )
               
                Spacer(modifier = modifier.width(50.dp))

                val colorText = when(textButton) {
                     LoginOptionData().facebook-> {
                        Color.White
                    }
                    else -> Color(0xFF3F414E)
                }
                Text(
                    text = textButton,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorText,
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
}