package com.shilpakala.showcase.presentation.screens.artist_profile

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shilpakala.showcase.presentation.navigation.Screen
import com.shilpakala.showcase.presentation.screens.home.ArtworkCard
import com.shilpakala.showcase.presentation.theme.DeepCharcoal
import com.shilpakala.showcase.presentation.theme.MutedSage
import com.shilpakala.showcase.presentation.theme.WarmGold
import com.shilpakala.showcase.presentation.theme.WhatsAppGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistProfileScreen(
    navController: NavController,
    artistId: String,
    viewModel: ArtistProfileViewModel = hiltViewModel()
) {
    val artist by viewModel.artist.collectAsState()
    val artworks by viewModel.artworks.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(artistId) {
        viewModel.loadArtist(artistId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        artist?.let { art ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Header
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Surface(
                        modifier = Modifier.size(126.dp),
                        shape = CircleShape,
                        border = BorderStroke(3.dp, WarmGold)
                    ) {
                        AsyncImage(
                            model = art.profileImageUrl,
                            contentDescription = art.name,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = art.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MutedSage,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = art.location,
                            color = MutedSage,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    AssistChip(
                        onClick = { },
                        label = { Text("✓ Verified Master Shilpi") },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFFE8F5E9),
                            labelColor = Color(0xFF2E7D32)
                        ),
                        border = null,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Stats Row
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem("25+", "Years")
                        VerticalDivider(modifier = Modifier.height(40.dp))
                        StatItem(art.specialty, "Style")
                    }
                }

                // About Section
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        var expanded by remember { mutableStateOf(false) }
                        Text(
                            "About",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = art.bio,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = if (expanded) Int.MAX_VALUE else 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .animateContentSize()
                        )
                        Text(
                            text = if (expanded) "Read less" else "Read more",
                            color = WarmGold,
                            modifier = Modifier
                                .clickable { expanded = !expanded }
                                .padding(vertical = 4.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                // Portfolio Header
                item {
                    Text(
                        text = "Portfolio (${artworks.size} works)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Portfolio Grid
                item {
                    Box(modifier = Modifier.height(500.dp)) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalItemSpacing = 12.dp
                        ) {
                            items(artworks) { artwork ->
                                ArtworkCard(artwork) {
                                    navController.navigate(Screen.ArtworkDetail.createRoute(artwork.id))
                                }
                            }
                        }
                    }
                }

                // WhatsApp Button
                item {
                    Button(
                        onClick = {
                            val uri = Uri.parse("https://wa.me/919876543210?text=Namaste ${art.name}, I saw your profile on ShilpaKala Showcase.")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setPackage("com.whatsapp")
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Icon(Icons.Default.Whatsapp, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Contact ${art.name} on WhatsApp", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = DeepCharcoal,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
