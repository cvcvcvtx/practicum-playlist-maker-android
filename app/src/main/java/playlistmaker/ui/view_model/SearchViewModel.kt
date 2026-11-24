package playlistmaker.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import playlistmaker.domain.api.TracksRepository



class SearchViewModel(
    private val trackRepository: TracksRepository
) : ViewModel() {
    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()

    fun searchTracks(query: String) {
        viewModelScope.launch {
            _searchScreenState.update { SearchState.Searching }
            try {
                val result = trackRepository.searchTracksContains(query)
                if (result.isNotEmpty()) {
                    _searchScreenState.update { SearchState.Success(result) }
                } else {
                    _searchScreenState.update { SearchState.Fail("Ничего не найдено") }
                }
            } catch (e: Exception) {
                _searchScreenState.update { SearchState.Fail("Ошибка при поиске: ${e.message}") }
            }
        }
    }

    fun clearSearch() {
        _searchScreenState.update { SearchState.Initial }
    }
}