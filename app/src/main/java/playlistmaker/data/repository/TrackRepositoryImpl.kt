package playlistmaker.data.repository

import kotlinx.coroutines.delay
import playlistmaker.data.NetworkClient
import playlistmaker.data.dto.TrackDto
import playlistmaker.data.dto.TracksSearchRequest
import playlistmaker.data.dto.TracksSearchResponse
import playlistmaker.domain.api.TracksRepository
import playlistmaker.domain.model.Track

class TracksRepositoryImpl(private val networkClient: NetworkClient, private val storage: Storage) : TracksRepository {



    override suspend fun searchTracksContains(expression: String): List<Track> {
        // Имитация обращения по сети. Задержка 3 секунды
        delay(3000)

        // Фильтруем локальный список tracksDto по вхождению expression
        return storage.tracksDto
            .filter { track ->
                track.trackName.contains(expression, ignoreCase = true) ||
                        track.artistName.contains(expression, ignoreCase = true)
            }
            .map { trackDto ->
                val seconds = trackDto.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d:%02d".format(minutes, seconds % 60)
                Track(trackDto.id, trackDto.trackName, trackDto.artistName, trackTime)
            }
    }

    override fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))
        if (response.resultCode == 200) { // успешный запрос
            return (response as TracksSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(it.id, it.trackName, it.artistName, trackTime)
            }
        } else {
            return emptyList()
        }
    }
}