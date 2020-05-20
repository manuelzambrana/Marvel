package com.example.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.marvel.EventFragment.ComicsEventsFragment
import com.example.marvel.EventFragment.InfoFragment
import com.example.marvel.seriesFragments.ComicsSeriesFragments
import com.example.marvel.seriesFragments.EventsSeriesFragment
import com.example.marvel.seriesFragments.StoriesSeriesFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_event_info.*
import kotlinx.android.synthetic.main.activity_series_info.*

class EventInfoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_event_info)


    val myAdapter = object : FragmentStateAdapter(this) {
      override fun createFragment(position: Int): Fragment {
        return when (position) {
          0 -> {
            InfoFragment()
          }
          1 -> {
            ComicsEventsFragment()
          }
          else -> {
            InfoFragment()
          }
        }
      }

      override fun getItemCount(): Int {
        return 2
      }
    }
    eventFragment.adapter = myAdapter


    TabLayoutMediator(mainTabLayoutEvents, eventFragment) { tab, position ->
      when (position) {
        0 -> {
          tab.text = "Informacion"
        }
        1 -> {
          tab.text = "Comics"
        }

        else -> {
          tab.text = "Informacion"
        }
      }
    }.attach()
  }
}

