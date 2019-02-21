package ru.thstdio.dogphoto.mvp.listdog.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import java.util.*


class ListDogModel(val apiDog: ServiceDogApi) {
    val random = Random()
    fun findListAllDog(): Single<HashMap<String, List<String>>> {
        return apiDog
            .getListAllDog()
            .subscribeOn(Schedulers.io())
            .map { listDog -> listDog.message }
    }

    fun findImageDog(breed: String): Single<String> {
        return apiDog
            .getRandomBreed(breed)
            .subscribeOn(Schedulers.io())
            .map { entity -> entity.message }
            .map { listDog ->
                val number = random.nextInt(listDog.size)
                listDog[number]
            }
    }
    fun findImageDog(breed: String,subBreed:String): Single<String> {
        return apiDog
            .getRandomSubBread(breed,subBreed)
            .subscribeOn(Schedulers.io())
            .map { entity -> entity.message }
            .map { listDog ->
                val number = random.nextInt(listDog.size)
                listDog[number]
            }
    }
}