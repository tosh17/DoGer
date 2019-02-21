package ru.thstdio.dogphoto.mvp.listdog.view

import android.view.View
import kotlinx.android.synthetic.main.item_list_all_dog.view.*
import ru.thstdio.dogphoto.mvp.listdog.presenter.OnClickListener
import ru.thstdio.dogphoto.ui.App
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import javax.inject.Inject

open class ListDogHolder(itemView: View) : BaseListDogHolder(itemView), OnBindListDogHolder {

    @Inject
    lateinit var loader: IImageLoader

    init {
        App.instance.daggerComponent.inject(this)
    }

    override fun setOnClick(onClick: OnClickListener) {
        itemView.imageView.setOnClickListener{onClick.click()}
    }

    override fun setImage(url: String) {
        loader.load(url, itemView.imageView)
    }

    override fun setTitle(title: String) {
        itemView.textTitle.text = title
    }


}