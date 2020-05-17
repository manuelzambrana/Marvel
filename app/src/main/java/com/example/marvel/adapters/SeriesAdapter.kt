package com.example.marvel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.models.creators.ResultCreators
import com.example.marvel.models.series.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_creators.view.*
import kotlinx.android.synthetic.main.item_series.view.*

class SeriesAdapter  (private val dataSeries: ArrayList<Result>, val seriesListener: SerieListener) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {




  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_series, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataSeries[position])
  }
  override fun getItemCount(): Int {
    return dataSeries.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Result) {
    itemView.txtNameSerie.text = item.title

      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.imgSerie)

      itemView.imgSerie.setOnClickListener{
        seriesListener.onSerieClick(item.id)
        Log.v("serie", item.id.toString())
      }


    }

  }



}

interface SerieListener {
  fun onSerieClick(id: Int)
}

