package ru.thstdio.dogphoto.mvp.randomdog.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.Api
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog

@InjectViewState
class PresenterRandomDog : MvpPresenter<ViewRandomDog>() {
    val model = ModelRandomDog(Api())
    fun setLike() {
    }

    fun newRandom() {}
    @SuppressLint("CheckResult")
    fun init() {
        model.searRandomUrl()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { url ->
                run {
                    viewState.setDogImage(url)
                    viewState.setDogName(url.parseDogName)
                    findWebWiki(url.parseDogName)

                }
            }
    }

    private fun findWebWiki(parseDogName: String) {
        model.searchWikiLink(parseDogName)!!.subscribe(viewState::setWebView)
    }
}

private val String.parseDogName: String
    get() {
        val temp = this.split("/")
        return temp[temp.size - 2]
    }
