package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_initial_screen.*

class InitialScreen : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_initial_screen)

    irPersonajes.setOnClickListener {
      var comicIntent = Intent(this, MainActivity::class.java)

      startActivity(comicIntent)

    }
  }
}
