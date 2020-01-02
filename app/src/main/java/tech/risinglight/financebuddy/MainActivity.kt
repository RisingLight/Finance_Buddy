package tech.risinglight.financebuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val type = Typeface.createFromAsset(assets, "lobster.ttf")
        textView.typeface = type

        expenseManagerCV.setOnClickListener {
            startActivity( Intent(applicationContext, ExpenseManagerActivity::class.java))
        }
        card1.setOnClickListener {
            startActivity( Intent(applicationContext, InvestmentActivity::class.java))
        }
    }
}
