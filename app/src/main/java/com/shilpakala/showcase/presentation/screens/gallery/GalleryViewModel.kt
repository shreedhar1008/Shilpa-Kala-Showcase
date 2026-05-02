package com.shilpakala.showcase.presentation.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: ArtistRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedFilter = MutableStateFlow("All")
    val selectedFilter = _selectedFilter.asStateFlow()

    private val _allArtworks = repository.getAllArtworks()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val filteredArtworks: StateFlow<List<Artwork>> = combine(
        _allArtworks,
        _searchQuery,
        _selectedFilter
    ) { artworks, query, filter ->
        artworks.filter { artwork ->
            val matchesFilter = when (filter) {
                "Stone" -> artwork.material.contains("Stone", ignoreCase = true) || 
                          artwork.material.contains("Granite", ignoreCase = true)
                "Wood" -> artwork.material.contains("Wood", ignoreCase = true) || 
                         artwork.material.contains("Teak", ignoreCase = true)
                "Available" -> !artwork.isWorkInProgress
                "WIP" -> artwork.isWorkInProgress
                else -> true
            }

            val matchesSearch = artwork.title.contains(query, ignoreCase = true) ||
                    artwork.heritageStyle.contains(query, ignoreCase = true)
            
            matchesFilter && matchesSearch
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun updateFilter(filter: String) {
        _selectedFilter.value = filter
    }
}
