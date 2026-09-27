package com.practicum.playlist_maker_android_dmitrikovvitaly

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.practicum.playlist_maker_android_dmitrikovvitaly.ui.theme.PlaylistmakerandroidDmitrikovVitalyTheme
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawMainScreen()
        }
    }


    @Preview(showSystemUi = false)
    @Composable
    fun DrawMainScreen(){
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


                MenuItem(R.drawable.search, stringResource(R.string.search_text), onClick = { })

                MenuItem(R.drawable.playlists, stringResource(R.string.playlists_text), onClick = { })

                MenuItem(R.drawable.favourite, stringResource(R.string.favourites_text), onClick = { })

                MenuItem(R.drawable.settings, stringResource(R.string.settings_text), onClick = { })



            }
        }
    }



    @Composable
    fun MenuItem(
        iconResId: Int,
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
                painter = painterResource(iconResId),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )


            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = null
            )

        }
    }





}