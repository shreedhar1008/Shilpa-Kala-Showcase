package com.shilpakala.showcase.data.repository

import com.shilpakala.showcase.data.local.SampleDataProvider
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtistRepository @Inject constructor(
    private val sampleDataProvider: SampleDataProvider
) {

    fun getArtists(): Flow<List<Artist>> = flow {
        emit(sampleDataProvider.artists)
    }

    fun getArtistById(id: String): Flow<Artist?> = flow {
        emit(sampleDataProvider.artists.find { it.id == id })
    }

    fun getArtworkById(id: String): Flow<Artwork?> = flow {
        emit(sampleDataProvider.artworks.find { it.id == id })
    }

    fun getArtworksByArtist(artistId: String): Flow<List<Artwork>> = flow {
        emit(sampleDataProvider.artworks.filter { it.artistId == artistId })
    }

    fun getAllArtworks(): Flow<List<Artwork>> = flow {
        emit(sampleDataProvider.artworks)
    }

    fun getWipArtworks(): Flow<List<Artwork>> = flow {
        emit(sampleDataProvider.artworks.filter { it.isWorkInProgress })
    }

    fun getArtworksByStyle(style: String): Flow<List<Artwork>> = flow {
        emit(sampleDataProvider.artworks.filter { it.heritageStyle.contains(style, ignoreCase = true) })
    }
}
