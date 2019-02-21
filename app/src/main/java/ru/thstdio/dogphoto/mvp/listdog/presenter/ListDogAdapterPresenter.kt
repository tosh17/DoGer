package ru.thstdio.dogphoto.mvp.listdog.presenter

import android.annotation.SuppressLint
import io.reactivex.Scheduler
import ru.thstdio.dogphoto.entity.Dog
import ru.thstdio.dogphoto.mvp.listdog.model.ListDogModel
import ru.thstdio.dogphoto.mvp.listdog.view.OnBindListDogHolder
import ru.thstdio.dogphoto.mvp.listdog.view.subdog.OnBindListSubDogHolder
import ru.thstdio.dogphoto.navigation.LocalCiceroneHolder
import ru.thstdio.dogphoto.navigation.screen.Screens

class ListDogAdapterPresenter {


    lateinit var ciceroneHolder: LocalCiceroneHolder

    lateinit var dogs: List<Dog>
    lateinit var subDogs: MutableList<List<Dog>>

    lateinit var model: ListDogModel
    lateinit var mainThread: Scheduler


    fun size(): Int = dogs.size

    fun subSize(position: Int): Int = subDogs[position].size

    fun onBind(holder: OnBindListDogHolder, position: Int) {
        dogs[position].run {
            holder.setTitle(this.breed)
            if (this.url == null) loadUrl(holder, this)
            else holder.setImage(this.url!!)
        }
    }

     fun onClick(position: Int) {
        val screen = Screens.GalleryDogScreen
        screen.initValue(dogs[position].breed,"")
        ciceroneHolder.getCicerone("ListDog")?.router?.navigateTo(screen)
    }

    @SuppressLint("CheckResult")
    private fun loadUrl(holder: OnBindListDogHolder, dog: Dog) {
        if (dog.subBreed == null) {
            model.findImageDog(dog.breed)
                .observeOn(mainThread)
                .subscribe { url ->
                    dog.url = url
                    holder.setImage(url)
                }
        } else {
            model.findImageDog(dog.breed, dog.subBreed!!)
                .observeOn(mainThread)
                .subscribe { url ->
                    dog.url = url
                    holder.setImage(url)
                }
        }
    }

    fun onBind(holder: OnBindListSubDogHolder, position: Int) {
        holder.setTitle(dogs[position].breed)
        holder.setAdapter(this, position)

    }

    fun onSubBind(holder: OnBindListDogHolder, position: Int, subPosition: Int) {
        subDogs[position][subPosition].subBreed?.let {
            holder.setTitle(it)
            if (subDogs[position][subPosition].url == null) loadUrl(holder, subDogs[position][subPosition])
            else holder.setImage(subDogs[position][subPosition].url!!)
        }

    }
    fun onClick(position: Int, subPosition: Int) {
        val screen = Screens.GalleryDogScreen
        screen.initValue(subDogs[position][subPosition].breed,subDogs[position][subPosition].subBreed!!)
        ciceroneHolder.getCicerone("ListDog")?.router?.navigateTo(screen)
    }

    fun getItemViewType(position: Int): Int =
        if (subDogs[position].size > 0) 1
        else 0

    fun setMap(dogMap: HashMap<String, List<String>>) {
        dogs = dogMap.keys.toList().map { name -> Dog(breed = name, subBreed = null, url = null) }
        subDogs = ArrayList()
        dogs.forEach { dog ->
            dogMap[dog.breed]?.let {
                subDogs.add(it.map { subName ->
                    Dog(
                        breed = dog.breed,
                        subBreed = subName,
                        url = null
                    )
                })
            }
        }
    }




}