package com.shilpakala.showcase.presentation.screens.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shilpakala.showcase.presentation.theme.CreamWhite
import com.shilpakala.showcase.presentation.theme.MutedSage
import com.shilpakala.showcase.presentation.theme.SoftTaupe
import com.shilpakala.showcase.presentation.theme.WarmGold

data class StepData(
    val title: String,
    val duration: String,
    val description: String,
    val status: StepStatus,
    val imageUrl: String = ""
)

enum class StepStatus { COMPLETED, IN_PROGRESS, NOT_STARTED }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(navController: NavController) {
    // Hardcoded steps data as requested
    val steps = listOf(
        StepData(
            "Stone Selection", "3 days",
            "Sourcing high-quality Krishna Shila (black stone) from the quarries of Karkala.",
            StepStatus.COMPLETED,
            "https://images.unsplash.com/photo-1576016770956-debb63d92058?w=800"
        ),
        StepData(
            "Block Preparation", "5 days",
            "Cleaning and squaring the raw stone block to prepare it for marking.",
            StepStatus.COMPLETED,
            "https://images.unsplash.com/photo-1599727717435-09852899452b?w=800"
        ),
        StepData(
            "Sketching & Marking", "2 days",
            "Applying traditional iconometric measurements (Talamana) using red oxide.",
            StepStatus.COMPLETED,
            "https://images.unsplash.com/photo-1582555172866-f73bb12a2ab3?w=800"
        ),
        StepData(
            "Rough Carving", "14 days",
            "Using heavy chisels to remove bulk material and define the basic silhouette.",
            StepStatus.COMPLETED,
            "https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800"
        ),
        StepData(
            "Detailed Carving", "21 days",
            "Defining jewelry, facial features, and intricate drapery using fine chisels.",
            StepStatus.IN_PROGRESS,
            "https://images.unsplash.com/photo-1564399580075-5dfe19c205f3?w=800"
        ),
        StepData(
            "Surface Finishing", "7 days",
            "Polishing with varying grades of abrasive stones to achieve a smooth finish.",
            StepStatus.NOT_STARTED
        ),
        StepData(
            "Consecration & Quality Check", "1 day",
            "Final inspection and ritual cleaning before the artwork is ready for display.",
            StepStatus.NOT_STARTED
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Journey of a Sculpture", color = WarmGold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = CreamWhite)
            )
        },
        containerColor = CreamWhite
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    "Watch how a block of stone becomes a masterpiece",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            itemsIndexed(steps) { index, step ->
                TimelineStepItem(step, index == steps.size - 1, index + 1)
            }
        }
    }
}

@Composable
fun TimelineStepItem(step: StepData, isLast: Boolean, stepNumber: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (step.status == StepStatus.COMPLETED) WarmGold else SoftTaupe),
                contentAlignment = Alignment.Center
            ) {
                if (step.status == StepStatus.COMPLETED) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
                } else {
                    Text(
                        stepNumber.toString(),
                        color = if (step.status == StepStatus.IN_PROGRESS) WarmGold else MutedSage,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(if (step.imageUrl.isNotEmpty()) 240.dp else 100.dp)
                        .background(if (step.status == StepStatus.COMPLETED) WarmGold else SoftTaupe)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 32.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    step.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (step.status == StepStatus.IN_PROGRESS) WarmGold else Color.Unspecified
                )
                Text(
                    step.duration,
                    style = MaterialTheme.typography.labelSmall,
                    color = MutedSage
                )
            }
            if (step.imageUrl.isNotEmpty()) {
                AsyncImage(
                    model = step.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (step.status == StepStatus.NOT_STARTED) Color.Gray else Color.Unspecified,
                lineHeight = 20.sp
            )
        }
    }
}
