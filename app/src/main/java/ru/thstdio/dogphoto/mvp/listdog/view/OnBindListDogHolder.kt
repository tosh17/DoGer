package ru.thstdio.dogphoto.mvp.listdog.view

import ru.thstdio.dogphoto.mvp.listdog.presenter.OnClickListener

interface OnBindListDogHolder {
    fun setTitle(title : String)
    fun setImage(url: String)
    fun setOnClick(onClick: OnClickListener)
   }