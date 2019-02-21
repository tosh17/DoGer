package ru.thstdio.dogphoto.mvp.listdog.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.thstdio.dogphoto.App
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogViewState
import ru.thstdio.dogphoto.navigation.LocalCiceroneHolder
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class ListDogPresenter() : MvpPresenter<ListDogViewState>() {


    val listPresenter = ListDogAdapterPresenter()

    @Inject
    lateinit var model: ListDogModel

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder


    @field:[Inject Named("mainTread")]
    lateinit var mainThread: Scheduler

    lateinit var navigationTag: String

    init {
        App.instance.daggerComponent.inject(this)
        listPresenter.model = model
        listPresenter.mainThread = mainThread
        listPresenter.ciceroneHolder = ciceroneHolder


    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        findListDog()
    }

    @SuppressLint("CheckResult")
    private fun findListDog() {
        model.findListAllDog().observeOn(mainThread).subscribe { dogMap ->
            run {
                listPresenter.setMap(dogMap)
                viewState.initAdapte()
            }
        }
    }

}