package ru.thstdio.dogphoto.mvp.main.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy::class)
    fun selectTab(tab: String)
}