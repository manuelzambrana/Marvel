package com.example.marvel.models.comicSeries

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)
