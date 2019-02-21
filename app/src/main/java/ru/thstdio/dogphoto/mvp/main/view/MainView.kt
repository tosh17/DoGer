package ru.thstdio.dogphoto.mvp.main.view

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun selectTab(tab: String)
}