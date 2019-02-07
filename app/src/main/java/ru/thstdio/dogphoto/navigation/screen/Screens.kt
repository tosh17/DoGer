package ru.thstdio.dogphoto.navigation.screen

import android.support.v4.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.thstdio.dogphoto.ui.fragment.RandomDogFragment

object Screens {
    object RandomDogScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RandomDogFragment()
        }
    }
}