package ru.thstdio.dogphoto.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.list_fragment.view.*
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogAdapter
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogViewState


class ListDogFragment : MvpAppCompatFragment(), ListDogViewState {
    companion object {
        fun getInstance(typeAdapter: Int): ListDogFragment {
            val fragment = ListDogFragment()
            val args = Bundle()
            args.putInt("typeAdapter", typeAdapter)
            fragment.setArguments(args)
            return fragment
        }
    }

    lateinit var root: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(ru.thstdio.dogphoto.R.layout.list_fragment, container, false)
        root.recyclerView.layoutManager = LinearLayoutManager(context)
        root.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return root
    }

   @InjectPresenter(type = PresenterType.GLOBAL,tag = "DogList")
    lateinit var mPresenter: ListDogPresenter

//    @ProvidePresenter(type = PresenterType.GLOBAL)
//    fun providePresenter() = ListDogPresenter()

    private lateinit var adapter: ListDogAdapter

    override fun initAdapte() {
        adapter = ListDogAdapter(mPresenter.listPresenter)
        root.recyclerView.adapter = adapter
    }

}