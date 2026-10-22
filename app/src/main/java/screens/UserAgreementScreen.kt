package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practicum.playlist_maker_android_dmitrikovvitaly.R

@Composable
fun DrawUserAgreementScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Row(
            modifier = Modifier.statusBarsPadding().fillMaxWidth().height(56.dp).padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }

            Text(
                text = stringResource(R.string.agreement),
                color = Color.Black,
                fontSize = 22.sp,
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "https://yandex.ru/legal/practicum_offer/",
            modifier = Modifier.padding(start = 16.dp, top = 30.dp)
        )

    }

}