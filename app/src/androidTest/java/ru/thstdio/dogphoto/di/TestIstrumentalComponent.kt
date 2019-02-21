package ru.thstdio.dogphoto.di

import dagger.Component
import ru.thstdio.dogphoto.ApiInstrumentalTest
import ru.thstdio.dogphoto.di.module.ApiModule
import ru.thstdio.dogphoto.di.module.PresenterModel

@Component(modules = [ApiModule::class, PresenterModel::class])
interface TestIstrumentalComponent {
    fun inject(apiTest: ApiInstrumentalTest)
}
