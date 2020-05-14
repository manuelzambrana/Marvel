package com.example.marvel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import kotlinx.android.synthetic.main.activity_comic_info.*


class ComicInfoActivity : AppCompatActivity() {
  var titleComic = ""
  var description = ""
  var creadores = ""
  var date = ""
  var image = ""
  var position = 0
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_comic_info)
    val bundle = intent.extras
    if (bundle != null) {
      titleComic = bundle.getString("title").toString()
      description = bundle.getString("description").toString()
      creadores = bundle.getString("creators").toString()
      date = bundle.getString("date").toString()
      image = bundle.getString("image").toString()
      position = bundle.getInt("position")

      Log.v("miapp", titleComic)
    }

    txtTitleComic.text = titleComic
    txtDescription.text = description
    txtCreators.text = "Creadores: "+creadores
    txtDate.text = "Fecha de salida: "+date
    Picasso.get().load(image).into(imgComicInfo)
    Picasso.get().load(image).into(bckImage)

    next.setOnClickListener{
      txtTitleComic.text = titleComic
      Log.v("miapp", "$position")
    }

  }
}
