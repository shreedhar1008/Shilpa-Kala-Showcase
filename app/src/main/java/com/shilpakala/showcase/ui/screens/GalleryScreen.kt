package com.shilpakala.showcase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shilpakala.showcase.ui.components.SculptureGridCard
import com.shilpakala.showcase.ui.theme.*
import com.shilpakala.showcase.viewmodel.ShilpaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    viewModel: ShilpaViewModel,
    onSculptureClick: (String) -> Unit,
    onBack: () -> Unit
) {
    val artists by viewModel.artists.collectAsState()
    val allSculptures = artists.flatMap { it.sculptures }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Masterpiece Gallery", style = MaterialTheme.typography.displayLarge, color = OnPrimaryContainer)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = OnPrimaryContainer)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryContainer)
            )
        },
        containerColor = Surface
    ) { paddingValues ->
        if (allSculptures.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No sculptures found in the gallery.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(allSculptures) { sculpture ->
                    SculptureGridCard(
                        sculpture = sculpture,
                        onClick = { onSculptureClick(sculpture.id) }
                    )
                }
            }
        }
    }
}
