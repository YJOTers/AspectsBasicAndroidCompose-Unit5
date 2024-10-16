package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val booksRepository: BookRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUri = "https://www.googleapis.com/books/v1/"
    private val json = Json {ignoreUnknownKeys = true}
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUri)
        .build()
    private val retrofitService by lazy{
        retrofit.create(BooksApiService::class.java)
    }
    override val booksRepository by lazy{
        NetworkBooksRepository(retrofitService)
    }
}