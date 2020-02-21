package tech.risinglight.financebuddy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_expense_manager.*
import tech.risinglight.financebuddy.R


class ExpenseManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_manager)
            rv.loadUrl("https://www.moneycontrol.com/stocks/marketstats/index.php")
        }


}
