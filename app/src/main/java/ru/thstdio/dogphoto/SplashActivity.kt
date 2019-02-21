package ru.thstdio.dogphoto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ru.thstdio.dogphoto.ui.MainActivity
import android.content.Intent



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
