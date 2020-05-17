package com.example.marvel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.models.comicSeries.Result
import kotlinx.android.synthetic.main.item_comics_series.view.*

class ComicSerieAdapter (private val dataComicSerie: ArrayList<Result>) : RecyclerView.Adapter<ComicSerieAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comics_series, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataComicSerie[position])

    }

  override fun getItemCount(): Int {
    return dataComicSerie.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Result) {

      itemView.txtSeriesComic.text = item.title
      Log.v("funciona2", "${item.title}")



      }


    }







}
