package com.example.mylittlepony.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.mylittlepony.R
import com.example.mylittlepony.data.PonyData
import com.example.mylittlepony.viewmodel.PonyListViewModel


@Composable
fun PonyListScreen(
    viewmodel: PonyListViewModel,
    onPonyClick: (Int) -> Unit,
) {
    val state by viewmodel.uiState.collectAsState()
    val lazyPagingItems: LazyPagingItems<PonyData>? = state.data?.collectAsLazyPagingItems()

    when {

        lazyPagingItems?.loadState?.refresh is LoadState.Error -> {
            StateText("${state.error}")
        }

        lazyPagingItems == null -> {
            StateText("Loading...")
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 36.dp, bottom = 36.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFfae1f9)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(lazyPagingItems.itemCount) { index ->
                    val pony = lazyPagingItems[index]
                    pony?.let { PonyCard(it, onPonyClick) }
                }
            }
        }
    }
}

@Composable
fun StateText(text: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text)
    }
}

@Composable
fun PonyCard(pony: PonyData, onClick: (Int) -> Unit) {
    val paddingMid = dimensionResource(id = R.dimen.padding_mid)
    val cardCorner = dimensionResource(id = R.dimen.card_corner)
    val textSize = dimensionResource(id = R.dimen.text_size)
    val paddingColumn = dimensionResource(id = R.dimen.padding_column)
    Card(
        shape = RoundedCornerShape(cardCorner),
        modifier = Modifier
            .padding(paddingMid)
            .wrapContentWidth()
            .clickable { onClick(pony.id) }
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
