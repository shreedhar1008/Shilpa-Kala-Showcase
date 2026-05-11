package com.shilpakala.showcase.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shilpakala.showcase.data.models.Artist
import com.shilpakala.showcase.ui.theme.Primary
import com.shilpakala.showcase.viewmodel.ShilpaViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistRegistrationScreen(
    viewModel: ShilpaViewModel,
    onBack: () -> Unit,
    onRegistrationSuccess: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var specialty by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }

    val isFormValid = name.isNotBlank() && location.isNotBlank() && specialty.isNotBlank() && bio.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Join as Artisan", fontWeight = FontWeight.Bold) },
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
                "Create your Digital Portfolio",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Primary
            )
            
            Text(
                "Set up your profile to start showcasing and selling your handcrafted masterpieces to a global audience.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name / Studio Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location (e.g., Mysuru, Karnataka)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = specialty,
                onValueChange = { specialty = it },
                label = { Text("Specialty (e.g., Bronze Casting)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = whatsapp,
                onValueChange = { whatsapp = it },
                label = { Text("WhatsApp Number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio / Artist Story") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 6
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val newId = "artist_" + UUID.randomUUID().toString().substring(0, 8)
                    val newArtist = Artist(
                        id = newId,
                        name = name.trim(),
                        location = location.trim(),
                        specialty = specialty.trim(),
                        bio = bio.trim(),
                        whatsappNumber = whatsapp.trim(),
                        profileImageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCKUfbl9awap3wjuEqDn3hT6WykV_A-yeZoj2hTTj1_9jRS8v0EHtdo9jQDRCppjeMwHfrtUXwYdEuhjriJ8-YeoXRVqPtrJImRbspEmFii51TXAgfE8i4X0QeKyOr89IIJfsaxqS-ewimmgnj2VJ7pImO-4fyyfXoY8WzA8Qw1nLkRiMEuqS5DtFjb0ZTi41vW7-KlR8NRiTx3HPIqgqz2EDDQ8igcnVM9dEUFeNFPle671ckMVk6idh0L-zAcV1F3fPO_n-Exaf8g",
                        sculptures = emptyList(),
                        timelineSteps = emptyList() // Custom artists won't have a timeline for now
                    )
                    viewModel.registerArtist(newArtist)
                    onRegistrationSuccess(newId)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = isFormValid,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text("Create Portfolio", fontSize = 16.dp.value.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
