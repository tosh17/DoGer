package ru.thstdio.dogphoto.mvp.randomdog.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
open class PresenterRandomDog() : MvpPresenter<ViewRandomDog>() {

    @Inject
    lateinit var model: ModelRandomDog

 //   @Inject @Named("mainTread")
    @field:[Inject Named("mainTread")]
    lateinit var mainThread: Scheduler

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        findNewDog()
    }

    @SuppressLint("CheckResult")
    private fun findNewDog() {
        model.searchRandomUrl()
            .observeOn(mainThread)
            .subscribe { url ->
                run {
                    viewState.setDogImage(url)
                    viewState.setDogName(url.parseDogName)
                    findWebWiki(url.parseDogName)

                }
            }
    }


    @SuppressLint("CheckResult")
    private fun findWebWiki(parseDogName: String) {
        model.searchWikiLink(" dog $parseDogName")!!.observeOn(mainThread).subscribe(
            viewState::setWebView
        )
    }

    var isTitlechange: Boolean = false
    fun appBarLayout(p1: Float) {
        if (p1 < 0.5 && !isTitlechange) {
            isTitlechange = true
            viewState.changeTitle(isTitlechange)
        }
        if (p1 > 0.5 && isTitlechange) {
            isTitlechange = false
            viewState.changeTitle(isTitlechange)
        }
        Log.d("Test", " " + p1)
    }

    fun clickFab() {
        findNewDog()
    }
}

private val String.parseDogName: String
    get() {
        val temp = this.split("/")
        return temp[temp.size - 2]
    }
