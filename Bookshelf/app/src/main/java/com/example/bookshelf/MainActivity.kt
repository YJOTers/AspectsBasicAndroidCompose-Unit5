package com.example.bookshelf

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.theme.BookshelfTheme
import com.example.bookshelf.ui.views.BookViewModel
import com.example.bookshelf.ui.views.MainView

class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm: BookViewModel = viewModel(factory = BookViewModel.Factory)
                    MainView(
                        viewUiState = vm.vmUiState,
                        retryAction = vm::getBooks,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}