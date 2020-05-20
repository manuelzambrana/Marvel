package com.example.marvel.EventFragment

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.marvel.R
import com.example.marvel.api.ApiRest
import com.example.marvel.models.EventId.EventById
import com.example.marvel.models.EventId.ResultEventById
import com.example.marvel.objects.DataHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class InfoFragment : Fragment() {
  var TAG = "events"
  var dataEvent: ArrayList<ResultEventById> = arrayListOf()
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_info, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    ApiRest.initService()
    getEventsId(0)

  }


  private fun getEventsId(offset: Int) {
    loadingEventsInfo.visibility = View.VISIBLE
    val call = ApiRest.service.getEventsId(
      eventId = DataHolder.id.toString()
      , offset = offset, limit = 10
    )
    call.enqueue(object : Callback<EventById> {
      override fun onResponse(call: Call<EventById>, response: Response<EventById>) {
        val body = response.body()
        if (response.isSuccessful && body != null) {
          val result = body.data.results


          if (result != null) {
            dataEvent.addAll(result)
            dataEvent.forEach { resultEventById ->
              txtTitleEvent.text = resultEventById.title
              txtDescriptionEvent.text = resultEventById.description
              var creadores = ""
              resultEventById.creators.items.forEach {nombre->
                creadores += "${nombre.name}, "
              }
              txtCreatorsEvents.text = "Creadores: "+creadores
              val url: String = resultEventById.thumbnail.path.replace("http", "https")
              val extension = resultEventById.thumbnail.extension
              var image = "${url}/portrait_xlarge.$extension"
              Picasso.get().load(image).into(bckImageEvent)
              Picasso.get().load(image).into(imgEventInfo)
              resultEventById.urls.forEach{url->
                link.setOnClickListener { boton->
                  var url2 = url.url
                  var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url2))
                  startActivity(intent)

                }
              }
            }

          }
        } else {
          Log.e(TAG, response.errorBody()?.string())
        }
        loadingEventsInfo.visibility = View.GONE
      }

      override fun onFailure(call: Call<EventById>, t: Throwable) {
        Log.e(TAG, t.message)
        loadingEventsInfo.visibility = View.GONE
      }
    })
  }



}
