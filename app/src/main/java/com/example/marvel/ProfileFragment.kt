package com.example.marvel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.marvel.Start.LoginActivity
import com.example.marvel.User.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
  val TAG = "miapp"
  val db = FirebaseFirestore.getInstance()
  var auth = FirebaseAuth.getInstance()
  var myUser: User? = null
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    val query = db.collection("user").document(auth.currentUser?.uid.toString())

    query.get().addOnSuccessListener { document->
      if (document != null) {
        myUser = document.toObject(User::class.java)
        editName.setText(myUser?.name)
        editEmail.setText(myUser?.email)
        editEmail.isEnabled = false
        editEmail.alpha = 0.5F
      }else{
        Log.d(TAG, "no such document")
      }
    }.addOnFailureListener{exception ->
      Log.d(TAG, "get failed with", exception)

    }

    btnLogOut.setOnClickListener {
      FirebaseAuth.getInstance().signOut()
      val goToLogin = Intent(context, LoginActivity::class.java)
      startActivity(goToLogin)
      activity?.finish()

    }

    btnEdit.setOnClickListener {
      myUser?.name = editName.text.toString()
      db.collection("user").document(auth.currentUser?.uid.toString()).set(myUser!!)
    }

    super.onViewCreated(view, savedInstanceState)
  }

}
