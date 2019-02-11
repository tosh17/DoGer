package ru.thstdio.dogphoto.mvp.randomdog.view

import com.arellomobile.mvp.MvpView

interface ViewRandomDog:MvpView {
    fun setDogImage(path:String)
    fun setDogName(name:String)
    fun setWebView(url:String)
    fun changeTitle(isTitlechange: Boolean)
}