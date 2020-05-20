package com.example.marvel.models.EventId

data class Data(
  val count: Int,
  val limit: Int,
  val offset: Int,
  val results: List<ResultEventById>,
  val total: Int
)
