package com.shilpakala.showcase.presentation.screens.artwork_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.shilpakala.showcase.presentation.theme.DeepCharcoal
import com.shilpakala.showcase.presentation.theme.SoftTaupe
import com.shilpakala.showcase.presentation.theme.WarmGold
import com.shilpakala.showcase.presentation.theme.WhatsAppGreen
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkDetailScreen(
    navController: NavController,
    artworkId: String,
    viewModel: ArtworkDetailViewModel = hiltViewModel()
) {
    val artwork by viewModel.artwork.collectAsState()
    val artist by viewModel.artist.collectAsState()
    val relatedArtworks by viewModel.relatedArtworks.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(artworkId) {
        viewModel.loadArtwork(artworkId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.background(Color.White.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        artwork?.let { art ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                // Hero Image
                item {
                    val zoomState = rememberZoomState(maxScale = 5f)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                            .background(Color.Black)
                    ) {
                        AsyncImage(
                            model = art.imageUrl,
                            contentDescription = art.title,
                            modifier = Modifier
                                .fillMaxSize()
                                .zoomable(zoomState),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Text(
                        "Pinch to explore carving details",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                // Info Card
                item {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(SoftTaupe)
                            .padding(16.dp)
                    ) {
                        Text(
                            art.title,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Serif,
                            color = DeepCharcoal,
                            fontWeight = FontWeight.Bold
                        )
                        artist?.let {
                            Text(
                                "By ${it.name}",
                                color = WarmGold,
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .clickable {
                                        navController.navigate(Screen.ArtistProfile.createRoute(it.id))
                                    },
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        AssistChip(
                            onClick = { },
                            label = { Text(art.heritageStyle) },
                            colors = AssistChipDefaults.assistChipColors(labelColor = WarmGold)
                        )
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                        
                        InfoRow("Material", art.material)
                        InfoRow("Dimensions", art.dimensions)
                        InfoRow("Price Range", art.priceRange)
                        
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                        
                        var expanded by remember { mutableStateOf(false) }
                        Text(
                            "About this piece",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            art.description,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = if (expanded) Int.MAX_VALUE else 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.animateContentSize()
                        )
                        Text(
                            if (expanded) "Read less" else "Read more",
                            color = WarmGold,
                            modifier = Modifier.clickable { expanded = !expanded },
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                // Enquiry Button
                item {
                    Button(
                        onClick = {
                            val message = """
                                Namaste! I am interested in your artwork.
                                Product ID: ${art.id}
                                Title: ${art.title}
                                Material: ${art.material}
                                Dimensions: ${art.dimensions}
                                Price Range: ${art.priceRange}
                                Heritage Style: ${art.heritageStyle}
                                Please share availability and payment details. Thank you!
                            """.trimIndent()
                            val phone = artist?.whatsappNumber?.replace(Regex("[^0-9]"), "") ?: ""
                            val uri = Uri.parse("https://wa.me/${'$'}phone?text=${Uri.encode(message)}")
                            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                setPackage("com.whatsapp")
                            }
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
                        Text("Enquire via WhatsApp", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // More from Artist
                if (relatedArtworks.isNotEmpty()) {
                    item {
                        Text(
                            "More from ${artist?.name ?: "this artist"}",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(relatedArtworks) { related ->
                                AsyncImage(
                                    model = related.imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable {
                                            navController.navigate(Screen.ArtworkDetail.createRoute(related.id))
                                        },
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        Text(value, fontWeight = FontWeight.Medium, style = MaterialTheme.typography.bodyMedium)
    }
}
