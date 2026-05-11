package com.shilpakala.showcase.navigation

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shilpakala.showcase.ui.components.BottomNavBar
import com.shilpakala.showcase.ui.screens.*
import com.shilpakala.showcase.ui.theme.Primary
import com.shilpakala.showcase.viewmodel.ShilpaViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ShilpaViewModel = viewModel()
    val context = LocalContext.current
    var showAboutDialog by remember { mutableStateOf(false) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute by remember {
        derivedStateOf { navBackStackEntry?.destination?.route }
    }

    val showBottomBar by remember {
        derivedStateOf { currentRoute in listOf("home", "heritage", "gallery") }
    }

    // About Dialog
    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            confirmButton = {
                TextButton(onClick = { showAboutDialog = false }) {
                    Text("Close", color = Primary, fontWeight = FontWeight.SemiBold)
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🪨 Shilpa-Kala Showcase",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF8B4513),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Version 1.0.0  •  Made in India",
                        fontSize = 11.sp,
                        color = Color(0xFF9E9E9E),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Divider(color = Color(0xFFDAA520), thickness = 1.dp)
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "📖 Our Mission",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF3E2723)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Shilpa-Kala Showcase exists to tell the world that these master craftsmen exist. Their art is alive, their skills are unmatched, and their stories deserve to be heard beyond their villages.",
                        fontSize = 13.sp,
                        color = Color(0xFF5D4037),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "💡 Why This App Exists",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF3E2723)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Master craftsmen from villages like Shivarapatna create world-class sculptures — yet most of the world has never heard their names.\n\nThis app gives them a face, a voice, and a digital identity they never had before.",
                        fontSize = 13.sp,
                        color = Color(0xFF5D4037),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "🌍 Preserving a Living Heritage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF3E2723)
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(text = "•  Showcasing rare styles like Hoysala, Dravidian and Chalukya to the world", fontSize = 13.sp, color = Color(0xFF5D4037), lineHeight = 19.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(text = "•  Documenting the journey of stone becoming sacred art for future generations", fontSize = 13.sp, color = Color(0xFF5D4037), lineHeight = 19.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(text = "•  Making sure this 500-year-old tradition is never forgotten or left undiscovered", fontSize = 13.sp, color = Color(0xFF5D4037), lineHeight = 19.sp)
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "✅ Our Commitment",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF3E2723)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "We are not a marketplace. We are a platform of pride and awareness.\n\nEvery profile here represents a real master craftsman whose art deserves recognition. We simply make sure the world can find them, see their work, and know they exist.",
                        fontSize = 13.sp,
                        color = Color(0xFF5D4037),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(Modifier.height(16.dp))
                    Divider(color = Color(0xFFDAA520), thickness = 1.dp)
                    Spacer(Modifier.height(12.dp))

                    Text(
                        text = "\"कला अमर है।\"",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF8B4513),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Art is immortal.  —  Ancient Sanskrit Proverb",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF8D6E63),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "© 2025 Shilpa-Kala Showcase. All rights reserved.",
                        fontSize = 11.sp,
                        color = Color(0xFF9E9E9E),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "Built to celebrate the artisans of Bharat.",
                        fontSize = 11.sp,
                        color = Color(0xFF9E9E9E),
                        textAlign = TextAlign.Center
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large
        )
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute ?: "home",
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onAboutClick = { showAboutDialog = true }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(
                    viewModel = viewModel,
                    onArtistClick = { artistId -> navController.navigate("artist/$artistId") },
                    onSculptureClick = { sculptureId -> navController.navigate("sculpture/$sculptureId") },
                    onHeritageClick = { navController.navigate("heritage") },
                    onGalleryClick = { navController.navigate("gallery") }
                )
            }

            composable("gallery") {
                GalleryScreen(
                    viewModel = viewModel,
                    onSculptureClick = { sculptureId -> navController.navigate("sculpture/$sculptureId") },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "artist/{artistId}",
                arguments = listOf(navArgument("artistId") { type = NavType.StringType })
            ) { backStackEntry ->
                val artistId = backStackEntry.arguments?.getString("artistId") ?: ""
                val artist = viewModel.getArtistById(artistId)
                if (artist != null) {
                    ArtistPortfolioScreen(
                        artist = artist,
                        onBack = { navController.popBackStack() },
                        onSculptureClick = { sculptureId -> navController.navigate("sculpture/$sculptureId") },
                        onTimelineClick = { navController.navigate("timeline/$artistId") },
                        onWhatsAppClick = {
                            val url = "https://wa.me/${artist.whatsappNumber}"
                            try {
                                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                            } catch (e: Exception) {
                                Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onShareClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, "Check out ${artist.name}'s work on Shilpa-Kala Showcase!")
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share"))
                        }
                    )
                }
            }

            composable(
                route = "sculpture/{sculptureId}",
                arguments = listOf(navArgument("sculptureId") { type = NavType.StringType })
            ) { backStackEntry ->
                val sculptureId = backStackEntry.arguments?.getString("sculptureId") ?: ""
                val sculpture = viewModel.getSculptureById(sculptureId)
                if (sculpture != null) {
                    SculptureDetailScreen(
                        sculpture = sculpture,
                        onBack = { navController.popBackStack() },
                        onBuyClick = { navController.navigate("checkout/$sculptureId") }
                    )
                }
            }

            composable(
                route = "timeline/{artistId}",
                arguments = listOf(navArgument("artistId") { type = NavType.StringType })
            ) { backStackEntry ->
                val artistId = backStackEntry.arguments?.getString("artistId") ?: ""
                val artist = viewModel.getArtistById(artistId)
                if (artist != null) {
                    WorkInProgressTimelineScreen(
                        artist = artist,
                        onBack = { navController.popBackStack() }
                    )
                }
            }

            
            composable("register_artist") {
                ArtistRegistrationScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onRegistrationSuccess = { newArtistId ->
                        navController.popBackStack()
                        navController.navigate("artist/$newArtistId")
                    }
                )
            }

            composable(
                route = "add_artwork/{artistId}",
                arguments = listOf(navArgument("artistId") { type = NavType.StringType })
            ) { backStackEntry ->
                val artistId = backStackEntry.arguments?.getString("artistId") ?: ""
                AddArtworkScreen(
                    artistId = artistId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onSuccess = { navController.popBackStack() }
                )
            }

            composable(
                route = "checkout/{sculptureId}",
                arguments = listOf(navArgument("sculptureId") { type = NavType.StringType })
            ) { backStackEntry ->
                val sculptureId = backStackEntry.arguments?.getString("sculptureId") ?: ""
                val sculpture = viewModel.getSculptureById(sculptureId)
                if (sculpture != null) {
                    CheckoutScreen(
                        sculpture = sculpture,
                        onBack = { navController.popBackStack() },
                        onSuccess = { 
                            navController.popBackStack("home", inclusive = false)
                        }
                    )
                }
            }

            composable("heritage") {
                HeritageStoryScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
