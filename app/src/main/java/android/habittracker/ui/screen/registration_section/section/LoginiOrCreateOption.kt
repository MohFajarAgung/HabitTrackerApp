package android.habittracker.ui.screen.registration_section.section

import android.app.Activity
import android.habittracker.R
import android.habittracker.ui.screen.registration_section.AuthViewModel
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class LoginOrCreateOptionData(
    val facebook : String = "CONTINUE WITH FACEBOOK",
    val google : String = "CONTINUE WITH GOOGLE",
)

@Composable
fun LoginOrCreateOption(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult() ,
        onResult = {result ->
            if(result.resultCode == Activity.RESULT_OK){
                authViewModel.handleSignInResult(
                    context =  context,
                    navController = navController,
                    intent  = result.data)
            }
        })
    Column {
        CustomLoginOptionButton(
            textButton = LoginOrCreateOptionData().facebook,
            iconPainter = painterResource(id = R.drawable.facebook_icon),
            bgColor = Color(0xFF7583CA),
        ) {
            Toast.makeText(context, "Facebook", Toast.LENGTH_SHORT).show()
        }
        CustomLoginOptionButton(
            textButton = LoginOrCreateOptionData().google,
            iconPainter = painterResource(id = R.drawable.google_icon),
            bgColor = Color.White,
        ) {
            authViewModel.signInWithGoogle(context){ intentSender ->
                intentSender?.let {
                    launcher.launch(IntentSenderRequest.Builder(it).build())

                }
            }
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
    Box(modifier = modifier.padding(vertical = 10.dp)) {

        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(63.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(bgColor),
            border = BorderStroke(width = 1.dp, color = Color(0xFFEBEAEC)),
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
                     LoginOrCreateOptionData().facebook-> {
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
                    )
                )
            }
        }
    }
}