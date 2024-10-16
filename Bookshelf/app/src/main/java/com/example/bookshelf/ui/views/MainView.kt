package com.example.bookshelf.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookshelf.R
import com.example.bookshelf.model.Items

enum class MyViews { First, Second }

@Composable
fun MainView(
    viewUiState: BooksUiState,
    retryAction: ()-> Unit,
    modifier: Modifier = Modifier
){
    var viewSelected by remember { mutableStateOf(MyViews.First) }
    var bookSelected: Items? by remember { mutableStateOf(null) }
    Scaffold(
        topBar = { MyTopBar(
            viewSelected = viewSelected,
            back = {viewSelected = MyViews.First}
        )},
        modifier = modifier
    ) { paddingValues ->
        when(viewSelected){
            MyViews.First -> {
                BooksView(
                    viewUiState = viewUiState,
                    clickBook = { book ->
                        bookSelected = book
                        viewSelected = MyViews.Second
                    },
                    contentPadding = paddingValues,
                    retryAction = retryAction,
                    modifier = modifier
                )
            }
            MyViews.Second -> {
                InfoBookView(
                    data = bookSelected!!,
                    back = {viewSelected = MyViews.First},
                    modifier = modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopBar(
    viewSelected: MyViews,
    back: ()-> Unit
){
    TopAppBar(
        title = {Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )},
        navigationIcon = {
            if(viewSelected == MyViews.Second){
                IconButton(onClick = back) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}