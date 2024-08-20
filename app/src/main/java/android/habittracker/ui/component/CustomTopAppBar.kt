package android.habittracker.ui.component

import android.habittracker.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    TopAppBar(
        modifier = modifier.padding(top = 20.dp),
        navigationIcon = {
            OutlinedButton(
                onClick = {
                          onClick()
                },
                modifier = Modifier.padding( 18.dp).size(55.dp),  //avoid the oval shape
                shape = CircleShape,
                border = BorderStroke(1.dp, Color(0xFFEBEAEC)),
                contentPadding = PaddingValues(0.dp),  //avoid the little icon
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White,contentColor = Color(0xFF3F414E))
            ) {
                Icon(
                    modifier = modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "tombol kembali"
                )
            }
        },
        title = {

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )

}