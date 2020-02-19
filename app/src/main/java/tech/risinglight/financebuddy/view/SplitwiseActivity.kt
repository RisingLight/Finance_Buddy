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
//        val transactionList: ArrayList<Transaction> = ArrayList()
        var swModel1 = SplitWiseModel("abhijit@gmail.com","Abhijit", "Rs 200")
        var swModel2 = SplitWiseModel("abhishek@gmail.com","Abhishek", "Rs 240")
        var swModel3 = SplitWiseModel("abhinay@gmail.com","Abhinay", "Rs 100")
        listSW.add(swModel1)
        listSW.add(swModel2)
        listSW.add(swModel3)
        val adapter = SplitwiseRecyclerAdapter(splitwiseModelArrayList = listSW)
        splitWiseRV.layoutManager = linearLayoutManager
        splitWiseRV.adapter = adapter
    }
}
