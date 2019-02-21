package ru.thstdio.dogphoto.repo.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "image_cache")
class RoomImage {
    @NotNull
    @PrimaryKey
    lateinit var sha1: String
    var path: String? = null
}