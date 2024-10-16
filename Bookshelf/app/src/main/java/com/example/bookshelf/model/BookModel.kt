package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookModel(
    val kind: String,
    val totalItems: Int,
    val items: List<Items>
)
@Serializable
data class Items(
    val id: String,
    val volumeInfo: VolumenInfo
)
@Serializable
data class VolumenInfo(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val description: String,
    val imageLinks: ImageLinks
)
@Serializable
data class ImageLinks(
    val thumbnail: String
)