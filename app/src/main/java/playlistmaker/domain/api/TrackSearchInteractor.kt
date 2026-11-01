package playlistmaker.domain.api

import playlistmaker.domain.model.Track

interface TrackSearchInteractor {
    fun searchTracks(expression: String): List<Track>
}