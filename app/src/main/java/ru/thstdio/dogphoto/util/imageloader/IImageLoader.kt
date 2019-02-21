package ru.thstdio.dogphoto.util.imageloader

import android.widget.ImageView

interface IImageLoader {
    fun setError(idRes:Int)
    fun setPlaceholder(idRes:Int)
    fun load(url: String,view : ImageView)
    fun setOnListener(listen: ListenerColorPallet)
}