package com.instafix.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlin.random.Random

data class Technician(
    val id: String,
    val name: String,
    val photoUrl: String,
    val rating: Float,
    val distance: Float
)

@Composable
fun TechniciansScreen(navController: NavController) {
    // Mock data for technicians
    val technicians = remember {
        List(10) { index ->
            Technician(
                id = "tech_$index",
                name = "Technician ${index + 1}",
                photoUrl = "https://i.pravatar.cc/150?img=$index",
                rating = Random.nextFloat() * 2 + 3, // Random rating between 3 and 5
                distance = Random.nextFloat() * 5 + 1 // Random distance between 1 and 6 km
            )
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Available Technicians",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(technicians) { technician ->
                TechnicianCard(technician)
            }
        }
    }
}

@Composable
fun TechnicianCard(technician: Technician) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile photo
            AsyncImage(
                model = technician.photoUrl,
                contentDescription = "Technician photo",
                modifier = Modifier
                    .size(60.dp),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Technician info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = technician.name,
                    style = MaterialTheme.typography.titleMedium
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "★ ${String.format("%.1f", technician.rating)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = "${String.format("%.1f", technician.distance)} km away",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(8.dp))
            
            // Action buttons
            Column {
                Button(
                    onClick = { /* Accept technician */ },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("Accept")
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                OutlinedButton(
                    onClick = { /* Decline technician */ },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("Decline")
                }
            }
        }
    }
} 