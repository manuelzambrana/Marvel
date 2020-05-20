package com.example.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.adapters.CharactersAdapter
import com.example.marvel.adapters.CreatorsAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.characters
import com.example.marvel.models.creators.MyCreators
import com.example.marvel.models.creators.ResultCreators
import kotlinx.android.synthetic.main.activity_comics.*
import kotlinx.android.synthetic.main.activity_creators.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreatorsActivity : AppCompatActivity() {
  val TAG = "miapp"
  var dataCreators: ArrayList<ResultCreators> = arrayListOf()
  private var offset = 0
  private var aptoParaCargarComics = true
  private var adapter: CreatorsAdapter? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_creators)
    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_creators.layoutManager = mLayoutManager
    adapter = CreatorsAdapter(dataCreators)
    recycler_creators.adapter = adapter
    ApiRest.initService()
    fastScrollCreators.attachRecyclerView(recycler_creators)

    recycler_creators.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
              get(offset)

            }
          }
        }
      }
    })
    aptoParaCargarComics = true
    get(offset)
  }


  private fun get(offset: Int) {
    loadingCreators.visibility = View.VISIBLE
    val call = ApiRest.service.getCreators(limit = 10, offset = offset)
    call.enqueue(object : Callback<MyCreators> {
      override fun onResponse(call: Call<MyCreators>, response: Response<MyCreators>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          aptoParaCargarComics = true
          val result = body.data.results

          if (result != null) {
            Log.i("miapp", body.toString())
            dataCreators.addAll(result)
            adapter?.notifyDataSetChanged()

          }
        } else {
          Log.e("miapp", response.errorBody()?.string())
        }
        loadingCreators.visibility = View.GONE
      }

      override fun onFailure(call: Call<MyCreators>, t: Throwable) {
        loadingCreators.visibility = View.GONE
        Log.e("miapp", t.message)

      }
    })
  }
}

