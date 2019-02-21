package ru.thstdio.dogphoto.ui.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import kotlinx.android.synthetic.main.list_fragment.view.*
import ru.thstdio.dogphoto.mvp.doggallery.presenter.GalleryDogPresenter
import ru.thstdio.dogphoto.mvp.doggallery.view.DogGalleryViewState
import ru.thstdio.dogphoto.mvp.doggallery.view.GalleryDogAdapter
import ru.thstdio.dogphoto.ui.App

class CurrentDogGallery : MvpAppCompatFragment(), DogGalleryViewState {
    companion object {
        val BREED_VALUE = "breed"
        val SUB_BREED_VALUE = "sub_breed"
        val LIKE_DOG = "my_dog"

        fun getInstance(breed: String, subBreed: String): CurrentDogGallery {
            val fragment = CurrentDogGallery()
            val args = Bundle()
            args.putString(BREED_VALUE, breed)
            args.putString(SUB_BREED_VALUE, subBreed)
            fragment.setArguments(args)
            return fragment
        }
    }

    @InjectPresenter(type = PresenterType.LOCAL, tag = "DogGallery")
    lateinit var mPresenter: GalleryDogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.daggerComponent.inject(mPresenter)
    }

    lateinit var root: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(ru.thstdio.dogphoto.R.layout.list_fragment, container, false)
        val coll = when (getResources().getConfiguration().orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 2
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 3
        }
        root.recyclerView.layoutManager = GridLayoutManager(context, coll)
        val breed = arguments?.getString(BREED_VALUE)
        val subBreed = arguments?.getString(SUB_BREED_VALUE)
        if (breed == LIKE_DOG) mPresenter.loadMyDog()
        else mPresenter.loadDogs(breed!!, subBreed!!)
        return root
    }

    private lateinit var adapter: GalleryDogAdapter

   override fun initAdapte() {
        adapter = GalleryDogAdapter(mPresenter.listPresenter)
        root.recyclerView.adapter = adapter
    }
}