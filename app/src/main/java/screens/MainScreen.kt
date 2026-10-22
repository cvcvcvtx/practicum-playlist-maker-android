package screens

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_dmitrikovvitaly.R


@Composable
fun DrawMainScreen(
    navigateToSearch: () -> Unit,
    navigateToPlaylists: () -> Unit,
    navigateToFavourites: () -> Unit,
    navigateToSettings: () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF3772E7))
    ){

        // header box
        Box(
            modifier = Modifier.statusBarsPadding().height(70.dp).fillMaxWidth().padding(4.dp),
        ){

            Box(
                modifier = Modifier.padding(horizontal = 12.dp).height(26.dp).fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = stringResource(R.string.title),
                    fontSize = 22.sp,
                    color = Color.White
                )
            }


        }

        // menu column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Color.White)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 0.dp
                )

        ){

            val context = LocalContext.current
            val stringSearch = stringResource(R.string.search_text)
            val stringPlaylists = stringResource(R.string.playlists_text)
            val stringFavourites = stringResource(R.string.favourites_text)
            val stringSettings = stringResource(R.string.settings_text)

            MenuItem(painterResource(R.drawable.search), stringSearch,
                onClick = {
                    //Toast.makeText(context, stringSearch, Toast.LENGTH_SHORT).show()
                    //val intent = Intent(context, SearchActivity::class.java)
                    //startActivity(intent)
                    navigateToSearch()
                }
            )

            MenuItem(painterResource(R.drawable.playlists), stringPlaylists,
                onClick = {
                    Toast.makeText(context, stringPlaylists, Toast.LENGTH_SHORT).show()
                    //navigateToPlaylists()
                }
            )

            MenuItem(painterResource(R.drawable.favourite), stringFavourites,
                onClick = {
                    Toast.makeText(context, stringFavourites, Toast.LENGTH_SHORT).show()
                    //navigateToFavourites()
                }
            )

            MenuItem(painterResource(R.drawable.settings), stringSettings,
                onClick = {
                    //Toast.makeText(context, stringSettings, Toast.LENGTH_SHORT).show()
                    //val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                    //startActivity(intent)
                    navigateToSettings();
                }
            )



        }
    }
}



@Composable
fun MenuItem(
    painter: Painter,
    name: String,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .height(66.dp)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painter,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = name,
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )


        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            tint = Color.Gray,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

    }
}




