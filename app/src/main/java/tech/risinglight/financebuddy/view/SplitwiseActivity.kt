package tech.risinglight.financebuddy.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_splitwise.*
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.adapter.SplitwiseRecyclerAdapter
import tech.risinglight.financebuddy.model.SplitWiseModel
import tech.risinglight.financebuddy.model.Transaction

class SplitwiseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splitwise)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val listSW: ArrayList<SplitWiseModel> = ArrayList()
        val transactionList: ArrayList<Transaction> = ArrayList()
        var swModel1 = SplitWiseModel("Abhijit", "Rs 200", transactionList)
        var swModel2 = SplitWiseModel("Abhishek", "Rs 240", transactionList)
        var swModel3 = SplitWiseModel("Abhinay", "Rs 100", transactionList)
        listSW.add(swModel1)
        listSW.add(swModel2)
        listSW.add(swModel3)
        val adapter = SplitwiseRecyclerAdapter(splitwiseModelArrayList = listSW)
        splitWiseRV.layoutManager = linearLayoutManager
        splitWiseRV.adapter = adapter
    }
}
