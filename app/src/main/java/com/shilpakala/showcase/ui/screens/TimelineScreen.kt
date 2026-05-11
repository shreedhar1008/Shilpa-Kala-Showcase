package com.shilpakala.showcase.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shilpakala.showcase.data.models.Artist
import com.shilpakala.showcase.ui.components.TimelineStepCard
import com.shilpakala.showcase.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkInProgressTimelineScreen(
    artist: Artist,
    onBack: () -> Unit
) {
    val currentStep = 2 // 0-indexed: step 3 is "In Progress"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("🏰", fontSize = 20.sp)
                        Text("Shilpa-Kala", style = MaterialTheme.typography.displayLarge, color = OnPrimaryContainer)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = OnPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryContainer)
            )
        },
        containerColor = Background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Hero Card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(400.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    AsyncImage(
                        model = artist.timelineSteps.firstOrNull()?.imageUrl ?: "",
                        contentDescription = "Carving process",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color(0xCC000000)),
                                    startY = 100f
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    ) {
                        Text(
                            "CHRONICLES OF CREATION",
                            style = MaterialTheme.typography.labelMedium,
                            color = SecondaryContainer,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "From Rock to Masterpiece",
                            style = MaterialTheme.typography.displayLarge,
                            color = Color.White
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Witness the metamorphosis of ancient stone into divine form.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            // Timeline steps with vertical line
            itemsIndexed(artist.timelineSteps) { index, step ->
                val isCompleted = index < currentStep
                val isInProgress = index == currentStep

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    // Left: vertical line + step number circle
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(40.dp)
                    ) {
                        // Top line segment
                        if (index > 0) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(16.dp)
                                    .background(SecondaryContainer.copy(alpha = 0.3f))
                            )
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // Step circle
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isCompleted || isInProgress) SecondaryContainer
                                    else SurfaceDim
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "${index + 1}",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = if (isCompleted || isInProgress) OnSecondaryContainer else OnSurfaceVariant
                            )
                        }

                        // Bottom line segment
                        if (index < artist.timelineSteps.size - 1) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .fillMaxHeight()
                                    .defaultMinSize(minHeight = 200.dp)
                                    .background(SecondaryContainer.copy(alpha = 0.3f))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Right: timeline card
                    TimelineStepCard(
                        step = step,
                        isCompleted = isCompleted,
                        isInProgress = isInProgress,
                        modifier = Modifier.weight(1f).padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}
