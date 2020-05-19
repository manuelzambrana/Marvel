package com.example.marvel.seriesFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.marvel.R
import com.example.marvel.adapters.ComicSerieAdapter
import com.example.marvel.adapters.StoriesSeriesAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.comicSeries.ComicSeries
import com.example.marvel.models.seriesStories.Result
import com.example.marvel.models.seriesStories.SeriesStories
import com.example.marvel.objects.DataHolder
import kotlinx.android.synthetic.main.fragment_comics_series_fragments.*
import kotlinx.android.synthetic.main.fragment_events_series.*
import kotlinx.android.synthetic.main.fragment_stories_series.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class StoriesSeriesFragment : Fragment() {
  var TAG = "fragment"
  var dataStoriesSeries: ArrayList<Result> = arrayListOf()
  lateinit var adapter: StoriesSeriesAdapter
  private var offset = 0
  private var aptoParaCargarComics = true
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_stories_series, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    ApiRest.initService()

    val mLayoutManager = GridLayoutManager(this.context, 2)
    recycler_stories_series.layoutManager = mLayoutManager
    adapter = StoriesSeriesAdapter(dataStoriesSeries)
    recycler_stories_series.adapter = adapter

    recycler_stories_series.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
          val visibleItemCount: Int = mLayoutManager.childCount
          val totalItemCount: Int = mLayoutManager.itemCount
          val pastVisibleItems: Int = mLayoutManager.findFirstVisibleItemPosition()
          if (aptoParaCargarComics) {

            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
              Log.i("apto", " Llegamos al final.")
              aptoParaCargarComics = false
              offset += 10
              getComicSerie(offset)

            }
          }
        }
      }
    })


    aptoParaCargarComics = true
    getComicSerie(offset)




  }

  private fun getComicSerie(offset: Int) {
    loadingStoriesSeries.visibility =  View.VISIBLE
    disponibilidadStories.visibility = View.INVISIBLE
    val call = ApiRest.service.getStoriesSeries(characterId = DataHolder.id.toString()
      , offset = offset, limit = 10)
    call.enqueue(object : Callback<SeriesStories> {
      override fun onResponse(call: Call<SeriesStories>, response: Response<SeriesStories>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          aptoParaCargarComics = true
          val result = body.data.results

          if (result != null) {
            dataStoriesSeries.addAll(result)
            adapter?.notifyDataSetChanged()



          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        if (body != null) {
          if (body.data.count > 0) {
            disponibilidadStories.visibility = View.INVISIBLE
          } else {
            disponibilidadStories.visibility = View.VISIBLE
          }
        }
        loadingStoriesSeries.visibility =  View.GONE
      }

      override fun onFailure(call: Call<SeriesStories>, t: Throwable) {
        Log.e(TAG, t.message)
        loadingStoriesSeries.visibility =  View.GONE


      }
    })
  }

}
