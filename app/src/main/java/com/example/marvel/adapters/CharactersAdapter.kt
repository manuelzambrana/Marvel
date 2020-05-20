package com.example.marvel.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.models.AllCharacters
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class CharactersAdapter (val data: ArrayList<AllCharacters>, val characterListener: ComicListener) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>()  {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(data[position])
    if (position==(itemCount-1)) {

    }
  }
  override fun getItemCount(): Int {
    return data.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: AllCharacters) {
      itemView.txtComicName.text = item.name
      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.portada)
      itemView.portada.setOnClickListener {
        characterListener.onCharacterClick(item.id)
        Log.v("miapp", item.id.toString())
      }


    }

  }




}

interface ComicListener {
  fun onCharacterClick(id: Int)
}
