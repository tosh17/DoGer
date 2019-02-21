package ru.thstdio.dogphoto.di.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.repo.IRepoImage
import ru.thstdio.dogphoto.repo.room.AppDb
import ru.thstdio.dogphoto.repo.room.RepoImageRoom
import javax.inject.Named
import javax.inject.Singleton

@Module
class BdModule {
    @Singleton
    @Provides
    fun createBd(applicationContext: Context) : IRepoImage {
        var db = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java, "database"
        ).setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
            .build()
        return RepoImageRoom(db)
    }

}