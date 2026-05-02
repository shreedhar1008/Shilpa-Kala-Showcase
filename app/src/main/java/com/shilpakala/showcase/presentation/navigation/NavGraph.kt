package com.shilpakala.showcase.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shilpakala.showcase.presentation.screens.artist_profile.ArtistProfileScreen
import com.shilpakala.showcase.presentation.screens.artwork_detail.ArtworkDetailScreen
import com.shilpakala.showcase.presentation.screens.gallery.GalleryScreen
import com.shilpakala.showcase.presentation.screens.heritage.HeritageScreen
import com.shilpakala.showcase.presentation.screens.home.HomeScreen
import com.shilpakala.showcase.presentation.screens.timeline.TimelineScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = when (currentDestination?.route) {
        Screen.Home.route,
        Screen.Gallery.route,
        Screen.Heritage.route,
        Screen.Timeline.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Gallery.route) { GalleryScreen(navController) }
            composable(Screen.Heritage.route) { HeritageScreen(navController) }
            composable(Screen.Timeline.route) { TimelineScreen(navController) }
            composable(Screen.ArtworkDetail.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("artworkId") ?: ""
                ArtworkDetailScreen(navController, id)
            }
            composable(Screen.ArtistProfile.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("artistId") ?: ""
                ArtistProfileScreen(navController, id)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem(Screen.Home, "Home", Icons.Default.Home),
        NavigationItem(Screen.Gallery, "Gallery", Icons.Default.Image),
        NavigationItem(Screen.Heritage, "Heritage", Icons.Default.AutoStories),
        NavigationItem(Screen.Timeline, "Timeline", Icons.Default.Timeline)
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class NavigationItem(
    val screen: Screen,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
