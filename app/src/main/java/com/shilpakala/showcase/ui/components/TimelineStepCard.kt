package com.shilpakala.showcase.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shilpakala.showcase.data.models.TimelineStep
import com.shilpakala.showcase.ui.theme.*

@Composable
fun TimelineStepCard(
    step: TimelineStep,
    isCompleted: Boolean,
    isInProgress: Boolean,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isInProgress) 6.dp else 2.dp
        ),
        border = if (isInProgress) {
            androidx.compose.foundation.BorderStroke(2.dp, Secondary)
        } else {
            androidx.compose.foundation.BorderStroke(1.dp, SurfaceVariant)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Step image
            AsyncImage(
                model = step.imageUrl,
                contentDescription = step.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alpha = if (!isCompleted && !isInProgress) 0.4f else 1f
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Title + status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = step.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = if (isCompleted || isInProgress) Primary else OnSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                val (badgeText, badgeColor, badgeTextColor) = when {
                    isInProgress -> Triple("In Progress", SecondaryContainer, OnSecondaryContainer)
                    isCompleted -> Triple("Completed", TertiaryContainer.copy(alpha = 0.1f), Tertiary)
                    else -> Triple("Upcoming", SurfaceVariant, OnSurfaceVariant)
                }
                Surface(
                    shape = RoundedCornerShape(50),
                    color = badgeColor
                ) {
                    Text(
                        text = badgeText.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        ),
                        color = badgeTextColor,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = step.description,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isCompleted || isInProgress) OnSurfaceVariant else OnSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}
