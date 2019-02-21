package ru.thstdio.dogphoto.mvp.doggallery.view

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.gallery_item.view.*
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.doggallery.presenter.GalleryDogAdapterPresenter

class GalleryDogAdapter(val presenter: GalleryDogAdapterPresenter) : RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GalleryHolder {
        return GalleryHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.gallery_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = presenter.size()


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        RxView.clicks(holder.itemView.imageHeart).map{
                obj -> holder }.subscribe{presenter.clickLike(holder,position)}
        presenter.onBind(holder, position)
    }
}