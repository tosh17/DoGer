package ru.thstdio.dogphoto

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented random_dog_fragment, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under random_dog_fragment.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("ru.thstdio.dogphoto", appContext.packageName)
    }
}
