package com.example.marvel.seriesFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsSeriesFragment : Fragment() {
  var TAG = "fragment"
  var dataEventsSeries: ArrayList<Result> = arrayListOf()
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
    ApiRest.initService()
    getEventSerie()


  }

  private fun getEventSerie() {

    val call = ApiRest.service.getEventsSeries(characterId = DataHolder.id.toString()
      , offset = 0, limit = 10)
    call.enqueue(object : Callback<EventsSerie> {
      override fun onResponse(call: Call<EventsSerie>, response: Response<EventsSerie>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {

          val result = body.data.results

          if (result != null) {
            dataEventsSeries.addAll(result)
            adapter?.notifyDataSetChanged()
            dataEventsSeries.forEach {
              Log.v("events", "${it.title}")
            }


          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }

      }

      override fun onFailure(call: Call<EventsSerie>, t: Throwable) {
        Log.e(TAG, t.message)



      }
    })
  }


}
