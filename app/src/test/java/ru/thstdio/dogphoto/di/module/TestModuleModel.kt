package ru.thstdio.dogphoto.di.module

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog

@Module
open class TestModuleModel {
    @Provides
    open fun createRandomDogModel(): ModelRandomDog =
       Mockito.mock(ModelRandomDog::class.java)

}