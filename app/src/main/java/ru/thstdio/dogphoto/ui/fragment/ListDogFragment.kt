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
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogAdapter
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogViewState


class ListDogFragment : MvpAppCompatFragment(), ListDogViewState {
    companion object {
        fun getInstance(typeAdapter: String): ListDogFragment {
            val fragment = ListDogFragment()
            val args = Bundle()
            args.putString("typeAdapter", typeAdapter)
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

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "DogList")
    lateinit var mPresenter: ListDogPresenter

    @ProvidePresenter(type = PresenterType.GLOBAL, tag = "DogList")
    fun providePresenter(): ListDogPresenter {
        val presenter = ListDogPresenter()
        presenter.navigationTag = arguments?.getString("typeAdapter")!!
        return presenter
    }

    private lateinit var adapter: ListDogAdapter

    override fun initAdapte() {
        adapter = ListDogAdapter(mPresenter.listPresenter)
        root.recyclerView.adapter = adapter
    }

}