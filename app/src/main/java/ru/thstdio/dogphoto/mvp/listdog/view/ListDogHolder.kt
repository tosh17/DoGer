package ru.thstdio.dogphoto.mvp.listdog.view

import android.view.View
import kotlinx.android.synthetic.main.item_list_all_dog.view.*

open class ListDogHolder(itemView: View) : BaseListDogHolder(itemView), OnBindListDogHolder {

    override fun setTitle(title: String) {
        itemView.textTitle.text = title
    }

}