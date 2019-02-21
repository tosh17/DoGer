package ru.thstdio.dogphoto.mvp.main.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.thstdio.dogphoto.mvp.main.view.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    lateinit var currentTab: String

    fun currentTab(tab: String) {
        currentTab = tab
        viewState.selectTab(tab)
    }

    fun removeNavigator() {

    }
}