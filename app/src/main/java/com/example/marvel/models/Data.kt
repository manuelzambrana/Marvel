package com.example.marvel.models

data class Data(
  val count: Int,
  val limit: Int,
  val offset: Int,
  val results: ArrayList<AllCharacters>,
  val total: Int
)
