package ru.thstdio.dogphoto

import android.app.Application
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        cicerone = Cicerone.create()
        instance = this
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }
}