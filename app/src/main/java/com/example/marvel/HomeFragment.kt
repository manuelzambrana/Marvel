package com.example.marvel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {



  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?

  ): View? {


    // Inflate the layout for this fragment

    var view: View = inflater.inflate(R.layout.fragment_home, container, false)

   view.goToCharacters.setOnClickListener {view ->
      var characters = Intent(activity, MainActivity::class.java)
     startActivity(characters)
    }

    view.goToCreators.setOnClickListener { view->
      var creators = Intent(activity, CreatorsActivity::class.java)
      startActivity(creators)
    }

    view.goToSeries.setOnClickListener { view ->
      var series = Intent(activity, SeriesActivity::class.java)
      startActivity(series)
    }
    return view




  }

}
