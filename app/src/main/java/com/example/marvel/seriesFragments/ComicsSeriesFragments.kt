package com.example.marvel.seriesFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.SeriesInfoActivity
import com.example.marvel.adapters.ComicSerieAdapter
import com.example.marvel.adapters.ComicsAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.comicSeries.ComicSeries
import com.example.marvel.models.comicSeries.Result
import com.example.marvel.models.comics.ComicsStatus
import com.example.marvel.objects.DataHolder
import kotlinx.android.synthetic.main.activity_comics.*
import kotlinx.android.synthetic.main.fragment_comics_series_fragments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ComicsSeriesFragments : Fragment() {
var TAG = "fragment"
var dataComicsSeries: ArrayList<Result> = arrayListOf()
  lateinit var adapter: ComicSerieAdapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
      ApiRest.initService()




    Log.v("funciona","${DataHolder.id}")


  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    return inflater.inflate(R.layout.fragment_comics_series_fragments, container, false)

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    adapter = ComicSerieAdapter(dataComicsSeries)
    recycler_comics_series.layoutManager = GridLayoutManager(this.context, 2)
    recycler_comics_series.adapter = adapter

    getComicSerie()
  }



  private fun getComicSerie() {
    val call = ApiRest.service.getComisSeries(characterId = DataHolder.id.toString()
      , offset = 0, limit = 10)
    call.enqueue(object : Callback<ComicSeries> {
      override fun onResponse(call: Call<ComicSeries>, response: Response<ComicSeries>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          val result = body.data.results

          if (result != null) {
            dataComicsSeries.clear()
            dataComicsSeries.addAll(result)
            adapter?.notifyDataSetChanged()

            dataComicsSeries.forEach {
              Log.v("funciona", it.title)
            }

          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }

      }

      override fun onFailure(call: Call<ComicSeries>, t: Throwable) {
        Log.e(TAG, t.message)

      }
    })
  }

}

