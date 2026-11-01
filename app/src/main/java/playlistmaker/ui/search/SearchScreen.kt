package playlistmaker.ui.search


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practicum.playlist_maker_android_dmitrikovvitaly.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import playlistmaker.domain.model.Track
import playlistmaker.ui.view_model.SearchState
import playlistmaker.ui.view_model.SearchViewModel




@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    navigateToDetailScreen: (Track) -> Unit,
    navigateBack: () -> Unit
) {
    val screenState by searchViewModel.searchScreenState.collectAsState()
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current



    Box(modifier = Modifier.fillMaxSize().background(Color.White)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()

        ) {


            Row(
                modifier = Modifier.statusBarsPadding().fillMaxWidth().height(56.dp).padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { navigateBack() },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }

                Text(
                    text = stringResource(R.string.search_text),
                    color = Color.Black,
                    fontSize = 22.sp,



                    )

                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }


            OutlinedTextField(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray
                ),
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                placeholder = {
                    Text(stringResource(R.string.search_tracks_placeholder), fontSize = 16.sp, color = Color.Gray)
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                searchViewModel.searchTracks(text)
                                focusManager.clearFocus()
                            }
                        },
                        imageVector = Icons.Filled.Search,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                text = ""
                                searchViewModel.clearSearch()
                            },
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(color = Color.Gray, fontSize = 16.sp),
                singleLine = true,
            )

            SearchResults(
                screenState = screenState,
                modifier = Modifier.padding(top = 16.dp),
                onTrackClick = navigateToDetailScreen // Передаём обновлённый параметр
            )
        }
    }

}



@Composable
private fun SearchResults(
    screenState: SearchState,
    modifier: Modifier,
    onTrackClick: (Track) -> Unit // Используем новое имя параметра
) {
    when (screenState) {
        is SearchState.Initial -> {
            // Ничего не показываем в начальном состоянии
        }

        is SearchState.Searching -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is SearchState.Success -> {
            val tracks = screenState.foundList
            if (tracks.isEmpty()) {
                InformerState(stringResource(R.string.no_tracks_found))
            } else {
                LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(tracks.size) { index ->
                        TrackListItem(
                            track = tracks[index],
                            onClick = { onTrackClick(tracks[index]) } // Вызываем навигацию по клику
                        )
                    }
                }
            }
        }

        is SearchState.Fail -> {
            InformerState(
                titleText = screenState.error, // Берём сообщение прямо из состояния
                subtitleText = stringResource(R.string.check_connection) // Дополнительный текст из ресурсов
            )
        }
    }
}




@Composable
private fun InformerState(
    titleText: String,
    subtitleText: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = titleText,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        subtitleText?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}





@Composable
private fun TrackListItem(
    track: Track,
    onClick: () -> Unit // Оставляем только обработчик одиночного клика
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Иконка трека (заглушка)
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color.LightGray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                track.trackName,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = track.artistName,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "•",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    track.trackTime,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    maxLines = 1,
                )
            }
        }
        Spacer(
            modifier = Modifier
                .padding(4.dp)
        )
        Icon(
            modifier = Modifier.size(24.dp).padding(4.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
        )
    }
}
