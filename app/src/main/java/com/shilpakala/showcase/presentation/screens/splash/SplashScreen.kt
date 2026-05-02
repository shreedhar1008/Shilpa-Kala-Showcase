package com.shilpakala.showcase.presentation.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shilpakala.showcase.presentation.theme.CreamWhite
import com.shilpakala.showcase.presentation.theme.WarmGold
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
        delay(2000)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CreamWhite),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(1000)) + scaleIn(animationSpec = tween(1000))
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Shilpa-Kala",
                        style = MaterialTheme.typography.displayLarge,
                        color = WarmGold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Celebrating India's Master Craftsmen",
                        style = MaterialTheme.typography.titleMedium,
                        color = WarmGold.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Text(
            text = "MindMatrix VTU Internship",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            style = MaterialTheme.typography.labelSmall,
            color = WarmGold.copy(alpha = 0.5f)
        )
    }
}
