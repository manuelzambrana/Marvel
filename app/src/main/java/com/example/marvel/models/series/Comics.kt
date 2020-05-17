package com.example.marvel.models.series

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)
