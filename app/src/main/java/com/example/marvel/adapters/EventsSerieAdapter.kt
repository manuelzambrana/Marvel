package com.example.marvel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.models.comicSeries.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics_series.view.*
import kotlinx.android.synthetic.main.item_events_serie.view.*

class EventsSerieAdapter (private val dataEventsSerie: ArrayList<com.example.marvel.models.eventsSerie.Result>) : RecyclerView.Adapter<EventsSerieAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_events_serie, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataEventsSerie[position])

  }

  override fun getItemCount(): Int {
    return dataEventsSerie.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: com.example.marvel.models.eventsSerie.Result) {

      itemView.txtSeriesEvent.text = item.title

      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.portadaSeEvent)
      Log.v("funciona2", "${item.title}")



    }


  }







}
