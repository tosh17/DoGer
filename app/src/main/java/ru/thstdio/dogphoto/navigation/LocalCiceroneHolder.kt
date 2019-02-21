package ru.thstdio.dogphoto.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.HashMap

class LocalCiceroneHolder {
    private val containers: HashMap<String, Cicerone<Router>>

    init {
        containers = HashMap()
    }

    fun getCicerone(containerTag: String): Cicerone<Router>? {
        if (!containers.containsKey(containerTag)) {
            containers[containerTag] = Cicerone.create()
        }
        return containers[containerTag]
    }
}