package com.example.marvel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.adapters.CharactersAdapter
import com.example.marvel.adapters.ComicListener
import com.example.marvel.api.ApiRest
import com.example.marvel.models.AllCharacters
import com.example.marvel.models.characters
import com.google.android.gms.common.data.DataHolder
import com.trendyol.bubblescrollbarlib.BubbleTextProvider
import com.turingtechnologies.materialscrollbar.AlphabetIndicator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class MainActivity : AppCompatActivity(), ComicListener {
  val TAG = "miapp"
  var data: ArrayList<AllCharacters> = arrayListOf()
  private var adapter: CharactersAdapter? = null
  private var offset = 0
  private var aptoParaCargar = true


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    ApiRest.initService()

    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_generos.layoutManager = mLayoutManager
    adapter = CharactersAdapter(data, this)
    recycler_generos.adapter = adapter
    fastScroll.attachRecyclerView(recycler_generos)




    recycler_generos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

              getCharacters(offset)
            }
          }
        }
      }
    })


    aptoParaCargar = true
    getCharacters(offset)
  }


  private fun getCharacters(offset: Int) {
    loading.visibility = View.VISIBLE
    val call = ApiRest.service.getCharacters(offset, 10)
    call.enqueue(object : Callback<characters> {
      override fun onResponse(call: Call<characters>, response: Response<characters>) {
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
        loading.visibility = View.GONE
      }

      override fun onFailure(call: Call<characters>, t: Throwable) {
        aptoParaCargar = true
        Log.e(TAG, t.message)
        loading.visibility = View.GONE
      }
    })
  }

  override fun onCharacterClick(id: Int) {
    var comicIntent = Intent(this, ComicsActivity::class.java)
    comicIntent.putExtra("id", id)
    startActivity(comicIntent)
    Log.v(TAG, "cLICK")
  }
}
