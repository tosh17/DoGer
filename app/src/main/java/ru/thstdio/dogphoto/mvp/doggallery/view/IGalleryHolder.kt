package ru.thstdio.dogphoto.mvp.doggallery.view

interface IGalleryHolder {
    fun onBind(url:String,isLike:Boolean)
    fun clickLike(isLike: Boolean)
}