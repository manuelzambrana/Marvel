package com.example.marvel.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {

  lateinit var service: ApiService
  val URL = "https://gateway.marvel.com:443"
  val URL_IMAGES = ""
  val api_key = "99fb6b7e6c55754494fcdc8c3eec3a37"
  val private_key = "bed80e42ab767e1a2a190ad488cc51206682cec9"
  val hash = "57afda495af48bd0285f97b618d335e0"
  val ts = "1"
  val limit = "10"
  val offset = "0"

  fun initService() {
    val retrofit = Retrofit.Builder()
      .baseUrl(URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
    service = retrofit.create(ApiService::class.java)
  }
}
