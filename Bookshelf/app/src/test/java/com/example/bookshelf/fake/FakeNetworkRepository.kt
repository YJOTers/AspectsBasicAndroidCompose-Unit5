package com.example.bookshelf.fake

import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.model.BookModel

class FakeNetworkRepository: BookRepository {
    override suspend fun getBooks(): BookModel {
        return FakeDataSource.books
    }
}