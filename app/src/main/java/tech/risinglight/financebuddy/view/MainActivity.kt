package tech.risinglight.financebuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tech.risinglight.financebuddy.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseManagerCV.setOnClickListener {
            startActivity( Intent(applicationContext, ExpenseManagerActivity::class.java))
        }
        investmentCV.setOnClickListener {
            startActivity( Intent(applicationContext, InvestmentActivity::class.java))
        }
    }
}
