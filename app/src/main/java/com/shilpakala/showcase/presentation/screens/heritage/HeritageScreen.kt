package com.shilpakala.showcase.presentation.screens.heritage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shilpakala.showcase.presentation.navigation.Screen
import com.shilpakala.showcase.presentation.theme.CreamWhite
import com.shilpakala.showcase.presentation.theme.DeepCharcoal
import com.shilpakala.showcase.presentation.theme.ParchmentYellow
import com.shilpakala.showcase.presentation.theme.WarmGold

data class HeritageStyleInfo(
    val name: String,
    val period: String,
    val description: String,
    val imageUrl: String,
    val features: List<String>
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HeritageScreen(navController: NavController) {
    // Hardcoded data as requested
    val styles = listOf(
        HeritageStyleInfo(
            "Hoysala", "11th–14th Century",
            "Known for extreme attention to detail and star-shaped temple plans. Soapstone was the primary medium, allowing for intricate jewelry-like carving.",
            "https://images.unsplash.com/photo-1621259580436-1e6a6a60e03e?w=800",
            listOf("Soapstone", "Stellar Plan", "Intricate Detail", "Madanikas")
        ),
        HeritageStyleInfo(
            "Chalukya", "6th–12th Century",
            "The cradle of temple architecture. Blending northern and southern styles (Vesara), characterized by rock-cut caves and structural sandstone temples.",
            "https://images.unsplash.com/photo-1600100397608-f09074aa908c?w=800",
            listOf("Sandstone", "Rock-cut", "Vesara Style", "Cave Temples")
        ),
        HeritageStyleInfo(
            "Vijayanagara", "14th–16th Century",
            "A synthesis of all previous styles. Famous for large monolithic sculptures, ornate pillars (Yali), and grand temple complexes in Hampi.",
            "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=800",
            listOf("Granite", "Monoliths", "Yali Pillars", "Musical Columns")
        ),
        HeritageStyleInfo(
            "Dravidian", "7th Century onwards",
            "Defined by pyramid-shaped towers (Vimana) and massive gateways (Gopuram). High focus on iconometry and divine proportions in idol making.",
            "https://images.unsplash.com/photo-1610413340051-9032658824f1?w=800",
            listOf("Granite", "Iconometry", "Vimana", "Gopuram")
        ),
        HeritageStyleInfo(
            "Vesara", "7th–11th Century",
            "A unique hybrid style that combines the curved Shikhara of the North with the layered Vimana of the South, seen primarily in Karnataka.",
            "https://images.unsplash.com/photo-1608958435111-9860b769185a?w=800",
            listOf("Hybrid", "Decorative", "Unique Shikhara")
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Heritage Styles", color = WarmGold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = CreamWhite)
            )
        },
        containerColor = CreamWhite
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ParchmentYellow)
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "India's Stone Legacy",
                        style = MaterialTheme.typography.displaySmall,
                        fontFamily = FontFamily.Serif,
                        color = DeepCharcoal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            items(styles) { style ->
                HeritageStyleCard(style) {
                    navController.navigate(Screen.Gallery.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeritageStyleCard(style: HeritageStyleInfo, onViewArtists: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            AsyncImage(
                model = style.imageUrl,
                contentDescription = style.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style.name,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = DeepCharcoal,
                        fontWeight = FontWeight.Bold
                    )
                    SuggestionChip(
                        onClick = { },
                        label = { Text(style.period) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = WarmGold.copy(alpha = 0.1f),
                            labelColor = WarmGold
                        ),
                        border = null
                    )
                }
                Text(
                    style.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    style.features.forEach { feature ->
                        AssistChip(
                            onClick = { },
                            label = { Text(feature, fontSize = 11.sp) },
                            modifier = Modifier.height(28.dp)
                        )
                    }
                }
                TextButton(
                    onClick = onViewArtists,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("View Artists →", color = WarmGold, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
