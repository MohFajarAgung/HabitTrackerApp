package android.habittracker.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LoginWithEmailOption(
    val logIn: String = "OR LOG IN WITH EMAIL",
    val SignUp: String = "OR SIGN UP WITH EMAIL"
)

@Composable
fun LoginWithEmail(
    modifier: Modifier = Modifier,
    textHead : String,
){
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier.padding(vertical = 40.dp),
            text = textHead,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA1A4B2),
                shadow = Shadow(
                    color = Color(0xFF000000).copy(alpha = 0.25f),
                    offset = Offset(0f, 4f),
                    blurRadius = 4f
                )
            )
        )
        if(textHead == LoginWithEmailOption().SignUp){
        TextField(value = username, onValueChange = { username = it} )
        }

        TextField(value = emailText, onValueChange = { emailText = it} )

        TextField(value = passwordText, onValueChange = { passwordText = it} )


    }
}