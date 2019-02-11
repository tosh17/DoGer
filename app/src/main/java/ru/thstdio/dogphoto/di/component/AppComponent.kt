package ru.thstdio.dogphoto.di.component

import dagger.Component
import ru.thstdio.dogphoto.di.module.ApiModule
import ru.thstdio.dogphoto.di.module.PresenterModel
import ru.thstdio.dogphoto.di.module.SchedulerModule
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogPresenter
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog

@Component(modules = [ApiModule::class,PresenterModel::class,SchedulerModule::class])
interface AppComponent {
    fun inject(presenter: PresenterRandomDog)
    fun inject(presenter: ListDogPresenter)

}