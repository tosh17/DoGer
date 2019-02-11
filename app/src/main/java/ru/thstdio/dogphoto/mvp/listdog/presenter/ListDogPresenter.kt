package ru.thstdio.dogphoto.mvp.listdog.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.App
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogViewState
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class ListDogPresenter : MvpPresenter<ListDogViewState>() {
    val listPresenter = ListDogAdapterPresenter()

    @Inject
    lateinit var model: ListDogModel


    @field:[Inject Named("mainTread")]
    lateinit var mainThread: Scheduler

    init {
        App.instance.daggerComponent.inject(this)
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