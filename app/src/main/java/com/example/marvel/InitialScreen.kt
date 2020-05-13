package com.example.marvel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_initial_screen.*
import kotlinx.android.synthetic.main.fragment_home.*

class InitialScreen : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_initial_screen)


    bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.homeMenu -> {
          goToFragment(HomeFragment())
          true
        }
        R.id.favmenu -> {
          goToFragment(FavFragment())
          true
        }
        R.id.profileMenu -> {
          goToFragment(ProfileFragment())
          true
        }
        else -> false
      }

    }
    bottom_navigation_view.selectedItemId = R.id.homeMenu


  }

  fun goToFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
  }
}
