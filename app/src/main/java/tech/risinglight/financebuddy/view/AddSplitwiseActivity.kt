package tech.risinglight.financebuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_splitwise.*
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.SplitWiseModel
import tech.risinglight.financebuddy.model.SplitwiseTransaction
import tech.risinglight.financebuddy.model.Transaction
import tech.risinglight.financebuddy.repo.Repo
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddSplitwiseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_splitwise)
        creditRadioButton.isChecked = true
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        var repo = Repo(application)
        if (intent.getStringExtra("email") == null) {
            var transactionList = ArrayList<SplitwiseTransaction>()
            splitwiseAddbtn.setOnClickListener {
                var amount = ""
                if (creditRadioButton.isChecked)
                    amount = amountET.text.toString()
                else
                    amount = "-" + amountET.text.toString()
                val splitwiseTransaction = SplitwiseTransaction(amount.toDouble(), currentDate)
                transactionList.add(splitwiseTransaction)
                var splitwiseModel = SplitWiseModel(
                    emaiET.text.toString(),
                    nameET.text.toString(),
                    amount, transactionList
                )

                repo.insertSW(splitwiseModel)
                startActivity(Intent(applicationContext, SplitwiseActivity::class.java))
                finish()
            }
        } else {
            var splitWiseModelList = repo.getSpSW(intent.getStringExtra("email").toString())
            emaiET.setText(intent.getStringExtra("email").toString())
            nameET.setText(intent.getStringExtra("name").toString())
            emaiET.isEnabled = false
            nameET.isEnabled = false
            splitwiseAddbtn.setOnClickListener {
                var amount = intent.getStringExtra("amount").toString().toDouble()
                print("Amountttttttttttt$amount")
                var finalAmount: Double
                if (creditRadioButton.isChecked)
                    finalAmount = amount + amountET.text.toString().toDouble()
                else
                    finalAmount = amount - amountET.text.toString().toDouble()
                val splitwiseTransaction = SplitwiseTransaction(finalAmount, currentDate)
                splitWiseModelList.get(0).transactionsList!!.toMutableList()
                    .add(splitwiseTransaction)
                var splitwiseModel = SplitWiseModel(
                    emaiET.text.toString(),
                    nameET.text.toString(),
                    finalAmount.toString(),
                    splitWiseModelList.get(0).transactionsList!!.toMutableList()
                )
                repo.updateSW(splitwiseModel)
                startActivity(Intent(applicationContext, SplitwiseActivity::class.java))
                finish()
            }

        }

    }
}
