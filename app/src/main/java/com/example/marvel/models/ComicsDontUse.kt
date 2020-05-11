package com.example.marvel.models

data class ComicsDontUse(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)
