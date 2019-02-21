package ru.thstdio.dogphoto.repo.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "image_like")
class RoomLike{
    @NotNull
    @PrimaryKey
    lateinit var sha1: String
    lateinit var path: String

}