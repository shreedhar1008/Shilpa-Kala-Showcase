package com.shilpakala.showcase.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.shilpakala.showcase.data.models.Artist
import com.shilpakala.showcase.ui.components.SculptureGridCard
import com.shilpakala.showcase.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistPortfolioScreen(
    artist: Artist,
    onBack: () -> Unit,
    onSculptureClick: (String) -> Unit,
    onTimelineClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onShareClick: () -> Unit,
    onAddArtworkClick: () -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    var bioExpanded by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        modifier = Modifier.fillMaxSize().background(Background),
        contentPadding = PaddingValues(bottom = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero header spanning full width
        item(span = { GridItemSpan(maxLineSpan) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(PrimaryContainer)
            ) {
                // Background image with opacity
                AsyncImage(
                    model = artist.profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.1f
                )
                // Back button
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = OnPrimary)
                }
                // Profile content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile image
                    AsyncImage(
                        model = artist.profileImageUrl,
                        contentDescription = artist.name,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(SurfaceVariant),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    // Name + specialty
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            artist.name,
                            style = MaterialTheme.typography.headlineMedium,
                            color = OnPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = SecondaryContainer
                        ) {
                            Text(
                                artist.specialty,
                                style = MaterialTheme.typography.labelMedium,
                                color = OnSecondaryContainer,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        artist.bio,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OnPrimary.copy(alpha = 0.9f),
                        maxLines = if (bioExpanded) Int.MAX_VALUE else 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .animateContentSize()
                            .clickable { bioExpanded = !bioExpanded }
                    )
                    if (artist.bio.length > 100) {
                        Text(
                            text = if (bioExpanded) "Show Less" else "Read More",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnPrimary,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .clickable { bioExpanded = !bioExpanded }
                        )
                    }
                }
            }
        }

        // Action bar
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .offset(y = (-12).dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onTimelineClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SurfaceBright,
                        contentColor = Primary
                    ),
                    border = ButtonDefaults.outlinedButtonBorder,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Filled.Timeline, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Timeline", style = MaterialTheme.typography.labelLarge)
                }

                OutlinedButton(
                    onClick = onShareClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = SurfaceBright,
                        contentColor = Primary
                    ),
                    modifier = Modifier.size(48.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(Icons.Filled.Share, "Share", modifier = Modifier.size(20.dp))
                }
            }
        }

        // Gallery header
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Portfolio Gallery",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Primary
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SurfaceVariant
                ) {
                    Text(
                        "Sculptures",
                        style = MaterialTheme.typography.labelMedium,
                        color = OnSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Sculpture grid
        items(artist.sculptures) { sculpture ->
            SculptureGridCard(
                sculpture = sculpture,
                onClick = { onSculptureClick(sculpture.id) },
                modifier = Modifier.padding(
                    start = if (artist.sculptures.indexOf(sculpture) % 2 == 0) 20.dp else 0.dp,
                    end = if (artist.sculptures.indexOf(sculpture) % 2 == 1) 20.dp else 0.dp
                )
            )
        }
    }
}
