package com.example.marvel.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.marvel.InitialScreen
import com.example.marvel.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
  val TAG = "miapp"
  var auth: FirebaseAuth = FirebaseAuth.getInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    btnSignup.setOnClickListener {
      startActivity(Intent(this, RegistroActivity::class.java))
      finish()
    }

    btnLogin.setOnClickListener {
      val email = edEmail.text.toString()
      val pass = edPass.text.toString()
      login(email,pass)
    }
  }

  private fun login(email: String, pass: String) {
    auth.signInWithEmailAndPassword(email, pass)
      .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          Log.d(TAG, "signInWithEmail:success")

          startActivity(Intent(this, InitialScreen::class.java))
          finish()

        } else {
          Log.w(TAG, "signInWithEmail:failure", task.exception)
          Toast.makeText(
            baseContext, "Authentication failed.",
            Toast.LENGTH_SHORT
          ).show()
        }
      }
  }
}
