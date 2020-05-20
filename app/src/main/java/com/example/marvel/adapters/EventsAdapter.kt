package com.example.marvel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.models.AllCharacters
import com.example.marvel.models.creators.MyCreators
import com.example.marvel.models.creators.ResultCreators
import com.example.marvel.models.events.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics.view.*
import kotlinx.android.synthetic.main.item_creators.view.*
import kotlinx.android.synthetic.main.item_events.view.*
import kotlinx.android.synthetic.main.item_home.view.*

class EventsAdapter (private val dataEvents: ArrayList<Result>, val eventListener: EventListener) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {




  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_events, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataEvents[position])
  }
  override fun getItemCount(): Int {
    return dataEvents.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Result) {
      itemView.txtEvent.text = item.title
      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.portadEvent)

      itemView.portadEvent.setOnClickListener{
        eventListener.onEventClick(item.id)
        Log.v("miapp", item.id.toString())
      }



    }

  }

interface  EventListener {
  fun onEventClick(id: Int)
}

}


