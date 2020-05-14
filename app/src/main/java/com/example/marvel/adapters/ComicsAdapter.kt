package com.example.marvel.adapters
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.ComicInfoActivity
import com.example.marvel.R
import com.example.marvel.models.comics.ComicsResult
import com.example.marvel.models.comics.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics.view.*
import kotlinx.android.synthetic.main.item_home.view.*

class ComicsAdapter (private val dataComics: ArrayList<ComicsResult>, val comicListenerInfo: ComicListenerInfo, val comicPosition: ComicPosition) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comics, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataComics[position])
    val data = dataComics?.get(position)
    data?.let {
      holder.bind(it)
      holder.itemView.setOnClickListener {
        Log.v("miapp", "He hecho click en la celda $position")
        comicPosition.onPosition(position)
      }
    }
  }
  override fun getItemCount(): Int {
    return dataComics.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ComicsResult) {
      var creadores = ""
      itemView.txtComicCharacter.text = item.title
      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.portadaComic)
       item.creators.items.forEach {nombre->
         creadores += "${nombre.name}, "
      }
      //creadores = creadores.substring(0, creadores.length-2)
      itemView.creators.text = creadores

      itemView.clickComics.setOnClickListener{
        var fecha = item.dates.elementAt(0).date.toString().substring(0,10 )
        comicListenerInfo.onComicClick(item.id)
        Log.v("miapp", item.id + " ${item.title} ")
        var intent = Intent(itemView.context, ComicInfoActivity::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("description", item.description)
        intent.putExtra("creators", creadores)
        intent.putExtra("pages", item.pageCount)
        intent.putExtra("date", fecha)
        intent.putExtra("image",image)
        itemView.context.startActivity(intent)


      }


    }

  }

  interface ComicListenerInfo {
    fun onComicClick(id: String)
  }

  interface  ComicPosition {
    fun onPosition(position: Int)

  }



}

