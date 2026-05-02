package com.shilpakala.showcase.data.model

data class Artwork(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val artistId: String,
    val category: ArtworkCategory,
    val material: String,
    val dimensions: String,
    val priceRange: String,
    val heritageStyle: String,
    val isWorkInProgress: Boolean = false,
    val completionPercentage: Int = 0
)

enum class ArtworkCategory {
    SCULPTURE, PAINTING, POTTERY, TEXTILE, METALWORK, OTHER
}
