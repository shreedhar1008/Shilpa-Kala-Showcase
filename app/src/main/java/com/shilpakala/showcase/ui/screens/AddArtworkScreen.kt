package com.shilpakala.showcase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shilpakala.showcase.data.models.Sculpture
import com.shilpakala.showcase.ui.theme.Primary
import com.shilpakala.showcase.viewmodel.ShilpaViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddArtworkScreen(
    artistId: String,
    viewModel: ShilpaViewModel,
    onBack: () -> Unit,
    onSuccess: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var style by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    val isFormValid = title.isNotBlank() && description.isNotBlank() && material.isNotBlank() && price.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("List New Artwork", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Showcase to the World",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            
            Text(
                "Provide details about your newly crafted piece. Once listed, buyers can purchase it directly.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Artwork Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = material,
                onValueChange = { material = it },
                label = { Text("Material (e.g., Teak Wood, Granite)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = style,
                onValueChange = { style = it },
                label = { Text("Style (e.g., Contemporary, Hoysala)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Selling Price (₹)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Detailed Description & Story") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val formattedPrice = if (price.startsWith("₹")) price else "₹"
                    val newSculpture = Sculpture(
                        id = "sculpt_" + UUID.randomUUID().toString().substring(0, 8),
                        productId = "SKS-NEW",
                        title = title.trim(),
                        description = description.trim(),
                        style = style.trim().ifEmpty { "Contemporary" },
                        material = material.trim(),
                        priceRange = formattedPrice,
                        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAL_HU_wrfyM1fo1aQl8yO9Hbt0U36cgSPnxboiYU-hz8k67aKO0PpQgEiLV5Plk3b6dNYwpaAIgyrK09NN4sPy4_DAn0IBh6raKUs4q5PrlT2QEXe8KRFyB63JHOTCOUEUBm5YpFyB0vjFpHweUd0JWt0gkE2G9AAQKveWP3Ze7bqVTpt9kW_EKN1Px2cr2iBsUNg5t3Xh_25GMYa5NFL4D4FiStS_lThBH3WayWr67oTaYBo1iKWzjLlcpLarrwuttPOu58yy08ez",
                        isAvailable = true,
                        artistId = artistId
                    )
                    viewModel.addSculpture(newSculpture)
                    onSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = isFormValid,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text("Publish to Gallery", fontSize = 16.dp.value.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
