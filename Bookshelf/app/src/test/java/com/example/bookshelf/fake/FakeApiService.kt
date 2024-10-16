package com.example.bookshelf.fake

import com.example.bookshelf.model.BookModel
import com.example.bookshelf.network.BooksApiService

class FakeApiService: BooksApiService{
    override suspend fun getBooks(): BookModel {
        return FakeDataSource.books
    }
}