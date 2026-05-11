package com.shilpakala.showcase.data.models

data class Artist(
    val id: String,
    val name: String,
    val location: String,
    val specialty: String,
    val bio: String,
    val profileImageUrl: String,
    val whatsappNumber: String,
    val sculptures: List<Sculpture>,
    val timelineSteps: List<TimelineStep>
)

data class Sculpture(
    val id: String,
    val productId: String,
    val title: String,
    val description: String,
    val style: String,
    val material: String,
    val priceRange: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val artistId: String = ""
)

data class TimelineStep(
    val id: String,
    val stepNumber: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)

data class HeritageStyle(
    val name: String,
    val origin: String,
    val period: String,
    val description: String,
    val characteristics: List<String>,
    val imageUrl: String
)
