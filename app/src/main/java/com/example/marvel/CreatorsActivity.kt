package com.example.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel.adapters.CharactersAdapter
import com.example.marvel.adapters.CreatorsAdapter
import com.example.marvel.api.ApiRest
import com.example.marvel.models.characters
import com.example.marvel.models.creators.MyCreators
import com.example.marvel.models.creators.ResultCreators
import kotlinx.android.synthetic.main.activity_creators.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreatorsActivity : AppCompatActivity() {
  val TAG = "miapp"
  var dataCreators: ArrayList<ResultCreators> = arrayListOf()
  var je = ""
  private var adapter: CreatorsAdapter? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_creators)
    val mLayoutManager = GridLayoutManager(this, 2)
    recycler_creators.layoutManager = mLayoutManager
    adapter = CreatorsAdapter(dataCreators)
    recycler_creators.adapter = adapter
    ApiRest.initService()
    get()
  }


  private fun get() {
    val call = ApiRest.service.getCreators(limit = 10, offset = 10)
    call.enqueue(object : Callback<MyCreators> {
      override fun onResponse(call: Call<MyCreators>, response: Response<MyCreators>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          val result = body.data.results

          if (result != null) {
            Log.i("miapp", body.toString())
            dataCreators.addAll(result)
            adapter?.notifyDataSetChanged()

          }
        } else {
          Log.e("miapp", response.errorBody()?.string())
        }
      }

      override fun onFailure(call: Call<MyCreators>, t: Throwable) {
        Log.e("miapp", t.message)

      }
    })
  }
}

