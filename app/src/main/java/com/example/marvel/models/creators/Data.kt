package com.example.marvel.models.creators

data class Data(
  val count: Int,
  val limit: Int,
  val offset: Int,
  val results: List<ResultCreators>,
  val total: Int
)
