package tech.risinglight.financebuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_news_ddetails.*
import tech.risinglight.financebuddy.R

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_ddetails)
        val intent= getIntent()
        titleTV.text = intent.getStringExtra("title")
        descTV.text = intent.getStringExtra("desc")

    }
}
