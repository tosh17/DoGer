package ru.thstdio.dogphoto.repo

import android.graphics.Bitmap
import java.io.File

interface IRepoImage {
    fun contain(url: String): Boolean
    fun loadFile(url: String): File?
    fun save(url: String, resource: Bitmap): File?
    fun containLike(url: String): Boolean
    fun saveLike(path: String): Boolean
    fun deleteLike(path: String): Boolean
    fun loadMuDog(): List<String>

}