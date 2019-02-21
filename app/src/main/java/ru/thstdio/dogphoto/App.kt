package ru.thstdio.dogphoto

import android.app.Application
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.thstdio.dogphoto.di.component.AppComponent
import ru.thstdio.dogphoto.di.component.DaggerAppComponent


class App : Application() {
    companion object {
        lateinit var instance: App private set

    }

    private lateinit var cicerone: Cicerone<Router>
    lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        cicerone = Cicerone.create()
        daggerComponent = DaggerAppComponent.builder().setContext(applicationContext).build()
            //builder().build()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }
}