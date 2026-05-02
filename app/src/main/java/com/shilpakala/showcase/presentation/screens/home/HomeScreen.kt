package com.shilpakala.showcase.presentation.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.shilpakala.showcase.data.model.Artist
import com.shilpakala.showcase.data.model.Artwork
import com.shilpakala.showcase.presentation.navigation.Screen
import com.shilpakala.showcase.presentation.theme.CreamWhite
import com.shilpakala.showcase.presentation.theme.SoftTaupe
import com.shilpakala.showcase.presentation.theme.WarmGold
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val featuredArtists by viewModel.featuredArtists.collectAsState()
    val recentArtworks by viewModel.recentArtworks.collectAsState()
    val wipArtworks by viewModel.wipArtworks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Shilpa-Kala Showcase",
                            style = MaterialTheme.typography.headlineSmall,
                            color = WarmGold
                        )
                        Text(
                            "Celebrating Ancient Craft",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CreamWhite)
            )
        },
        containerColor = CreamWhite
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Hero Pager
            item {
                HeroPager(featuredArtists) { artistId ->
                    navController.navigate(Screen.ArtistProfile.createRoute(artistId))
                }
            }

            // Featured Artists
            item {
                SectionHeader("Featured Artists")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(featuredArtists) { artist ->
                        ArtistCircleCard(artist) {
                            navController.navigate(Screen.ArtistProfile.createRoute(artist.id))
                        }
                    }
                }
            }

            // Latest Works
            item {
                SectionHeader("Latest Works")
                Box(modifier = Modifier.height(450.dp)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(recentArtworks) { artwork ->
                            ArtworkCard(artwork) {
                                navController.navigate(Screen.ArtworkDetail.createRoute(artwork.id))
                            }
                        }
                    }
                }
            }

            // Work In Progress
            item {
                SectionHeader("Work In Progress")
            }
            items(wipArtworks) { wip ->
                WipCard(wip)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeroPager(artists: List<Artist>, onExploreClick: (String) -> Unit) {
    val pagerState = rememberPagerState()

    if (artists.isNotEmpty()) {
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % 3.coerceAtMost(artists.size),
                    animationSpec = tween(600)
                )
            }
        }

        Column {
            HorizontalPager(
                count = 3.coerceAtMost(artists.size),
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) { page ->
                val artist = artists[page]
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        model = artist.profileImageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(artist.name, color = Color.White, style = MaterialTheme.typography.titleLarge)
                        Button(
                            onClick = { onExploreClick(artist.id) },
                            colors = ButtonDefaults.buttonColors(containerColor = WarmGold),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Explore")
                        }
                    }
                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                activeColor = WarmGold,
                inactiveColor = Color.LightGray
            )
        }
    }
}

@Composable
fun ArtistCircleCard(artist: Artist, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = artist.profileImageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            artist.name,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            artist.location.split(",").last().trim(),
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        SuggestionChip(
            onClick = { },
            label = { Text(artist.specialty, fontSize = 10.sp) },
            colors = SuggestionChipDefaults.suggestionChipColors(labelColor = WarmGold),
            modifier = Modifier.height(24.dp)
        )
    }
}

@Composable
fun ArtworkCard(artwork: Artwork, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SoftTaupe),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box {
            AsyncImage(
                model = artwork.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    artwork.title,
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1
                )
                Text(
                    "by ${artwork.artistId}", // Ideally we'd have artist name here
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            // Availability Dot
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (artwork.isWorkInProgress) Color(0xFFFFA500) else Color(0xFF25D366))
            )
        }
        PaddingValues(8.dp).let {
            Column(modifier = Modifier.padding(8.dp)) {
                AssistChip(
                    onClick = { },
                    label = { Text(artwork.heritageStyle, fontSize = 10.sp) },
                    colors = AssistChipDefaults.assistChipColors(labelColor = WarmGold),
                    modifier = Modifier.height(24.dp)
                )
            }
        }
    }
}

@Composable
fun WipCard(artwork: Artwork) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = SoftTaupe.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(artwork.title, style = MaterialTheme.typography.titleMedium)
            Text("In Progress", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = artwork.completionPercentage / 100f,
                modifier = Modifier.fillMaxWidth(),
                color = WarmGold,
                trackColor = SoftTaupe
            )
            Text(
                "${artwork.completionPercentage}% Complete",
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.labelSmall,
                color = WarmGold
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        title,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}
