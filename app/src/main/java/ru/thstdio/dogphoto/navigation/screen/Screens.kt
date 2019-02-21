package ru.thstdio.dogphoto.navigation.screen

import android.support.v4.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.thstdio.dogphoto.ui.fragment.CurrentDogGallery
import ru.thstdio.dogphoto.ui.fragment.ListDogFragment
import ru.thstdio.dogphoto.ui.fragment.RandomDogFragment

object Screens {
    object RandomDogScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return RandomDogFragment()
        }
    }
    object ListDogScreen : SupportAppScreen() {
        lateinit var currentTag: String
        public fun tag(tag:String):ListDogScreen{
            currentTag=tag
            return this
        }
        override fun getFragment(): Fragment {
            return ListDogFragment.getInstance(currentTag)
        }
    }
    object GalleryDogScreen : SupportAppScreen() {
        lateinit var mBreed:String
        lateinit var mSubBreed:String
        fun initValue( breed:String, subBreed:String): GalleryDogScreen {
            mBreed=breed
            mSubBreed=subBreed
            return this
        }
        fun initValueMyDog( ): GalleryDogScreen {
            mBreed= CurrentDogGallery.LIKE_DOG
            mSubBreed=""
            return this
        }
        override fun getFragment(): Fragment {
            return CurrentDogGallery.getInstance(mBreed,mSubBreed)
        }
    }
}