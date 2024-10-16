package com.example.bookshelf

import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.fake.FakeNetworkRepository
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.views.BookViewModel
import com.example.bookshelf.ui.views.BooksUiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun booksViewModel_getBooks_verifyUiStateSuccess() = runTest{
        val vm = BookViewModel(
            bookRepository = FakeNetworkRepository()
        )
        assertEquals(
            BooksUiState.Success(FakeDataSource.books),
            vm.vmUiState
        )
    }
}