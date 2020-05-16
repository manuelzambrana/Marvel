package com.example.marvel.api


import com.example.marvel.models.characters
import com.example.marvel.models.comics.ComicsStatus
import com.example.marvel.models.creators.MyCreators
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Path

interface ApiService {
  @GET("/v1/public/characters?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getCharacters(@Query("offset") offset:Int,
                    @Query("limit") limit:Int

  ): Call<characters>

  @GET("/v1/public/characters/{characterId}/comics?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getComics(@Path("characterId") characterId: String,
                @Query("offset") offset:Int,
                @Query("limit") limit:Int

  ): Call<ComicsStatus>

  @GET("/v1/public/creators?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getCreators(@Query("offset") offset:Int,
                    @Query("limit") limit:Int

  ):Call<MyCreators>

}
