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
import kotlinx.android.synthetic.main.item_stories_series.view.*

class StoriesSeriesAdapter (private val dataEventsSerie: ArrayList<com.example.marvel.models.seriesStories.Result>) : RecyclerView.Adapter<StoriesSeriesAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stories_series, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataEventsSerie[position])

  }

  override fun getItemCount(): Int {
    return dataEventsSerie.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: com.example.marvel.models.seriesStories.Result) {

      itemView.txtSeriesStories.text = item.title

      val url: String = "https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available/portrait_xlarge.jpg"
      Picasso.get().load(url).into(itemView.portadaSeStories)






    }


  }







}
