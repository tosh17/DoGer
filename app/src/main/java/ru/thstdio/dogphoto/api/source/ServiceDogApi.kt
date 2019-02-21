package ru.thstdio.dogphoto.api.source

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.thstdio.dogphoto.api.entity.dog.AllDogMessage
import ru.thstdio.dogphoto.api.entity.dog.BreedDog
import ru.thstdio.dogphoto.api.entity.dog.SingleDog

interface ServiceDogApi {
//    https://dog.ceo/api/breed

    @GET("breeds/image/random")
    abstract fun getRandomDog(): Single<SingleDog>

    @GET("breed/{breed}/images")
    abstract fun getRandomBreed(@Path("breed") breed: String): Single<BreedDog>

    @GET("breed/{breed}/{subBreed}/images")
    abstract fun getRandomSubBread(@Path("breed") breed: String, @Path("subBreed") subBreed: String): Single<BreedDog>

    @GET("breeds/list/all")
    fun getListAllDog(): Single<AllDogMessage>




//    @GET("users/{user}")
//    abstract fun getSearchInfo(@Path("user") userName: String): Single<SingleDog>
//
//    @GET
//    abstract fun getUserRepos(@Url url: String): Single<List<Repository>>
}