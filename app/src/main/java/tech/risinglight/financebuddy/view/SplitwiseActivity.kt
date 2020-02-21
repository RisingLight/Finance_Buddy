package tech.risinglight.financebuddy.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_splitwise.*
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.adapter.SplitwiseRecyclerAdapter
import tech.risinglight.financebuddy.model.SplitWiseModel
import tech.risinglight.financebuddy.model.Transaction
import tech.risinglight.financebuddy.repo.Repo

class SplitwiseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splitwise)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val repo = Repo(application)
        val adapter = SplitwiseRecyclerAdapter(repo.getAllSW())
        splitWiseRV.layoutManager = linearLayoutManager
        splitWiseRV.adapter = adapter
        splitwiseFab.setOnClickListener {
            startActivity(Intent(applicationContext,AddSplitwiseActivity::class.java))
        }
    }
}
