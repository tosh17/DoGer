package ru.thstdio.dogphoto.di

import dagger.Component
import ru.thstdio.dogphoto.di.module.TestModuleModel
import ru.thstdio.dogphoto.di.module.TestSchedulerModule
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog

@Component(modules = [TestModuleModel::class, TestSchedulerModule::class])
interface TestComponent {
    fun inject(testPresenter: PresenterRandomDog)
}