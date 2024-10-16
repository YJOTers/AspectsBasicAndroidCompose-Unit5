package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class NetworkRepositoryTest {
    @Test
    fun networkRepository_getBooks_verifyPhotoList(){
        runTest {
            val repository = NetworkBooksRepository(
                bookApiService = FakeApiService()
            )
            assertEquals(FakeDataSource.books, repository.getBooks())
        }
    }
}