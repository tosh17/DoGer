package ru.thstdio.dogphoto.util.imageloader.glide

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.random_dog_fragment.view.*
import ru.thstdio.dogphoto.repo.IRepoImage
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import ru.thstdio.dogphoto.util.imageloader.ListenerColorPallet
import timber.log.Timber

class GlideLoader(val imageRepo: IRepoImage, val mainThread: Scheduler) : IImageLoader {

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
        view.setImageResource(idResPlaceholder)
        Single.fromCallable { imageRepo.contain(url) }
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread)
            .subscribe { result ->
                if (result) loadFromFile(url, view)
                else loadFromUrl(url, view)
            }
    }
    @SuppressLint("CheckResult")
    private fun loadFromUrl(url: String, view: ImageView) {
        GlideApp.with(view.context).asBitmap().load(url).listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Bitmap>,
                isFirstResource: Boolean
            ): Boolean {
                Timber.e(e, "Image load failed")
                view.setImageResource(idResError)
                return false
            }

            override fun onResourceReady(
                resource: Bitmap,
                model: Any,
                target: Target<Bitmap>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                Single.just(true).observeOn(Schedulers.io())
                    .map{instance->imageRepo.save(url, resource)
                        instance}
                    .subscribe{instance->{}}
                listener?.let { listener ->
                    Palette.from(resource).generate { palette ->
                        palette?.dominantSwatch?.let {
                            listener.setColorFilter(it.rgb, it.titleTextColor)
                        }
                    }
                }
                return false
            }
        }).into(view)


    }

    private fun loadFromFile(url: String, view: ImageView) {

    }


}