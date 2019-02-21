package ru.thstdio.dogphoto.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.thstdio.dogphoto.App
import ru.thstdio.dogphoto.R
import ru.thstdio.dogphoto.mvp.main.presenter.MainPresenter
import ru.thstdio.dogphoto.mvp.main.view.MainView
import ru.thstdio.dogphoto.navigation.LocalCiceroneHolder
import ru.thstdio.dogphoto.navigation.screen.Screens
import ru.thstdio.dogphoto.ui.fragment.CurrentDogGallery.Companion.LIKE_DOG
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    var navigatorList: MutableMap<String, Navigator> = HashMap()

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "MainView")
    lateinit var mPresenter: MainPresenter

//    @ProvidePresenter(type = PresenterType.GLOBAL, tag = "MainView")
//    fun providePresenter(): MainPresenter {
//        mPresenter = MainPresenter()
//        App.instance.daggerComponent.inject(mPresenter)
//        return mPresenter
//    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mPresenter.currentTab("ListDog")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                mPresenter.currentTab("RandomDog")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mPresenter.currentTab("LikeDog")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.daggerComponent.inject(this)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mPresenter.currentTab("ListDog")
    }


    override fun selectTab(tab: String) {
        if (navigatorList[tab] == null) {
            val navigator = object : SupportAppNavigator(this, R.id.fragment_conteiner) {
                override fun applyCommands(commands: Array<Command>) {
                    super.applyCommands(commands)
         //           supportFragmentManager.executePendingTransactions()
                }
            }

            navigatorList[tab] = navigator
            ciceroneHolder.getCicerone(tab)?.navigatorHolder?.setNavigator(navigator)

        }
            ciceroneHolder.getCicerone(tab)?.router?.run {
            when (tab) {
                "ListDog" -> newRootScreen(Screens.ListDogScreen.tag(tab))
                "RandomDog" -> newRootScreen(Screens.RandomDogScreen)
                "LikeDog" -> newRootScreen(Screens.GalleryDogScreen.initValueMyDog())
            }

        }
          }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.removeNavigator()
    }
}
