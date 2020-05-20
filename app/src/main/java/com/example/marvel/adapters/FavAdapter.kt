package com.example.marvel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.User.Event
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fav.view.*

class FavAdapter(recyclerOptions: FirestoreRecyclerOptions<Event>)
  : FirestoreRecyclerAdapter<Event, FavAdapter.FavViewHolder>(recyclerOptions) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.FavViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false)
    return FavViewHolder(view)
  }

  override fun onBindViewHolder(holder: FavAdapter.FavViewHolder, position: Int, model: Event) {
    holder.bindData(model)
  }


  inner  class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(fav: Event) {
      Picasso.get().load(fav.image).into(itemView.portadafav)
      itemView.txtComicNamefav.text = fav.name
    }

  }
}
