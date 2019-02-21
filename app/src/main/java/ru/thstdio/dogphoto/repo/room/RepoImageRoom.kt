package ru.thstdio.dogphoto.repo.room

import android.graphics.Bitmap
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.App
import ru.thstdio.dogphoto.repo.IRepoImage
import ru.thstdio.dogphoto.repo.room.entity.RoomImage
import ru.thstdio.dogphoto.repo.room.entity.RoomLike
import ru.thstdio.dogphoto.repo.sha1
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class RepoImageRoom(val db: AppDb) : IRepoImage {
    override fun loadMuDog(): List<String> {
       return db.likeDao().getAll().map{like->like.path}.toList()
    }

    override fun saveLike(path: String): Boolean {
        db.likeDao().insert(pathToLike(path))
        return true
    }

    override fun deleteLike(path: String): Boolean {
        db.likeDao().delete(pathToLike(path))
        return true
    }

    override fun containLike(url: String): Boolean {
        return db.likeDao().contain(url.sha1) != null
    }

    private val dir: File = File(App.instance.getFilesDir(), "image")

    init {
        if (!dir.exists()) dir.mkdirs()
    }

    override fun save(url: String, resource: Bitmap): File? {
        val sha1 = url.sha1
        val fileName = String.format("%s.png", sha1)
        val file = File(dir, fileName)
        if (file.exists()) file.delete()
        try {
            val out = FileOutputStream(file)
            resource.compress(Bitmap.CompressFormat.PNG, 80, out)
            val image = RoomImage()
            image.sha1 = sha1
            image.path = fileName
            db.imageDao().insert(image)
            out.close()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return file
    }

    override fun contain(url: String): Boolean = db.imageDao().contain(url) != null


    override fun loadFile(url: String): File {
        val sha1 = url.sha1
        val path = db.imageDao().contain(sha1).path
        return File(dir, path)
    }

    fun pathToLike(path: String): RoomLike {
        val like = RoomLike()
        like.sha1 = path.sha1
        like.path=path
        return like
    }
}


