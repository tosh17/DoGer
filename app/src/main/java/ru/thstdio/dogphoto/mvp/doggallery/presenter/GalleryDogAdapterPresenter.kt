package ru.thstdio.dogphoto.mvp.doggallery.presenter

import android.annotation.SuppressLint
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.mvp.doggallery.model.GalleryModel
import ru.thstdio.dogphoto.mvp.doggallery.view.IGalleryHolder

class GalleryDogAdapterPresenter(var model: GalleryModel,var mainThread: Scheduler) {

    lateinit var list: List<String>
    lateinit var listLike: MutableList<Boolean>


    fun size(): Int {
        return list.size
    }

    fun onBind(holder: IGalleryHolder, position: Int) {
        holder.onBind(list[position], false)
        holder.clickLike(listLike[position])
    }

    @SuppressLint("CheckResult")
    fun clickLike(holder: IGalleryHolder, position: Int) {
        listLike[position] = !listLike[position]
        (if (listLike[position]) model.saveLike(list[position])
        else model.delLike(list[position]))
            .observeOn(mainThread)
            .subscribe { result -> holder.clickLike(listLike[position]) }

    }


}