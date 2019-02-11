package ru.thstdio.dogphoto.mvp.listdog.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.source.ServiceDogApi

class ListDogModel(val apiDog: ServiceDogApi) {
    fun findListAllDog(): Single<HashMap<String, List<String>>> {
        return apiDog
            .getListAllDog()
            .subscribeOn(Schedulers.io())
            .map { listDog -> listDog.message }
    }
}