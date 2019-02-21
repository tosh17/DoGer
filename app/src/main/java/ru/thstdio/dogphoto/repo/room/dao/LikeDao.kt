package ru.thstdio.dogphoto.repo.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import ru.thstdio.dogphoto.repo.room.entity.RoomLike


@Dao
interface LikeDao {
    @Insert(onConflict = REPLACE)
    fun insert(like: RoomLike)

    @Query("SELECT * FROM image_like")
    fun getAll(): List<RoomLike>

    @Query("SELECT * FROM image_like WHERE sha1 = :sha1")
    fun contain(sha1: String): RoomLike

    @Delete
    fun delete(like: RoomLike)
}