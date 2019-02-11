package ru.thstdio.dogphoto.mvp.listdog.presenter

import ru.thstdio.dogphoto.mvp.listdog.view.OnBindListDogHolder
import ru.thstdio.dogphoto.mvp.listdog.view.subdog.OnBindListSubDogHolder

class ListDogAdapterPresenter {

    lateinit var dogs: List<String>
    lateinit var subDogs: MutableList<List<String>>

    fun size(): Int = dogs.size

    fun subSize(position: Int): Int = subDogs[position].size

    fun onBind(holder: OnBindListDogHolder, position: Int) {
        holder.setTitle(dogs[position])
    }

    fun onBind(holder: OnBindListSubDogHolder, position: Int) {
        holder.setTitle(dogs[position])
        holder.setAdapter(this, position)
    }

    fun onSubBind(holder: OnBindListDogHolder, position: Int, subPosition: Int) {
        holder.setTitle(subDogs[position][subPosition])
    }

    fun getItemViewType(position: Int): Int =
        if (subDogs[position].size > 0) 1
        else 0

    fun setMap(dogMap: HashMap<String, List<String>>) {
        dogs = dogMap.keys.toList()
        subDogs = ArrayList()
        dogs.forEach { dogMame -> dogMap[dogMame]?.let { subDogs.add(it) } }
    }


}