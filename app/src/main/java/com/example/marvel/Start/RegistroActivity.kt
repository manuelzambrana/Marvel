package com.example.marvel.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.marvel.InitialScreen
import com.example.marvel.R
import com.example.marvel.User.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
  val TAG = "miapp"
  var auth: FirebaseAuth = FirebaseAuth.getInstance()
  val db = FirebaseFirestore.getInstance()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registro)

    btnSignUpNewUser.setOnClickListener {
      val email = edEmailSign.text.toString()
      val pass = edPassSign.text.toString()
      val name = edUserSign.text.toString()
      signUp(name, email, pass)
    }
    goLogin.setOnClickListener {
      startActivity(Intent(this, LoginActivity::class.java))
      finish()
    }
  }

  private fun signUp(name: String, email: String, pass: String) {
  auth.createUserWithEmailAndPassword(email, pass)
    .addOnCompleteListener(this) {task ->
      if (task.isSuccessful){
        val user = User()
        user.email = email
        user.name = name
        user.id = auth.currentUser?.uid

        db.collection("user").document(user.id.toString()).set(user).addOnSuccessListener { document->
          startActivity(Intent(this, InitialScreen::class.java))
          finish()
        }.addOnFailureListener {e->
          Log.w(TAG, "Error adding document", e)

        }
      }else {
        Toast.makeText(
          baseContext, "Registro no válido",
          Toast.LENGTH_SHORT
        ).show()
      }

    }
  }
}
