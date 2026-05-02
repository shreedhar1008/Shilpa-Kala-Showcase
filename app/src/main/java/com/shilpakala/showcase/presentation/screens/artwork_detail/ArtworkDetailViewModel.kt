package com.shilpakala.showcase.presentation.screens.artwork_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val repository: ArtistRepository
) : ViewModel() {

    private val _artwork = MutableStateFlow<Artwork?>(null)
    val artwork: StateFlow<Artwork?> = _artwork.asStateFlow()

    private val _artist = MutableStateFlow<Artist?>(null)
    val artist: StateFlow<Artist?> = _artist.asStateFlow()

    private val _relatedArtworks = MutableStateFlow<List<Artwork>>(emptyList())
    val relatedArtworks: StateFlow<List<Artwork>> = _relatedArtworks.asStateFlow()

    fun loadArtwork(artworkId: String) {
        viewModelScope.launch {
            repository.getArtworkById(artworkId).collect { art ->
                _artwork.value = art
                art?.let {
                    loadArtist(it.artistId)
                    loadRelatedArtworks(it.artistId, it.id)
                }
            }
        }
    }

    private fun loadArtist(artistId: String) {
        viewModelScope.launch {
            repository.getArtistById(artistId).collect {
                _artist.value = it
            }
        }
    }

    private fun loadRelatedArtworks(artistId: String, currentArtworkId: String) {
        viewModelScope.launch {
            repository.getArtworksByArtist(artistId).collect { list ->
                _relatedArtworks.value = list.filter { it.id != currentArtworkId }.take(4)
            }
        }
    }
}
