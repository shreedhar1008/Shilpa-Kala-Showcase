package com.shilpakala.showcase.presentation.navigation

sealed class Screen(val route: String) {
    object Home          : Screen("home")
    object Gallery       : Screen("gallery")
    object Heritage      : Screen("heritage")
    object Timeline      : Screen("timeline")
    object ArtworkDetail : Screen("artwork_detail/{artworkId}") {
        fun createRoute(id: String) = "artwork_detail/$id"
    }
    object ArtistProfile : Screen("artist_profile/{artistId}") {
        fun createRoute(id: String) = "artist_profile/$id"
    }
}
