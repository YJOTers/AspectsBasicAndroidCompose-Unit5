package com.example.bookshelf.fake

import com.example.bookshelf.model.BookModel
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.Items
import com.example.bookshelf.model.VolumenInfo

object FakeDataSource {
    val books = BookModel(
        kind = "aa_bb",
        totalItems = 3,
        items = listOf(
            Items(
                id = "1",
                volumeInfo = VolumenInfo(
                    title = "El Rock",
                    authors = listOf("Carlos Rojas"),
                    publishedDate = "2017-02-12",
                    description = "Asssds sddferer",
                    imageLinks = ImageLinks(
                        thumbnail = "http:imagen1.com"
                    )
                )
            ),
            Items(
                id = "2",
                volumeInfo = VolumenInfo(
                    title = "El Jazz",
                    authors = listOf("Kelly Mott", "Alton Bot"),
                    publishedDate = "2012-07-19",
                    description = "Asssds sddferer",
                    imageLinks = ImageLinks(
                        thumbnail = "http:imagen2.com"
                    )
                )
            ),
            Items(
                id = "3",
                volumeInfo = VolumenInfo(
                    title = "La Opera",
                    authors = listOf("Maria Malla", "Pablo Celi"),
                    publishedDate = "2022-04-23",
                    description = "Asssds sddferer",
                    imageLinks = ImageLinks(
                        thumbnail = "http:imagen3.com"
                    )
                )
            )
        )
    )
}