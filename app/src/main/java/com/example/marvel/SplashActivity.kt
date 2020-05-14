package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash2.*
import render.animations.*;

class SplashActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash2)

    val render = Render(this)
    render.setAnimation(Zoom().In(logo))
    render.setDuration(1400)
    render.start()

    Handler().postDelayed(({
      startActivity(Intent(this,InitialScreen::class.java))
      finish()


    }),1500)
  }
}
