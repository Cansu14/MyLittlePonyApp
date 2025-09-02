package com.example.mylittlepony.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mylittlepony.R
import com.example.mylittlepony.data.PonyData
import com.example.mylittlepony.viewmodel.PonyViewmodel


@Composable
fun PonyListScreen(viewmodel: PonyViewmodel) {
    val pony by viewmodel.ponyData.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .padding(top = 36.dp, bottom = 36.dp)
            .fillMaxWidth()
            .background(Color(0xFFfae1f9)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(pony) { pony ->
            PonyCard(pony)
        }
    }
}

@Composable
fun PonyCard(pony: PonyData) {
    val paddingMid = dimensionResource(id = R.dimen.padding_mid)
    val cardCorner = dimensionResource(id = R.dimen.card_corner)
    val textSize = dimensionResource(id = R.dimen.text_size)
    val paddingColumn = dimensionResource(id = R.dimen.padding_column)
    Card(
        shape = RoundedCornerShape(cardCorner),
        modifier = Modifier
            .padding(paddingMid)
            .wrapContentWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFeab9fa))
                .padding(paddingColumn)
        ) {
            Text(
                pony.name,
                color = Color(0xFF6e2885),
                fontSize = textSize.value.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
            )

            AsyncImage(
                model = pony.image[0],
                contentDescription = pony.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(cardCorner))
            )
        }
    }
}
