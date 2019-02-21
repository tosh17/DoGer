package ru.thstdio.dogphoto.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.thstdio.dogphoto.di.module.*
import ru.thstdio.dogphoto.mvp.doggallery.presenter.GalleryDogPresenter
import ru.thstdio.dogphoto.mvp.doggallery.view.GalleryHolder
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.BaseListDogHolder
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogHolder
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog
import ru.thstdio.dogphoto.ui.MainActivity
import ru.thstdio.dogphoto.ui.fragment.RandomDogFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class, PresenterModel::class, SchedulerModule::class,
        BdModule::class, ImageLoaderModule::class, NavigationModule::class]
)
interface AppComponent {
    fun inject(presenter: PresenterRandomDog)
    fun inject(presenter: ListDogPresenter)
    fun inject(presenter: GalleryDogPresenter)
    fun inject(randomDogFragment: RandomDogFragment)
    fun inject(randomDogFragment: ListDogHolder)
    fun inject(mainActivity: MainActivity)
    fun inject(galleryHolder: GalleryHolder)


    @Component.Builder
    interface MyBuilder {
        fun build(): AppComponent

        @BindsInstance
        fun setContext(applicationContext: Context): MyBuilder

    }
}