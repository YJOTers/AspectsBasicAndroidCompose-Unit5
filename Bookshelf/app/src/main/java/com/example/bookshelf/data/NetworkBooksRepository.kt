package com.example.bookshelf.data

import com.example.bookshelf.model.BookModel
import com.example.bookshelf.network.BooksApiService

interface BookRepository{
    suspend fun getBooks(): BookModel
}

class NetworkBooksRepository(
    private val bookApiService: BooksApiService
): BookRepository {
    override suspend fun getBooks() = bookApiService.getBooks()
}