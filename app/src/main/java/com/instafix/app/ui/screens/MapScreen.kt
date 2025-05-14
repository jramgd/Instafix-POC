package com.instafix.app.ui.screens

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val context = LocalContext.current
    var selectedLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var selectedService by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Placeholder for map view
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Map View Placeholder")
            }
        }
        
        FloatingActionButton(
            onClick = {
                scope.launch {
                    if (locationPermissionState.status == com.google.accompanist.permissions.PermissionStatus.Granted) {
                        // Get current location
                        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                            location?.let {
                                selectedLocation = Pair(it.latitude, it.longitude)
                            }
                        }
                    } else {
                        locationPermissionState.launchPermissionRequest()
                    }
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = "Current Location")
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Select Service Type",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ServiceTypeButton(
                        text = "Fully Down",
                        selected = selectedService == "Fully Down",
                        onClick = { selectedService = "Fully Down" }
                    )
                    
                    ServiceTypeButton(
                        text = "Not Working Properly",
                        selected = selectedService == "Not Working Properly",
                        onClick = { selectedService = "Not Working Properly" }
                    )
                    
                    ServiceTypeButton(
                        text = "Normal Servicing",
                        selected = selectedService == "Normal Servicing",
                        onClick = { selectedService = "Normal Servicing" }
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(
                        onClick = {
                            if (selectedLocation != null && selectedService != null) {
                                navController.navigate("home")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = selectedLocation != null && selectedService != null
                    ) {
                        Text("Submit Request")
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceTypeButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) 
                MaterialTheme.colorScheme.primary 
            else 
                MaterialTheme.colorScheme.surface
        )
    ) {
        Text(text)
    }
} 