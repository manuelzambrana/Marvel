package com.example.marvel.models.comics

data class Data(
  val count: String,
  val limit: String,
  val offset: String,
  val results: ArrayList<ComicsResult>,
  val total: String
)
