package playlistmaker.domain.api

import playlistmaker.domain.model.Track

interface TracksRepository {
    fun searchTracks(expression: String): List<Track>
    suspend fun searchTracksContains(expression: String): List<Track>
}