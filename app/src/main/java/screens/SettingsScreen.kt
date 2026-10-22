package screens

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_dmitrikovvitaly.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController


@Composable
fun DrawSettingsScreen(navController: NavController){
    var isNightTheme by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Box(
            modifier = Modifier.statusBarsPadding().height(56.dp).fillMaxSize()
        ){
            Text(
                text = stringResource(R.string.settings_text),
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 12.dp)
            )

        }

        Spacer(
            modifier = Modifier.height(24.dp).fillMaxWidth()
        )

        Row(
            modifier = Modifier.height(60.dp).fillMaxWidth()
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .clickable(onClick = {
                    isNightTheme = !isNightTheme
                }),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Тёмная тема",
                color = Color.Black,
                fontSize = 21.sp,
                modifier = Modifier.padding(start = 16.dp).weight(1f)
            )

            Switch(
                checked = isNightTheme,
                onCheckedChange = {
                    isNightTheme = it
                },
                modifier = Modifier.padding(end = 6.dp)
            )
        }



        DrawSettingsOption(stringResource(R.string.share_app), Icons.Default.Share, onClick = { shareApplication(context) })
        DrawSettingsOption(stringResource(R.string.message_support), Icons.Default.SupportAgent, onClick = { contactDevelopers(context) })
        DrawSettingsOption(stringResource(R.string.user_agreement), Icons.AutoMirrored.Filled.ArrowForwardIos, onClick = { navController.navigate("user_agreement_screen") })


    }
}


@Composable
fun DrawSettingsOption(text: String, imageVector: ImageVector, onClick: () -> Unit){

    Row(
        modifier = Modifier.height(60.dp).fillMaxWidth()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = text,
            color = Color.Black,
            fontSize = 21.sp,
            modifier = Modifier.padding(start = 16.dp).weight(1f)
        )

        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.padding(end = 12.dp).size(24.dp)
        )
    }
}



fun shareApplication(context: Context) {

    val appPackageName = context.packageName
    val playStoreLink = "https://google.com"

    val shareMessage = "Привет! Попробуй PlaylistMaker:\n$playStoreLink"

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareMessage)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, "Поделиться через")

    context.startActivity(shareIntent)
}


fun contactDevelopers(context: Context) {

    val shareIntent = Intent(Intent.ACTION_SENDTO)
    shareIntent.data = Uri.parse("mailto:")
    shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("cvcvcvtx@gmail.com"))

    context.startActivity(shareIntent)
}
