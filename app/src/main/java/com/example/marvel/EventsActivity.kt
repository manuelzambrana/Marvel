package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.adapters.CharactersAdapter
import com.example.marvel.adapters.EventsAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.AllCharacters
import com.example.marvel.models.characters
import com.example.marvel.models.events.Events
import com.example.marvel.models.events.Result
import com.example.marvel.objects.DataHolder
import kotlinx.android.synthetic.main.activity_events.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsActivity : AppCompatActivity(), EventsAdapter.EventListener {
  val TAG = "miapp"
  var data: ArrayList<Result> = arrayListOf()
  private var offset = 0
  private var aptoParaCargar = true
  private var adapter: EventsAdapter? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_events)
    ApiRest.initService()


    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_events.layoutManager = mLayoutManager
    adapter = EventsAdapter(data, this)
    recycler_events.adapter = adapter
    fastScrollEvents.attachRecyclerView(recycler_events)


    recycler_events.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
          val visibleItemCount: Int = mLayoutManager.childCount
          val totalItemCount: Int = mLayoutManager.itemCount
          val pastVisibleItems: Int = mLayoutManager.findFirstVisibleItemPosition()
          Log.v("scroll", "$pastVisibleItems")
          Log.v("scroll", "$totalItemCount")
          Log.v("scroll", "$visibleItemCount")
          if (aptoParaCargar) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
              Log.i(TAG, " Llegamos al final.")
              aptoParaCargar = false
              offset += 10

              getEvents(offset)
            }
          }
        }
      }
    })
    aptoParaCargar = true
    getEvents(offset)

  }




  private fun getEvents(offset: Int) {
    loadingEvents.visibility = View.VISIBLE
    val call = ApiRest.service.getEvents(offset, 10)
    call.enqueue(object : Callback<Events> {
      override fun onResponse(call: Call<Events>, response: Response<Events>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          aptoParaCargar = true
          val result = body.data.results

          if (result != null) {
            Log.i(TAG, body.toString())
            data.addAll(result)
            adapter?.notifyDataSetChanged()


          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        loadingEvents.visibility = View.GONE
      }

      override fun onFailure(call: Call<Events>, t: Throwable) {
        aptoParaCargar = true
        Log.e(TAG, t.message)
        loadingEvents.visibility = View.GONE
      }
    })
  }

  override fun onEventClick(id: Int) {
    var eventIntent = Intent(this, EventInfoActivity::class.java)
    DataHolder.id = id
    startActivity(eventIntent)
  }

}
