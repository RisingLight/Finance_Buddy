package tech.risinglight.financebuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val type = Typeface.createFromAsset(assets, "lobster.ttf")
        textView.typeface = type
    }
}
