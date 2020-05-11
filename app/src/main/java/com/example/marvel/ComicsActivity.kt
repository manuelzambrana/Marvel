package com.example.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.adapters.CharactersAdapter

import com.example.marvel.adapters.ComicsAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.comics.ComicsResult
import com.example.marvel.models.comics.ComicsStatus
import kotlinx.android.synthetic.main.activity_comics.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicsActivity : AppCompatActivity() {
  val TAG = "miapp"
  var idPersonaje = ""
  var dataComics: ArrayList<ComicsResult> = arrayListOf()
  private var adapter: ComicsAdapter? = null
  private var offset = 0
  private var aptoParaCargarComics = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comics)

    val bundle = intent.extras
    if (bundle != null) {
      idPersonaje = bundle.getInt("id").toString()
      Log.v(TAG, idPersonaje)
    }
    ApiRest.initService()


    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_comics.layoutManager = mLayoutManager
    adapter = ComicsAdapter(dataComics)
    recycler_comics.adapter = adapter

    recycler_comics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
          val visibleItemCount: Int = mLayoutManager.childCount
          val totalItemCount: Int = mLayoutManager.itemCount
          val pastVisibleItems: Int = mLayoutManager.findFirstVisibleItemPosition()
          if (aptoParaCargarComics) {
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
              Log.i(TAG, " Llegamos al final.")
              aptoParaCargarComics = false
              offset += 10
              getComics(offset)
            }
          }
        }
      }
    })
    aptoParaCargarComics = true
    getComics(offset)

  }

  private fun getComics(offset: Int) {
    loadingComics.visibility = View.VISIBLE
    val call = ApiRest.service.getComics(characterId = idPersonaje, offset = offset, limit = 10)
    call.enqueue(object : Callback<ComicsStatus> {
      override fun onResponse(call: Call<ComicsStatus>, response: Response<ComicsStatus>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {

          aptoParaCargarComics = true
          val result = body.data.results

          if (result != null) {
            dataComics.addAll(result)
            adapter?.notifyDataSetChanged()
          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        loadingComics.visibility = View.GONE
      }

      override fun onFailure(call: Call<ComicsStatus>, t: Throwable) {
        Log.e(TAG, t.message)
        loadingComics.visibility = View.GONE
      }
    })
  }


}



