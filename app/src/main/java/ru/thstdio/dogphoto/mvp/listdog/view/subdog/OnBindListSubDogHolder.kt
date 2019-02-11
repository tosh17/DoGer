package ru.thstdio.dogphoto.mvp.listdog.view.subdog

import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogAdapterPresenter

interface OnBindListSubDogHolder {
    fun setTitle(title: String)
    fun setAdapter(mPresenter: ListDogAdapterPresenter, position:Int)
}