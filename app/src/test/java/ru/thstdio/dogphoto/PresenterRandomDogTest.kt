package ru.thstdio.dogphoto

import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.thstdio.dogphoto.di.DaggerTestComponent
import ru.thstdio.dogphoto.di.TestComponent
import ru.thstdio.dogphoto.di.module.TestModuleModel
import ru.thstdio.dogphoto.di.module.TestSchedulerModule
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.presenter.PresenterRandomDog
import ru.thstdio.dogphoto.mvp.randomdog.view.ViewRandomDog
import timber.log.Timber
import java.util.concurrent.TimeUnit


class PresenterRandomDogTest {

    lateinit var presenter: PresenterRandomDog
    lateinit var testScheduler: TestScheduler

    @Mock
    lateinit var viewState: ViewRandomDog

    companion object {
        @BeforeClass
        fun setupClass() {
            Timber.plant(Timber.DebugTree())
            Timber.d("setup class")
        }

        @AfterClass
        fun tearDownClass() {
            Timber.d("tear down class")
        }
    }


    @Before
    fun setup() {
        Timber.d("setup")
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        presenter = Mockito.spy(PresenterRandomDog())

    }


    @Test
    fun findNewDogTest() {
        val dogName = "superDog"
        val url = "$dogName/123.jpeg"
        val wikiSearch = " dog $dogName"
        val wikiUrl = "wiki $dogName page"

        val component: TestComponent = DaggerTestComponent.builder()
            .testSchedulerModule(object : TestSchedulerModule() {
                override fun getMainTread(): Scheduler = testScheduler
            })
            .testModuleModel(object : TestModuleModel() {
                override fun createRandomDogModel(): ModelRandomDog {
                    val model = super.createRandomDogModel()
                    Mockito.`when`(model.searchRandomUrl()).thenReturn(Single.just(url))
                    Mockito.`when`(model.searchWikiLink(wikiSearch)).thenReturn(Maybe.just(wikiUrl))
                    return model
                }
            })
            .build()

        component.inject(presenter)
        presenter.attachView(viewState)
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)
        Mockito.verify(viewState).setDogImage(url)
        Mockito.verify(viewState).setDogName(dogName)
        Mockito.verify(viewState).setWebView(wikiUrl)
    }


}