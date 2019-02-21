package ru.thstdio.dogphoto.mvp.doggallery.view

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.gallery_item.view.*
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.ui.App
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import javax.inject.Inject

class GalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView), IGalleryHolder {
    @Inject
    lateinit var loader: IImageLoader

    init {
        App.instance.daggerComponent.inject(this)

    }

    override fun onBind(url: String, isLike: Boolean) {
        loader.load(url, itemView.imageDog)
    }


    override  fun clickLike(isLike: Boolean){
        itemView.imageHeart.setColorFilter(getColor(isLike))
    }

    fun getColor(isLike: Boolean)=
        if(isLike) itemView.context.resources.getColor(R.color.heartEnable)
        else itemView.context.resources.getColor(R.color.heartDisable)

}