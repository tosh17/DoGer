package ru.thstdio.dogphoto.di.module

import dagger.Module
import dagger.Provides
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog

@Module
class PresenterModel {
    @Provides
    fun createRandomDogModel(apiWiki: ServiceWikiApi, apiDog: ServiceDogApi): ModelRandomDog =
        ModelRandomDog(apiWiki, apiDog)

    @Provides
    fun createListDogModel(apiDog: ServiceDogApi): ListDogModel = ListDogModel(apiDog)
}