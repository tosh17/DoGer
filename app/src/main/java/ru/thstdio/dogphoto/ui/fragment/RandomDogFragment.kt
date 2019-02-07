package ru.thstdio.dogphoto.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.random_dog_fragment.view.*
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog
import ru.thstdio.dogphoto.ui.web.MyWebViewClient


class RandomDogFragment : MvpAppCompatFragment(), ViewRandomDog {
    override fun setWebView(url: String) {
        root.webContent.loadUrl(url)
    }

    override fun setDogName(name: String) {
        root.dogName.text=name
    }

    override fun setDogImage(path: String) {
        root.imageViewDog.load(path)
    }

    lateinit var root: View

    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var mPresenter: PresenterRandomDog

    @ProvidePresenter(type = PresenterType.GLOBAL)
    fun provideDialogPresenter() = PresenterRandomDog()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(ru.thstdio.dogphoto.R.layout.random_dog_fragment, container, false)
        root.webContent.webViewClient= MyWebViewClient()
        mPresenter.init()
        return root
    }
}

private fun ImageView.load(path: String) {
    Picasso.get().load(path)
        .error(R.drawable.ic_home_black_24dp)
        .into(this)

}
