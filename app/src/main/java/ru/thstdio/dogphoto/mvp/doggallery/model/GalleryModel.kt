package ru.thstdio.dogphoto.mvp.doggallery.model

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.repo.IRepoImage

class GalleryModel(val apiDog: ServiceDogApi,val imageRepo: IRepoImage) {

    fun findImageDog(breed: String, subBreed: String): Single<List<String>> {
        return apiDog
            .getRandomSubBread(breed, subBreed)
            .subscribeOn(Schedulers.io())
            .map { entity -> entity.message }

    }

    fun findImageDog(breed: String): Single<List<String>> {
        return apiDog
            .getRandomBreed(breed)
            .subscribeOn(Schedulers.io())
            .map { entity -> entity.message }

    }
    fun findImageMyDog(): Single<List<String>> {
        return Single.just(true)
            .subscribeOn(Schedulers.io())
            .map{start->imageRepo.loadMuDog()}

    }
    fun loadLike(url:String):Boolean{
       return  imageRepo.containLike(url)
    }
    fun saveLike(url:String): Single<Boolean> {
        return Single.just(url)
            .subscribeOn(Schedulers.io())
            .map{path->imageRepo.saveLike(path)}

    }
    fun delLike(url:String): Single<Boolean>{
       return  Single.just(url)
            .subscribeOn(Schedulers.io())
            .map{path->imageRepo.deleteLike(path)}
    }


}