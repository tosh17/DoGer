package ru.thstdio.dogphoto.api.source

import io.reactivex.Single
import retrofit2.http.GET
import ru.thstdio.dogphoto.api.entity.dog.SingleDog

interface ServiceDogApi {
//    https://dog.ceo/api/breed

    @GET("breeds/image/random")
    abstract fun getRandomDog(): Single<SingleDog>
//    @GET("users/{user}")
//    abstract fun getSearchInfo(@Path("user") userName: String): Single<SingleDog>
//
//    @GET
//    abstract fun getUserRepos(@Url url: String): Single<List<Repository>>
}