package ru.thstdio.dogphoto.util.imageloader.picaso

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.thstdio.dogphoto.repo.IRepoImage
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import ru.thstdio.dogphoto.util.imageloader.ListenerColorPallet

class PicasoLoader(val imageRepo: IRepoImage, val mainThread: Scheduler) :
    IImageLoader {
    var listener: ListenerColorPallet? = null

    var idResError = ru.thstdio.dogphoto.R.drawable.ic_pile_of_dung
    var idResPlaceholder = ru.thstdio.dogphoto.R.drawable.ic_long_haired_dog_head

    override fun setOnListener(onListen: ListenerColorPallet) {
        listener = onListen
    }

     override fun setError(idRes: Int) {
        idResError = idRes
    }

    override fun setPlaceholder(idRes: Int) {
        idResPlaceholder = idRes
    }




    @SuppressLint("CheckResult")
    override fun load(url: String, view: ImageView) {
          Single.fromCallable { imageRepo.contain(url) }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread)
            .subscribe { result ->
                if (result) loadFromFile(url, view)
                else loadFromUrl(url, view)
            }
    }

    private fun loadFromUrl(url: String, view: ImageView) {
        Picasso.get().load(url)
            .error(idResError)
            .placeholder(idResPlaceholder)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    placeHolderDrawable?.let {
                        view.setImageDrawable(placeHolderDrawable)
                    }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    errorDrawable?.let { view.setImageDrawable(errorDrawable) }
                }

                @SuppressLint("CheckResult")
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom?) {
                    view.setImageBitmap(bitmap)
                    Single.just(true).observeOn(Schedulers.io())
                        .map{instance->imageRepo.save(url, bitmap)
                            instance}
                        .subscribe{instance->{}}

                    listener?.let { listener ->
                        Palette.from(bitmap).generate { palette ->
                            palette?.dominantSwatch?.let {
                                listener.setColorFilter(it.rgb, it.titleTextColor)
                            }
                        }
                    }
                }
            })
    }

    private fun loadFromFile(url: String, view: ImageView) {
        val file = imageRepo.loadFile(url)
        file?.let {
            Picasso.get().load(file)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        listener?.let { listener ->
                            bitmap?.let { bitmap ->
                                Palette.from(bitmap).generate { palette ->
                                    palette?.dominantSwatch?.let {
                                        listener.setColorFilter(it.rgb, it.titleTextColor)
                                    }
                                }
                            }
                        }
                        view.setImageBitmap(bitmap)
                    }
                })
        }


    }
}