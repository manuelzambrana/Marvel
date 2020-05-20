package com.example.marvel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel.User.Event
import com.example.marvel.adapters.FavAdapter
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_fav2.*

/**
 * A simple [Fragment] subclass.
 */
class FavFragment : Fragment() {
  val TAG = "miapp"
  val db = FirebaseFirestore.getInstance()
  var auth = FirebaseAuth.getInstance()
  lateinit var  fireAdapter: FirestoreRecyclerAdapter<Event, FavAdapter.FavViewHolder>

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_fav2, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val query = db.collection("user").document(auth.currentUser?.uid.toString())
      .collection("favs")

    val fireOptions = FirestoreRecyclerOptions.Builder<Event>().setQuery(query, Event::class.java).build()
    val mLay = GridLayoutManager(context, 2)
    myRecyclerviewFav.layoutManager = mLay
    fireAdapter = FavAdapter(fireOptions)
    myRecyclerviewFav.adapter = fireAdapter

  }
  override fun onStart() {
    super.onStart()
    fireAdapter.startListening()
  }

  override fun onStop() {
    super.onStop()
    fireAdapter.stopListening()
  }


}
