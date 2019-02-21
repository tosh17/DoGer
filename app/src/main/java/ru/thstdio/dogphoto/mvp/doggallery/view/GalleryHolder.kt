package ru.thstdio.dogphoto.mvp.doggallery.view

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.gallery_item.view.*
import ru.thstdio.dogphoto.App
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
      //  itemView.imageHeart.setOnClickListener {clickLike(true)  }
    }


    override  fun clickLike(isLike: Boolean){

        itemView.imageHeart.setColorFilter(if(isLike)Color.RED else Color.WHITE)
    }


}