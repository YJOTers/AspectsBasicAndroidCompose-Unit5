package com.example.bookshelf.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.bookshelf.model.BookModel
import com.example.bookshelf.model.Items

@Composable
fun BooksView(
    viewUiState: BooksUiState,
    clickBook: (Items)-> Unit,
    contentPadding: PaddingValues,
    retryAction: ()-> Unit,
    modifier: Modifier = Modifier
) {
    when(viewUiState){
        is BooksUiState.Loading -> {
            LoadingView(
                modifier = modifier
            )
        }
        is BooksUiState.Error -> {
            ErrorView(
                retryAction = retryAction,
                modifier = modifier
            )
        }
        is BooksUiState.Success -> {
            SuccessView(
                books = viewUiState.books,
                clickBook = clickBook,
                contentPadding = contentPadding,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun SuccessView(
    books: BookModel,
    clickBook: (Items)-> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.medium_1)),
        contentPadding = contentPadding,
        modifier = modifier
    ){
        items(items = books.items){ book ->
            ItemView(
                data = book,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.short_1))
                    .clickable { clickBook(book) }
            )
        }
    }
}

@Composable
private fun ItemView(
    data: Items,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.medium_1))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data.volumeInfo.imageLinks.thumbnail
                    .replace("http","https"))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.error_200),
            placeholder = painterResource(id = R.drawable.loading_200),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun ErrorView(
    retryAction: ()-> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.error_200),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.medium_2))
        )
        Text(
            text = stringResource(id = R.string.error_text),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.short_1))
        )
        Button(onClick = retryAction) {
            Text(stringResource(id = R.string.error_button))
        }
    }
}

@Composable
private fun LoadingView(modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = R.drawable.loading_200),
        contentDescription = null,
        modifier = modifier.size(dimensionResource(id = R.dimen.medium_2))
    )
}