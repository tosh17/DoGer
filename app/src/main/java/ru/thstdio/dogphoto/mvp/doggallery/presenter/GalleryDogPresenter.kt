package ru.thstdio.dogphoto.mvp.doggallery.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Scheduler
import io.reactivex.Single
import ru.thstdio.dogphoto.mvp.doggallery.model.GalleryModel
import ru.thstdio.dogphoto.mvp.doggallery.view.DogGalleryViewState
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class GalleryDogPresenter : MvpPresenter<DogGalleryViewState>() {
    @Inject
    lateinit var model: GalleryModel
    @field:[Inject Named("mainTread")]
    lateinit var mainThread: Scheduler

    @Inject
    lateinit var listPresenter : GalleryDogAdapterPresenter

    @SuppressLint("CheckResult")
    fun loadMyDog() {
        model.findImageMyDog()
            .map { list -> initListPresenter(list) }
            .observeOn(mainThread)
            .subscribe { list ->
                viewState.initAdapte()
            }
    }


    @SuppressLint("CheckResult")
    fun loadDogs(breed: String, subBreed: String) {
        val subscriber: Single<List<String>>
        if (subBreed.isEmpty())
            subscriber = model.findImageDog(breed)
        else subscriber = model.findImageDog(breed, subBreed)
        subscriber
            .map { list -> initListPresenter(list) }
            .observeOn(mainThread)
            .subscribe { list ->
                viewState.initAdapte()
            }

    }

    private fun initListPresenter(list: List<String>): Boolean {
        listPresenter.list = list
        listPresenter.model = model
        listPresenter.mainThread=mainThread
        listPresenter.listLike = ArrayList()
        for (i in 0..(list.size - 1)) {
            listPresenter.listLike.add(model.loadLike(list[i]))
        }

        return true
    }

}


