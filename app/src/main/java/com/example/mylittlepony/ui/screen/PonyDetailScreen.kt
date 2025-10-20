package com.example.mylittlepony.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mylittlepony.data.PonyData
import com.example.mylittlepony.ui.theme.Pink80
import com.example.mylittlepony.viewmodel.PonyDetailViewModel


@Composable
fun PonyDetailScreen(
    viewmodel: PonyDetailViewModel,
    ponyId: Int,
    onBackClick: () -> Unit,
) {
    val state by viewmodel.uiState.collectAsState()

    LaunchedEffect(ponyId) {
        viewmodel.fetchPonyDetail(ponyId)
    }

    val ponyDetail = state.data as? PonyData

    Column(
        modifier = Modifier
            .background(Pink80)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> Text("Loading...")
            state.error != null -> Text("Error: ${state.error}")
            ponyDetail != null -> {
                AsyncImage(
                    model = ponyDetail.image.firstOrNull(),
                    contentDescription = ponyDetail.name,
                    modifier = Modifier.fillMaxWidth()
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFfae1f9)),
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Name: ${ponyDetail.name ?: "---"}")
                        Text("Sex: ${ponyDetail.sex ?: "---"}")
                        Text("Occupation: ${ponyDetail.occupation ?: "---"}")
                    }
                }
            }
        }
    }
}