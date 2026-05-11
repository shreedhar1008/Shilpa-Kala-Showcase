package com.shilpakala.showcase.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shilpakala.showcase.ui.components.ArtistCard
import com.shilpakala.showcase.ui.components.FeaturedSculptureCard
import com.shilpakala.showcase.ui.theme.*
import com.shilpakala.showcase.viewmodel.ShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: ShilpaViewModel,
    onArtistClick: (String) -> Unit,
    onSculptureClick: (String) -> Unit,
    onHeritageClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onRegisterArtistClick: () -> Unit = {}
) {
    val artists by viewModel.artists.collectAsState()
    val featured by viewModel.featuredWorks.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current
    
    var isSearchActive by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchActive) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { viewModel.setSearchQuery(it) },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Search sculptures or artists...") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true
                        )
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text("🏰", fontSize = 20.sp)
                            Text("Shilpa-Kala", style = MaterialTheme.typography.displayLarge, color = OnPrimaryContainer)
                        }
                    }
                },
                actions = {
                    if (isSearchActive) {
                        IconButton(onClick = { 
                            isSearchActive = false 
                            viewModel.setSearchQuery("")
                        }) { 
                            Icon(Icons.Filled.Close, "Close Search", tint = OnPrimaryContainer) 
                        }
                    } else {
                        IconButton(onClick = { isSearchActive = true }) { 
                            Icon(Icons.Filled.Search, "Search", tint = OnPrimaryContainer) 
                        }
                    }
                    IconButton(onClick = { Toast.makeText(context, "No new notifications", Toast.LENGTH_SHORT).show() }) { 
                        Icon(Icons.Filled.Notifications, "Notifications", tint = OnPrimaryContainer) 
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryContainer)
            )
        },
        containerColor = Surface
    ) { paddingValues ->

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Hero Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAMIaCNzmD7v63G5X3mhLQx_1wBL0YmXEdcRvd5Nf4eGysvE0n2dRQq6_P6O7HtN9qoMKUkX9I-sZO9DzMRMNonqO9AaPjK5s9TvPEW3bk18X5ot6iuoyeMwxbEDcJgJxOeYPG_f0AHFjqSKKx3M6cSzH8-1ikgkB6RpWGK2PZ2DOv2vjIi0QpG61JZWiqqRMLLLQnKQ8UGBfsLUuGKpUMSNWP9-nipWrBxXJLI5ijBTQCvb3_zrZeAMGf_3oMT4QVXw8utuh5ro6fz",
                        contentDescription = "Workshop",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Brush.horizontalGradient(listOf(Color(0xB3000000), Color.Transparent)))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(24.dp)
                            .widthIn(max = 200.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Master Craftsmen of Karnataka",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                            lineHeight = 28.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = onHeritageClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = SecondaryContainer,
                                contentColor = OnSecondaryContainer
                            ),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("Explore", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }

            // Featured Works
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Featured Works",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Primary
                    )
                    TextButton(onClick = onGalleryClick) {
                        Text(
                            "View Gallery",
                            style = MaterialTheme.typography.labelLarge,
                            color = Secondary
                        )
                    }
                }
            }

            item {
                if (featured.isEmpty()) {
                    Text(
                        "No featured works found",
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(featured) { sculpture ->
                            val artistName = artists.find { a -> a.sculptures.any { it.id == sculpture.id } }?.name ?: ""
                            FeaturedSculptureCard(
                                sculpture = sculpture,
                                artistName = artistName,
                                onClick = { onSculptureClick(sculpture.id) }
                            )
                        }
                    }
                }
            }

            // Master Craftsmen
            item {
                Text(
                    "Master Craftsmen",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Primary,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 12.dp)
                )
            }

            if (artists.isEmpty()) {
                item {
                    Text(
                        "No artists found matching your search",
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                items(artists) { artist ->
                    ArtistCard(
                        artist = artist,
                        onClick = { onArtistClick(artist.id) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
                    )
                }
            }

            // Heritage Teaser Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Brush.horizontalGradient(listOf(Primary, PrimaryContainer)))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Tracing Hoysala Roots",
                                style = MaterialTheme.typography.titleLarge,
                                color = OnPrimaryContainer
                            )
                            Text(
                                "The geometry of 12th century stone",
                                style = MaterialTheme.typography.bodySmall,
                                color = OnPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                        Button(
                            onClick = onHeritageClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Primary
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Discover", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    }
}
