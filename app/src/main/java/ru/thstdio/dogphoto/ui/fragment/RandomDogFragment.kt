package ru.thstdio.dogphoto.ui.fragment

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.transition.TransitionManager
import android.support.v7.graphics.Palette
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.random_dog_fragment.view.*
import ru.thstdio.dogphoto.App
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog
import ru.thstdio.dogphoto.ui.web.MyWebViewClient


class RandomDogFragment : MvpAppCompatFragment(), ViewRandomDog, AppBarLayout.OnOffsetChangedListener {
    override fun changeTitle(isTitlechange: Boolean) {
        TransitionManager.beginDelayedTransition(root.appBarCollaps as ViewGroup)
        val layoutParams = root.dogName.layoutParams as AppBarLayout.LayoutParams
        if (isTitlechange) layoutParams.gravity = Gravity.END or Gravity.BOTTOM
        else layoutParams.gravity = Gravity.START or Gravity.BOTTOM
        root.dogName.layoutParams = layoutParams
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll = appBarLayout.getTotalScrollRange()
        val percentage = Math.abs(offset).toFloat() / maxScroll.toFloat()
        mPresenter.appBarLayout(percentage)
    }

    override fun setWebView(url: String) {
        root.webContent.loadUrl(url)
    }

    override fun setDogName(name: String) {
        root.dogName.text = name
    }

    override fun setDogImage(path: String) {
        Picasso.get().load(path)
            .error(ru.thstdio.dogphoto.R.drawable.ic_pile_of_dung)
            .placeholder(ru.thstdio.dogphoto.R.drawable.ic_long_haired_dog_head)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    placeHolderDrawable?.let { root.imageViewDog.setImageDrawable(placeHolderDrawable) }
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    errorDrawable?.let { root.imageViewDog.setImageDrawable(errorDrawable) }
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    root.imageViewDog.setImageBitmap(bitmap)
                    bitmap?.let { bitmap ->
                        Palette.from(bitmap).generate { palette ->
                            palette?.dominantSwatch?.let {
                                setAppbarBackground(it.rgb, it.titleTextColor)

                            }
                        }
                    }
                }
            })
    }

    fun setAppbarBackground(color: Int, titleTextColor: Int) {
        root.apply {
            appBar.setBackgroundColor(color)
            appBarCollaps.setBackgroundColor(color)
            fab.backgroundTintList = ColorStateList.valueOf(color)
            fab.setColorFilter(titleTextColor)
            dogName.setTextColor(titleTextColor)
        }


    }

    lateinit var root: View

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "RandomWiki")
    lateinit var mPresenter: PresenterRandomDog

    @ProvidePresenter(type = PresenterType.GLOBAL, tag = "RandomWiki")
    fun providePresenter():PresenterRandomDog {
        mPresenter = PresenterRandomDog()
        App.instance.daggerComponent.inject(mPresenter)
        return mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(ru.thstdio.dogphoto.R.layout.random_dog_fragment, container, false)
        root.webContent.webViewClient = MyWebViewClient()
        root.appBar.addOnOffsetChangedListener(this)
        root.fab.setOnClickListener { mPresenter.clickFab() }
        return root
    }
}




