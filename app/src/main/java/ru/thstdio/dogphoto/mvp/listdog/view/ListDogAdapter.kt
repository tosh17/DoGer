package ru.thstdio.dogphoto.mvp.listdog.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.listdog.presenter.ListDogAdapterPresenter
import ru.thstdio.dogphoto.mvp.listdog.view.subdog.ListDogHolderSub

class ListDogAdapter(val presenter: ListDogAdapterPresenter) : RecyclerView.Adapter<BaseListDogHolder>() {
    companion object {
        const val TypeSingleDog = 0
    }

    override fun getItemViewType(position: Int): Int {
        return presenter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BaseListDogHolder =
        when (type) {
            TypeSingleDog -> ListDogHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list_all_dog,
                    parent,
                    false
                )
            )
            else -> ListDogHolderSub(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_list_all_dog_with_sub,
                    parent,
                    false
                )
            )
        }


    override fun getItemCount(): Int = presenter.size()


    override fun onBindViewHolder(holder: BaseListDogHolder, position: Int) {
        when {
            holder is ListDogHolder -> presenter.onBind(holder, position)
            holder is ListDogHolderSub -> presenter.onBind(holder, position)
        }
    }
}