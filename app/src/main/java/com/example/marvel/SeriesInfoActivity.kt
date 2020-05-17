package com.example.marvel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvel.seriesFragments.ComicsSeriesFragments
import com.example.marvel.seriesFragments.EventsSeriesFragment
import com.example.marvel.seriesFragments.StoriesSeriesFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_series_info.*
import kotlinx.android.synthetic.main.fragment_comics_series_fragments.*


class SeriesInfoActivity : AppCompatActivity() {
var idSerie = ""
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_series_info)


    val myAdapter = object : FragmentStateAdapter(this) {
      override fun createFragment(position: Int): Fragment {
        return when (position) {
          0 -> {
            ComicsSeriesFragments()
          }
          1 -> {
            EventsSeriesFragment()
          }
          2 -> {
            StoriesSeriesFragment()
          } else -> {
            ComicsSeriesFragments()
          }
        }
      }
      override fun getItemCount(): Int {
        return 3
      }
    }
    seriesFragment.adapter = myAdapter

    TabLayoutMediator(mainTabLayout, seriesFragment) { tab, position ->
      when (position) {
        0 -> {
          tab.text = "Comics"
        }
        1 -> {
          tab.text = "Events"
        }
        2-> {
          tab.text = "Stories"
        }
        else -> {
          tab.text = "Stories"
        }
      }
    }.attach()
  }


}
