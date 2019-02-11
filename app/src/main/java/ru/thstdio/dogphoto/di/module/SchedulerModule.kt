package ru.thstdio.dogphoto.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

@Module
class SchedulerModule {
    @Named("mainTread")
    @Provides
    fun getMainTread():Scheduler = AndroidSchedulers.mainThread()
}