package ru.thstdio.dogphoto.mvp.listdog.view.subdog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogAdapterPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.ListDogHolder

class ListDogSubAdapter(val presenter: ListDogAdapterPresenter, val position: Int) :
    RecyclerView.Adapter<ListDogHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListDogHolder {
        return ListDogHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_item_list_all_dog,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        presenter.subSize(position)


    override fun onBindViewHolder(holder: ListDogHolder, subPosition: Int) {
        presenter.onSubBind(holder, position, subPosition)
    }
}