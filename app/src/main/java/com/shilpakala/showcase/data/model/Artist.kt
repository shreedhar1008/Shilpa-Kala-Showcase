package com.shilpakala.showcase.data.model

data class Artist(
    val id: String,
    val name: String,
    val bio: String,
    val profileImageUrl: String,
    val specialty: String,
    val location: String,
    val whatsappNumber: String = "+919876543210"
)
