package com.example.bookshelf.ui.views

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.MainApplication
import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.model.BookModel
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BooksUiState{
    data class Success(val books: BookModel): BooksUiState
    data object Error: BooksUiState
    data object Loading: BooksUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class BookViewModel(
    private val bookRepository: BookRepository
): ViewModel(){
    var vmUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init{
        getBooks()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBooks(){
        viewModelScope.launch {
            vmUiState = BooksUiState.Loading
            vmUiState = try{
                BooksUiState.Success(bookRepository.getBooks())
            }catch(e: IOException){
                BooksUiState.Error
            }catch(e: HttpException){
                BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val booksRepository = application.container.booksRepository
                BookViewModel(bookRepository = booksRepository)
            }
        }
    }
}