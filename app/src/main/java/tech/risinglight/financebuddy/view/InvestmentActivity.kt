package tech.risinglight.financebuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_investment.*
import tech.risinglight.financebuddy.R

class InvestmentActivity : AppCompatActivity() {
    var text = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investment)
        sip.setOnClickListener {
            text = "https://en.m.wikipedia.org/wiki/Systematic_Investment_Plan"
            var intent = Intent(applicationContext,
                WebActivity::class.java)
            intent.putExtra("details",text)
            startActivity(intent)
        }
        real_estate.setOnClickListener {
            text = "https://en.m.wikipedia.org/wiki/Real_estate_investing"
            var intent = Intent(applicationContext,
                WebActivity::class.java)
            intent.putExtra("details",text)
            startActivity(intent)
        }
        ppf.setOnClickListener {
            text = "https://en.m.wikipedia.org/wiki/Public_Provident_Fund_(India)"
            var intent = Intent(applicationContext,
                WebActivity::class.java)
            intent.putExtra("details",text)
            startActivity(intent)
        }

        gold.setOnClickListener{
            text = "https://en.m.wikipedia.org/wiki/Gold_as_an_investment"
            var intent = Intent(applicationContext,
                WebActivity::class.java)
            intent.putExtra("details",text)
            startActivity(intent)
        }
        fixed_deposit.setOnClickListener {
            text = "https://en.m.wikipedia.org/wiki/Fixed_deposit"
            var intent = Intent(applicationContext,
                WebActivity::class.java)
            intent.putExtra("details",text)
            startActivity(intent)
        }
    }
}
