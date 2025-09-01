package com.example.mylittlepony.ui.screen

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import coil.compose.AsyncImage
import com.example.mylittlepony.viewmodel.PonyViewmodel

@Composable
fun PonyListScreen(viewmodel: PonyViewmodel) {
    val pony by viewmodel.ponyData.collectAsState(initial = emptyList())

    LazyColumn {
        items(pony) { pony ->
            Column {
                Text(pony.name)
                Text(pony.alias ?: " ")

                AsyncImage(
                    model = pony.image[0],
                    contentDescription = pony.name
                )

            }
        }
    }
}