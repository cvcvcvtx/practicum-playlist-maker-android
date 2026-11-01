package playlistmaker.domain.impl

import playlistmaker.domain.api.TrackSearchInteractor
import playlistmaker.domain.api.TracksRepository
import playlistmaker.domain.model.Track

class TrackSearchInteractorImpl(private val repository: TracksRepository) : TrackSearchInteractor {

    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }
}