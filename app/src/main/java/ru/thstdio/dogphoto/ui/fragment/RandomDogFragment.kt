package ru.thstdio.dogphoto.ui.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.random_dog_fragment.view.*
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog
import ru.thstdio.dogphoto.ui.App
import ru.thstdio.dogphoto.ui.web.MyWebViewClient
import ru.thstdio.dogphoto.util.imageloader.IImageLoader
import ru.thstdio.dogphoto.util.imageloader.ListenerColorPallet
import javax.inject.Inject


class RandomDogFragment : MvpAppCompatFragment(), ViewRandomDog, AppBarLayout.OnOffsetChangedListener {

    @Inject
    lateinit var loader: IImageLoader

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
    loader.load(path, root.imageViewDog)
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
    fun providePresenter(): PresenterRandomDog {
        mPresenter = PresenterRandomDog()
        App.instance.daggerComponent.inject(mPresenter)
        return mPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(ru.thstdio.dogphoto.R.layout.random_dog_fragment, container, false)
        root.webContent.webViewClient = MyWebViewClient()
        root.appBar.addOnOffsetChangedListener(this)
        root.fab.setOnClickListener { mPresenter.clickFab() }
        App.instance.daggerComponent.inject(this)
        loader.setOnListener(object : ListenerColorPallet {
            override fun setColorFilter(colorBackGraund: Int, colorText: Int) {
                setAppbarBackground(colorBackGraund, colorText)
            }
        })

        return root
    }
}




