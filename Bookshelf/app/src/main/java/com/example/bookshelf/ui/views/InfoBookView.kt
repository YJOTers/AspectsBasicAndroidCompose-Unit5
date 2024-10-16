package com.example.bookshelf.ui.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Items

@Composable
fun InfoBookView(
    data: Items,
    back: ()-> Unit,
    modifier: Modifier = Modifier
){
    BackHandler { back() }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data.volumeInfo.imageLinks.thumbnail
                    .replace("http","https"))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.error_200),
            placeholder = painterResource(id = R.drawable.loading_200),
            alpha = 0.2f,
            modifier = modifier
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.short_2))
        ){
            Text(
                text = data.volumeInfo.title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short_1)))
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = data.volumeInfo.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.medium_2))
                    .verticalScroll(rememberScrollState()),
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short_1)))
            Text(
                text = stringResource(id = R.string.author),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = data.volumeInfo.authors.joinToString(","),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short_1)))
            Text(
                text = stringResource(id = R.string.publishedDate),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = data.volumeInfo.publishedDate,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}