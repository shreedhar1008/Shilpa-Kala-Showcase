package com.shilpakala.showcase.domain.usecase

import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.data.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtworksByArtistUseCase @Inject constructor(
    private val repository: ArtistRepository
) {
    operator fun invoke(artistId: String): Flow<List<Artwork>> = repository.getArtworksByArtist(artistId)
}
