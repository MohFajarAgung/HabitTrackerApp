package android.habittracker.ui.screen.registration_section.section

import android.habittracker.ui.component.CustomButton
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class LoginOrSignUpWithEmailOption(
    val logIn: String = "OR LOG IN WITH EMAIL",
    val SignUp: String = "OR SIGN UP WITH EMAIL"
)

@Composable
fun LoginOrSignUpWithEmail(
    modifier: Modifier = Modifier,
    textHead: String,
    navController: NavController
) {

    val textUsername = remember {
        mutableStateOf("")
    }
    val textEmail = remember {
        mutableStateOf("")
    }
    val textPassword = remember {
        mutableStateOf("")
    }

    val checked = remember {
        mutableStateOf(false)
    }

    val contex = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier.padding(top = 20.dp, bottom = 30.dp),
            text = textHead,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFA1A4B2),
            )
        )
        if (textHead == LoginOrSignUpWithEmailOption().SignUp) {
            CustomAuthTextField(
                text = "Username",
                value = textUsername.value,
                onValueChange = { textUsername.value = it }
            )
            Spacer(modifier = modifier.height(20.dp))
        }
        CustomAuthTextField(
            text = "Email address",
            value = textEmail.value,
            onValueChange = { textEmail.value = it }
        )

        Spacer(modifier = modifier.height(20.dp))
        CustomAuthTextField(
            text = "Password",
            value = textPassword.value,
            onValueChange = { textPassword.value = it }
        )

        if (textHead == LoginOrSignUpWithEmailOption().SignUp) {
            IHaveReadCheckBox(checked = checked.value) {
                checked.value = it
            }
            Spacer(modifier = modifier.height(20.dp))
            CustomButton(
                modifier = modifier.fillMaxWidth(),
                textButton = "LOG IN",
                btnColor = Color(0xFF8E97FD),
                textColor = Color(0xFFF6F1FB)
            ) {
                if (checked.value) {
                    Toast.makeText(contex, textEmail.value, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(contex, "Check the checkbox", Toast.LENGTH_SHORT).show()
                }
            }
            BottomAlreadyAndDontHaveAcc(text = "ALREADY HAVE AN ACCOUNT?", textClick = "SIGN IN") {
                navController.popBackStack()
            }
        } else {
            Spacer(modifier = modifier.height(20.dp))
            CustomButton(
                modifier = modifier.fillMaxWidth(),
                textButton = "LOG IN",
                btnColor = Color(0xFF8E97FD),
                textColor = Color(0xFFF6F1FB)
            ) {

            }
            BottomAlreadyAndDontHaveAcc(text = "DON'T HAVE AN ACCOUNT?", textClick = "SIGN UP") {
                navController.navigate("signUpScreen")
            }
        }


    }
}


@Composable
fun CustomAuthTextField(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = RoundedCornerShape(12.dp),
    value: String,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(Color(0xFFF2F3F7))
            .padding(start = 25.dp, top = 25.dp, bottom = 25.dp),
        textStyle = TextStyle(
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight(500),
            color = Color.Black,
            fontSize = 16.sp,
//            fontFamily = Poppins
        ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    // Placeholder Text
                    Text(
                        text = text,
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFA1A4B2),
                            fontSize = 16.sp,
//                            fontFamily = Poppins
                        ),
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun IHaveReadCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {


    Box(modifier = modifier.fillMaxWidth()) {
        Row(modifier = modifier.align(Alignment.CenterStart)) {
            Text(
                text = "I have read the",
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A4B2),
                    fontSize = 14.sp,
//                            fontFamily = Poppins
                ),
            )
            Text(
                text = " Privace Policy",
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF7583CA),
                    fontSize = 14.sp,
//                            fontFamily = Poppins
                ),
            )
        }
        Checkbox(
            modifier = modifier.align(Alignment.CenterEnd),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun BottomAlreadyAndDontHaveAcc(
    modifier: Modifier = Modifier,
    text: String,
    textClick: String,
    onClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                modifier = modifier
                    .padding(bottom = 60.dp),
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFA1A4B2),
                )
            )

            Text(
                modifier = modifier
                    .padding(bottom = 60.dp, start = 5.dp)
                    .clickable {
                        onClick()
                    },
                text = textClick,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8E97FD),
                )
            )
        }
    }
}
