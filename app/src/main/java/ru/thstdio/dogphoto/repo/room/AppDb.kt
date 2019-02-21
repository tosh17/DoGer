package ru.thstdio.dogphoto.repo.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.thstdio.dogphoto.repo.room.dao.ImageDao
import ru.thstdio.dogphoto.repo.room.dao.LikeDao
import ru.thstdio.dogphoto.repo.room.entity.RoomImage
import ru.thstdio.dogphoto.repo.room.entity.RoomLike

@Database(entities = [RoomImage::class,RoomLike::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun likeDao():LikeDao
}