package com.shilpakala.showcase.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shilpakala.showcase.data.models.HeritageStyle
import com.shilpakala.showcase.ui.theme.*
import com.shilpakala.showcase.viewmodel.ShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeritageStoryScreen(
    viewModel: ShilpaViewModel,
    onBack: () -> Unit
) {
    val styles by viewModel.heritageStyles.collectAsState()


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
            // Stats Banner
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = SurfaceContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(0.3f))
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            "CHRONICLES OF STONE",
                            style = MaterialTheme.typography.labelLarge,
                            color = Primary,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Preserving Ancient Legacies",
                            style = MaterialTheme.typography.displayLarge,
                            color = OnSurface,
                            fontSize = 36.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Our archive documents the intricate evolution of Indian sculpture, mapping centuries of artistic mastery.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = OnSurfaceVariant.copy(0.8f)
                        )
                        Spacer(Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("12", style = MaterialTheme.typography.displayLarge, color = Primary, fontSize = 36.sp)
                                Text("Heritage Styles", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                            }
                            Box(
                                Modifier
                                    .width(1.dp)
                                    .height(48.dp)
                                    .background(OutlineVariant)
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("500+", style = MaterialTheme.typography.displayLarge, color = Primary, fontSize = 36.sp)
                                Text("Master Craftsmen", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                            }
                        }
                    }
                }
            }

            // Heritage style cards
            items(styles) { style ->
                HeritageStyleArticle(style = style)
                if (styles.indexOf(style) < styles.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                        color = OutlineVariant.copy(0.3f)
                    )
                }
            }

            // WhatsApp CTA
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = SurfaceContainerHigh,
                    border = androidx.compose.foundation.BorderStroke(1.dp, WhatsAppGreen.copy(0.2f))
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(WhatsAppGreen.copy(0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Send, null, tint = WhatsAppGreen, modifier = Modifier.size(28.dp))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Connect with a Master Artisan",
                                style = MaterialTheme.typography.headlineMedium,
                                color = OnSurface,
                                fontSize = 18.sp
                            )
                            Text(
                                "Commission a custom heritage piece directly.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = OnSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeritageStyleArticle(style: HeritageStyle) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
        // Large image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 10f)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                model = style.imageUrl,
                contentDescription = style.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp),
                shape = RoundedCornerShape(50),
                color = SecondaryContainer
            ) {
                Text(
                    style.period,
                    style = MaterialTheme.typography.labelMedium,
                    color = OnSecondaryContainer,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Title
        Text(
            style.name + " Sculpture",
            style = MaterialTheme.typography.displayLarge,
            color = OnSurface,
            fontSize = 28.sp
        )

        Spacer(Modifier.height(8.dp))

        // Location
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(Icons.Filled.LocationOn, null, tint = Primary, modifier = Modifier.size(16.dp))
            Text(style.origin, style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
        }

        Spacer(Modifier.height(12.dp))

        // Description
        Text(
            style.description,
            style = MaterialTheme.typography.bodyLarge,
            color = OnSurfaceVariant,
            lineHeight = 24.sp
        )

        Spacer(Modifier.height(16.dp))

        // Key Characteristics
        Text(
            "KEY CHARACTERISTICS",
            style = MaterialTheme.typography.labelMedium,
            color = Primary,
            letterSpacing = 2.sp
        )
        Spacer(Modifier.height(8.dp))
        style.characteristics.take(3).forEach { char ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(SecondaryContainer)
                )
                Text(char, style = MaterialTheme.typography.bodyMedium, color = OnSurfaceVariant)
            }
        }

        Spacer(Modifier.height(16.dp))

        // Explore button
        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryContainer,
                contentColor = OnPrimaryContainer
            )
        ) {
            Text("Explore Collection", style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.width(6.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, null, modifier = Modifier.size(16.dp))
        }
    }
}
