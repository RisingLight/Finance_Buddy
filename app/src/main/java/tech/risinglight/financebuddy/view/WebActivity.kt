package tech.risinglight.financebuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*
import tech.risinglight.financebuddy.R

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        intent = intent
        var link = intent.getStringExtra("details")
        webview.loadUrl(link)
    }
}
