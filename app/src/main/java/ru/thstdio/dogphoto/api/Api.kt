package ru.thstdio.dogphoto.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi

class Api() {

            fun createDogApi(): ServiceDogApi {
               return  Retrofit.Builder()
                    .baseUrl("https://dog.ceo/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(
                        GsonBuilder().setFieldNamingPolicy(
                            FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                    .build()
                    .create(ServiceDogApi::class.java)
            }
    fun createWikiApi(): ServiceWikiApi {
        return  Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/w/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setFieldNamingPolicy(
                    FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
            .build()
            .create(ServiceWikiApi::class.java)
    }
}