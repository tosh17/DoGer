package ru.thstdio.dogphoto.mvp.listdog.view.subdog

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.item_list_all_dog_with_sub.view.*


import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogAdapterPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.BaseListDogHolder

class ListDogHolderSub(itemView: View) : BaseListDogHolder(itemView), OnBindListSubDogHolder {
    override fun setTitle(title: String) {
        itemView.textTitle.text = title
    }

    override fun setAdapter(mPresenter: ListDogAdapterPresenter, position: Int) {
        itemView.recyclerViewItem.run {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ListDogSubAdapter(mPresenter, position)
        }
    }

}