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
import com.example.marvel.adapters.EventsSerieAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.comicSeries.ComicSeries
import com.example.marvel.models.eventsSerie.EventsSerie
import com.example.marvel.models.eventsSerie.Result
import com.example.marvel.objects.DataHolder
import kotlinx.android.synthetic.main.fragment_comics_series_fragments.*
import kotlinx.android.synthetic.main.fragment_events_series.*
import kotlinx.android.synthetic.main.item_events_serie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsSeriesFragment : Fragment() {
  var TAG = "fragment"
  var dataEventsSeries: ArrayList<Result> = arrayListOf()
  private var offset = 0
  private var aptoParaCargarComics = true
  lateinit var adapter: EventsSerieAdapter
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_events_series, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val mLayoutManager = GridLayoutManager(this.context, 2)
    recycler_events_series.layoutManager = mLayoutManager
    adapter = EventsSerieAdapter(dataEventsSeries)
    recycler_events_series.adapter = adapter

    recycler_events_series.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
              getEventSerie(offset)

            }
          }
        }
      }
    })
    aptoParaCargarComics = true

    ApiRest.initService()
    getEventSerie(offset)


  }

  private fun getEventSerie(offset: Int) {
    loadingEventsSeries.visibility = View.VISIBLE
    disponibilidad.visibility = View.INVISIBLE
    val call = ApiRest.service.getEventsSeries(characterId = DataHolder.id.toString()
      , offset = offset, limit = 10)
    call.enqueue(object : Callback<EventsSerie> {
      override fun onResponse(call: Call<EventsSerie>, response: Response<EventsSerie>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          aptoParaCargarComics = true
          val result = body.data.results

          if (result != null) {
            dataEventsSeries.addAll(result)

            adapter?.notifyDataSetChanged()



          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        if (body != null) {
          if (body.data.count > 0) {
            disponibilidad.visibility = View.INVISIBLE
            } else {
            disponibilidad.visibility = View.VISIBLE
            }
        }
        loadingEventsSeries.visibility = View.GONE


      }

      override fun onFailure(call: Call<EventsSerie>, t: Throwable) {
        Log.e(TAG, t.message)
        loadingEventsSeries.visibility = View.GONE




      }
    })
  }


}
