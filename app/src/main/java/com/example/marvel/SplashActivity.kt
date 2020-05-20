package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.marvel.Start.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_splash2.*
import render.animations.*;

class SplashActivity : AppCompatActivity() {
  var auth = FirebaseAuth.getInstance()

  var db = FirebaseFirestore.getInstance()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash2)

    val render = Render(this)
    render.setAnimation(Zoom().In(logo))
    render.setDuration(1400)
    render.start()

    Handler().postDelayed(({
      checkUser()


    }),1500)
  }

  fun checkUser() {
    if (auth.currentUser != null) {
      Log.v("miapp", "El user logueado es ${auth.currentUser!!.email}")
      startActivity(Intent(this, InitialScreen::class.java))
    } else {
      startActivity(Intent(this, LoginActivity::class.java))
    }

    finish()

  }
}
