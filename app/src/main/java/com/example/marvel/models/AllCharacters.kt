package com.example.marvel.models

data class AllCharacters(
  val comics: ComicsDontUse,
  val description: String,
  val events: Events,
  val id: Int,
  val modified: String,
  val name: String,
  val resourceURI: String,
  val series: Series,
  val stories: Stories,
  val thumbnail: Thumbnail,
  val urls: List<Url>
)
