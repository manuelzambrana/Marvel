package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.adapters.CreatorsAdapter
import com.example.marvel.adapters.SerieListener
import com.example.marvel.adapters.SeriesAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.characters
import com.example.marvel.models.series.Result
import com.example.marvel.models.series.Series
import com.example.marvel.objects.DataHolder
import kotlinx.android.synthetic.main.activity_creators.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_series.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesActivity : AppCompatActivity(), SerieListener {
  var TAG = "miapp"
  var dataSeries: ArrayList<Result> = arrayListOf()
  private var adapter: SeriesAdapter? = null
  private var offset = 0
  private var aptoParaCargarComics = true
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_series)

    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_series.layoutManager = mLayoutManager
    adapter = SeriesAdapter(dataSeries,this)
    recycler_series.adapter = adapter
    ApiRest.initService()
    fastScrollSeries.attachRecyclerView(recycler_series)

    ApiRest.initService()
    recycler_series.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
              getSeries(offset)

            }
          }
        }
      }
    })


    aptoParaCargarComics = true
    getSeries(offset)
  }


  fun getSeries(offset: Int) {
    loadingSeries.visibility = View.VISIBLE
    val call = ApiRest.service.getSeries(offset, limit = 10)
    call.enqueue(object : Callback<Series> {
      override fun onResponse(call: Call<Series>, response: Response<Series>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          aptoParaCargarComics = true
          val result = body.data.results

          if (result != null) {
            Log.i(TAG, body.toString())
            dataSeries.addAll(result)
            adapter?.notifyDataSetChanged()
          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        loadingSeries.visibility = View.GONE
      }

      override fun onFailure(call: Call<Series>, t: Throwable) {
        loadingSeries.visibility = View.GONE

        Log.e(TAG, t.message)

      }
    })
  }

  override fun onSerieClick(id: Int) {
      var serieIntent = Intent(this, SeriesInfoActivity::class.java)
      DataHolder.id = id
      startActivity(serieIntent)
  }
}
