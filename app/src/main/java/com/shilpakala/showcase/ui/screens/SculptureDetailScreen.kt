package com.shilpakala.showcase.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shilpakala.showcase.data.DataRepository
import com.shilpakala.showcase.data.models.Sculpture
import com.shilpakala.showcase.ui.theme.*

@Composable
fun SculptureDetailScreen(
    sculpture: Sculpture,
    onBack: () -> Unit,
    onBuyClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val artist = DataRepository.getArtistBySculptureId(sculpture.id)
    var expanded by remember { mutableStateOf(false) }
    var scale by remember { mutableFloatStateOf(1f) }
    val transformState = rememberTransformableState { zoomChange, _, _ ->
        scale = (scale * zoomChange).coerceIn(1f, 3f)
    }

    Box(modifier = Modifier.fillMaxSize().background(Surface)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // Hero Image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                ) {
                    AsyncImage(
                        model = sculpture.imageUrl,
                        contentDescription = sculpture.title,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(scaleX = scale, scaleY = scale)
                            .transformable(transformState),
                        contentScale = ContentScale.Crop
                    )
                    // Floating top controls
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .statusBarsPadding(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = onBack,
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Surface.copy(0.9f))
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Primary)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = {
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, "${sculpture.title} (${sculpture.productId}) — Shilpa-Kala Showcase")
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Share"))
                                },
                                colors = IconButtonDefaults.iconButtonColors(containerColor = Surface.copy(0.9f))
                            ) {
                                Icon(Icons.Filled.Share, "Share", tint = Primary)
                            }
                            IconButton(
                                onClick = {},
                                colors = IconButtonDefaults.iconButtonColors(containerColor = Surface.copy(0.9f))
                            ) {
                                Icon(Icons.Filled.Favorite, "Favorite", tint = Primary)
                            }
                        }
                    }
                    // Bottom badges
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 20.dp, bottom = 56.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(shape = RoundedCornerShape(8.dp), color = Primary.copy(0.8f)) {
                            Text(
                                "ID: ${sculpture.productId}",
                                style = MaterialTheme.typography.labelMedium,
                                color = OnPrimary,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(50), color = Surface.copy(0.4f)) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Filled.ZoomIn, null, tint = Color.White, modifier = Modifier.size(16.dp))
                                Text("Pinch to inspect", style = MaterialTheme.typography.labelSmall, color = Color.White)
                            }
                        }
                    }
                }
            }

            // Content card sliding over hero
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = SurfaceContainerLowest,
                    shadowElevation = 12.dp
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        val isPriced = !sculpture.priceRange.startsWith("?")
                        Text(if (isPriced) "Selling Price" else "Historical Period", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                        Spacer(Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                sculpture.priceRange,
                                style = MaterialTheme.typography.displayLarge,
                                color = Primary
                            )
                        }

                        Spacer(Modifier.height(8.dp))

                        // Artist row
                        if (artist != null) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                AsyncImage(
                                    model = artist.profileImageUrl,
                                    contentDescription = null,
                                    modifier = Modifier.size(36.dp).clip(CircleShape).background(SurfaceVariant),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    "Crafted by ",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = OnSurfaceVariant
                                )
                                Text(
                                    artist.name,
                                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Primary
                                )
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        // Tag chips
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                "🏛️" to sculpture.style + " Style",
                                "🪨" to sculpture.material
                            ).forEach { (icon, label) ->
                                Surface(shape = RoundedCornerShape(8.dp), color = SurfaceVariant) {
                                    Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                                        Text(icon, fontSize = 14.sp)
                                        Spacer(Modifier.width(6.dp))
                                        Text(label, style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(20.dp))

                        // Pricing card (repeated but keep for now as requested no design change)
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = SurfaceContainerLow,
                            border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(0.3f))
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text("Historical Period", style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
                                Spacer(Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        sculpture.priceRange,
                                        style = MaterialTheme.typography.displayLarge,
                                        color = Primary
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))

                        // Description
                        Text("The Narrative", style = MaterialTheme.typography.headlineMedium, color = OnSurface)
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = sculpture.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = OnSurfaceVariant,
                            maxLines = if (expanded) Int.MAX_VALUE else 4,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.animateContentSize()
                        )
                        TextButton(onClick = { expanded = !expanded }) {
                            Text(
                                if (expanded) "Show Less" else "Read Full Story",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = Primary
                            )
                            Icon(
                                if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                null,
                                tint = Primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        // Specifications table
                        Text("Technical Specifications", style = MaterialTheme.typography.headlineMedium, color = OnSurface)
                        Spacer(Modifier.height(12.dp))
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = SurfaceContainerLowest,
                            border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(0.2f))
                        ) {
                            Column {
                                listOf(
                                    "Style" to sculpture.style,
                                    "Material" to sculpture.material,
                                    "Artist" to (artist?.name ?: "Unknown"),
                                    "Origin" to (artist?.location ?: "Karnataka"),
                                    "Product ID" to sculpture.productId
                                ).forEachIndexed { index, (label, value) ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            label,
                                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                            color = OnSurfaceVariant,
                                            modifier = Modifier
                                                .weight(0.35f)
                                                .background(SurfaceContainerHigh.copy(0.3f))
                                                .padding(16.dp)
                                        )
                                        Text(
                                            value,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = OnSurface,
                                            modifier = Modifier.weight(0.65f).padding(16.dp)
                                        )
                                    }
                                    if (index < 4) HorizontalDivider(color = OutlineVariant.copy(0.1f))
                                }
                            }
                        }

                        Spacer(Modifier.height(100.dp))
                    }
                }
            }
        }

        // Sticky WhatsApp footer
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = Surface.copy(0.8f),
            tonalElevation = 0.dp
        ) {
            Button(
                onClick = {
                    if (artist != null) {
                        val msg = "Hi, I'm interested in \"${sculpture.title}\" (${sculpture.productId}) from Shilpa-Kala Showcase."
                        val url = "https://wa.me/${artist.whatsappNumber}?text=${Uri.encode(msg)}"
                        try {
                            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        } catch (e: Exception) {
                            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(
                    "ENQUIRE ON WHATSAPP",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )
            }
        }
    }
}
