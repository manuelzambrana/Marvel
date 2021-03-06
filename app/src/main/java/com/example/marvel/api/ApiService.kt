package com.example.marvel.api


import com.example.marvel.models.EventId.EventById
import com.example.marvel.models.characters
import com.example.marvel.models.comicSeries.ComicSeries
import com.example.marvel.models.comics.ComicsStatus
import com.example.marvel.models.creators.MyCreators
import com.example.marvel.models.events.Events
import com.example.marvel.models.eventsSerie.EventsSerie
import com.example.marvel.models.series.Series
import com.example.marvel.models.seriesStories.SeriesStories
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

  @GET("/v1/public/series?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getSeries(@Query("offset") offset:Int,
                  @Query("limit") limit:Int

  ):Call<Series>

  @GET("/v1/public/series/{seriesId}/comics?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getComisSeries(@Path("seriesId") characterId: String,
                @Query("offset") offset:Int,
                @Query("limit") limit:Int

  ): Call<ComicSeries>

  @GET("/v1/public/series/{seriesId}/events?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getEventsSeries(@Path("seriesId") characterId: String,
                     @Query("offset") offset:Int,
                     @Query("limit") limit:Int

  ): Call<EventsSerie>

  @GET("/v1/public/series/{seriesId}/stories?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getStoriesSeries(@Path("seriesId") characterId: String,
                      @Query("offset") offset:Int,
                      @Query("limit") limit:Int

  ): Call<SeriesStories>

  @GET("/v1/public/events?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getEvents(@Query("offset") offset:Int,
                @Query("limit") limit:Int

  ):Call<Events>

  @GET("/v1/public/events/{eventId}?ts=1&apikey=99fb6b7e6c55754494fcdc8c3eec3a37&hash=57afda495af48bd0285f97b618d335e0")
  fun getEventsId(@Path("eventId") eventId: String,
                 @Query("offset") offset:Int,
                @Query("limit") limit:Int

  ):Call<EventById>

}
