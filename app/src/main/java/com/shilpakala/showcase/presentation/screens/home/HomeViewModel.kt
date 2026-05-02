package com.shilpakala.showcase.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ArtistRepository
) : ViewModel() {

    private val _featuredArtists = MutableStateFlow<List<Artist>>(emptyList())
    val featuredArtists: StateFlow<List<Artist>> = _featuredArtists.asStateFlow()

    private val _recentArtworks = MutableStateFlow<List<Artwork>>(emptyList())
    val recentArtworks: StateFlow<List<Artwork>> = _recentArtworks.asStateFlow()

    private val _wipArtworks = MutableStateFlow<List<Artwork>>(emptyList())
    val wipArtworks: StateFlow<List<Artwork>> = _wipArtworks.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getArtists().collect { artists ->
                _featuredArtists.value = artists.take(4)
            }
        }
        viewModelScope.launch {
            repository.getAllArtworks().collect { artworks ->
                _recentArtworks.value = artworks.filter { !it.isWorkInProgress }.take(8)
                _wipArtworks.value = artworks.filter { it.isWorkInProgress }
            }
        }
    }
}
