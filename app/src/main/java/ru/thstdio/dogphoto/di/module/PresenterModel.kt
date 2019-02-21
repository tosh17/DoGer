package ru.thstdio.dogphoto.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.api.source.ServiceDogApi
import ru.thstdio.dogphoto.api.source.ServiceWikiApi
import ru.thstdio.dogphoto.mvp.doggallery.model.GalleryModel
import ru.thstdio.dogphoto.mvp.doggallery.presenter.GalleryDogAdapterPresenter
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog
import ru.thstdio.dogphoto.repo.IRepoImage
import javax.inject.Named

@Module
class PresenterModel {
    @Provides
    fun createRandomDogModel(apiWiki: ServiceWikiApi, apiDog: ServiceDogApi): ModelRandomDog =
        ModelRandomDog(apiWiki, apiDog)

    @Provides
    fun createListDogModel(apiDog: ServiceDogApi): ListDogModel = ListDogModel(apiDog)

    @Provides
    fun createGalleryDogModel(apiDog: ServiceDogApi,imageRepo: IRepoImage): GalleryModel = GalleryModel(apiDog,imageRepo)

    @Provides
    fun galleryDogListPresenter( model: GalleryModel,@Named("mainTread") mainThread: Scheduler) : GalleryDogAdapterPresenter = GalleryDogAdapterPresenter(model,mainThread)
}