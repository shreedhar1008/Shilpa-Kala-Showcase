package com.shilpakala.showcase.domain.usecase

import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.repository.ArtistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(
    private val repository: ArtistRepository
) {
    operator fun invoke(): Flow<List<Artist>> = repository.getArtists()
}
