package ru.thstdio.dogphoto.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.navigation.screen.Screens

class MainActivity : FragmentActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                selectTab("RandomDog")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                selectTab("RandomDog")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                selectTab("RandomDog")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        selectTab("RandomDog")
    }


    private fun selectTab(tab: String) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f.isVisible) {
                    currentFragment = f
                    break
                }
            }
        }
        val newFragment = fm.findFragmentByTag(tab)

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            when (tab) {
                "RandomDog" ->
                    transaction.add(R.id.fragment_conteiner, Screens.RandomDogScreen.fragment, tab)
            }
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }
}
