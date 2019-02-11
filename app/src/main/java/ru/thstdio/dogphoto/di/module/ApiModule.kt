package ru.thstdio.dogphoto.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi
import javax.inject.Named

@Module
class ApiModule {
    @Named("WikiUrl")
    @Provides
    fun getBaseUrlWiki(): String = "https://en.wikipedia.org/w/"

    @Named("DogUrl")
    @Provides
    fun getBaseUrlDog(): String = "https://dog.ceo/api/"

    @Provides
    fun createApiWiki(@Named("WikiUrl") baseUrl: String, gson: GsonConverterFactory): ServiceWikiApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gson)
            .build()
            .create(ServiceWikiApi::class.java)
    }

    @Provides
    fun createApiDog(@Named("DogUrl") baseUrl: String, gson: GsonConverterFactory): ServiceDogApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gson)
            .build()
            .create(ServiceDogApi::class.java)
    }

    @Provides
    fun createGson(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder().setFieldNamingPolicy(
            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES
        ).create()
    )
}