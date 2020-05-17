package com.example.marvel.models.comicSeries

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
