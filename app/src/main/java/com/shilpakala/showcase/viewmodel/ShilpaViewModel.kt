package com.shilpakala.showcase.viewmodel

import androidx.lifecycle.ViewModel
import com.shilpakala.showcase.data.DataRepository
import com.shilpakala.showcase.data.models.Artist
import com.shilpakala.showcase.data.models.HeritageStyle
import com.shilpakala.showcase.data.models.Sculpture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShilpaViewModel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _artists = MutableStateFlow<List<Artist>>(DataRepository.artists)
    val artists: StateFlow<List<Artist>> = _artists.asStateFlow()

    private val _featuredWorks = MutableStateFlow<List<Sculpture>>(DataRepository.getFeaturedSculptures())
    val featuredWorks: StateFlow<List<Sculpture>> = _featuredWorks.asStateFlow()

    private val _heritageStyles = MutableStateFlow(DataRepository.heritageStyles)
    val heritageStyles: StateFlow<List<HeritageStyle>> = _heritageStyles.asStateFlow()

    private val _selectedArtist = MutableStateFlow<Artist?>(null)
    val selectedArtist: StateFlow<Artist?> = _selectedArtist.asStateFlow()

    private val _selectedSculpture = MutableStateFlow<Sculpture?>(null)
    val selectedSculpture: StateFlow<Sculpture?> = _selectedSculpture.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _artists.value = DataRepository.artists
            _featuredWorks.value = DataRepository.getFeaturedSculptures()
        } else {
            _artists.value = DataRepository.artists.filter {
                it.name.contains(query, ignoreCase = true) || 
                it.specialty.contains(query, ignoreCase = true) ||
                it.location.contains(query, ignoreCase = true)
            }
            _featuredWorks.value = DataRepository.getFeaturedSculptures().filter {
                it.title.contains(query, ignoreCase = true) || 
                it.style.contains(query, ignoreCase = true) ||
                it.material.contains(query, ignoreCase = true)
            }
        }
    }

    fun selectArtist(artistId: String) {
        _selectedArtist.value = DataRepository.getArtistById(artistId)
    }

    fun selectSculpture(sculptureId: String) {
        _selectedSculpture.value = DataRepository.getSculptureById(sculptureId)
    }

    fun getArtistById(artistId: String): Artist? = DataRepository.getArtistById(artistId)

    fun getSculptureById(sculptureId: String): Sculpture? = DataRepository.getSculptureById(sculptureId)

    fun getArtistBySculptureId(sculptureId: String): Artist? = DataRepository.getArtistBySculptureId(sculptureId)

    fun addSculpture(sculpture: Sculpture) {
        DataRepository.addSculpture(sculpture)
        setSearchQuery(_searchQuery.value)
    }

    fun registerArtist(artist: Artist) {
        DataRepository.registerArtist(artist)
        setSearchQuery(_searchQuery.value)
    }
}
