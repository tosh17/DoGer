package ru.thstdio.dogphoto.mvp.main.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.thstdio.dogphoto.mvp.main.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
     var currentTab: String?=null


    fun currentTab(tab: String) {
        currentTab = tab
        viewState.selectTab(tab)
    }

    fun removeNavigator() {

    }

    fun initTab(tab: String) {
        if (currentTab == null) currentTab(tab)

    }
}