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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comics.view.*
import kotlinx.android.synthetic.main.item_creators.view.*
import kotlinx.android.synthetic.main.item_home.view.*

class CreatorsAdapter (private val dataCreators: ArrayList<ResultCreators>) : RecyclerView.Adapter<CreatorsAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_creators, parent, false)
    return ViewHolder(view)
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(dataCreators[position])
  }
  override fun getItemCount(): Int {
    return dataCreators.size

  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: ResultCreators) {
      itemView.txtNameCreator.text = item.fullName
      val url: String = item.thumbnail.path.replace("http", "https")
      val extension = item.thumbnail.extension
      var image = "${url}/portrait_xlarge.$extension"
      Picasso.get().load(image).into(itemView.imgCreator)



    }

  }



}


