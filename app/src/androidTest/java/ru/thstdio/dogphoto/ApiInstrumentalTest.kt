package ru.thstdio.dogphoto

import android.support.test.runner.AndroidJUnit4
import io.reactivex.observers.TestObserver
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import ru.thstdio.dogphoto.di.DaggerTestIstrumentalComponent
import ru.thstdio.dogphoto.di.TestIstrumentalComponent
import ru.thstdio.dogphoto.di.module.ApiModule
import ru.thstdio.dogphoto.mvp.randomdog.model.ModelRandomDog
import java.io.IOException
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ApiInstrumentalTest {
    @Inject
    lateinit var model: ModelRandomDog

    companion object {
        private lateinit var mockWebServer: MockWebServer

        @JvmStatic
        @BeforeClass
        @Throws(IOException::class)
        fun setupClass() {
            mockWebServer = MockWebServer()
            mockWebServer.start()
        }

        @JvmStatic
        @AfterClass
        @Throws(IOException::class)
        fun tearDownClass() {
            mockWebServer.shutdown()
        }
    }

    @Before
    fun setup() {
        val component: TestIstrumentalComponent = DaggerTestIstrumentalComponent
            .builder()
            .apiModule(object : ApiModule() {
                override fun getBaseUrlDog(): String = mockWebServer.url("/").toString()
            })
            .build()

        component.inject(this)
    }

    @Test
    fun getDogRundomDog() {
        val testUrl = "testSingleDogUrl"
        val jsonStr=createSingleDogUrl(testUrl)
        mockWebServer.enqueue(jsonStr)
        val observer = TestObserver<String>()

        model.searchRandomUrl().subscribe(observer)
        observer.awaitTerminalEvent()

        observer.assertValueCount(1)
        assertEquals(observer.values()[0], testUrl)
    }

    private fun createSingleDogUrl(url: String): MockResponse {
        val body = "{\"status\": \"success\",\"message\": \"$url\"}"
        return MockResponse().setBody(body)
    }
}