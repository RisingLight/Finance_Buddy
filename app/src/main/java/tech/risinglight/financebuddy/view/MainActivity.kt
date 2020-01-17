package tech.risinglight.financebuddy.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_main.*
import kotlinx.android.synthetic.main.content_main.*
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.adapter.TransactionsRecyclerViewAdapter
import tech.risinglight.financebuddy.model.Message
import tech.risinglight.financebuddy.model.Transaction
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_sheet.setBackgroundResource(R.drawable.custom_card_round)
        expenseManagerCV.setOnClickListener {
            startActivity(Intent(applicationContext, ExpenseManagerActivity::class.java))
        }
        investmentCV.setOnClickListener {
            startActivity(Intent(applicationContext, InvestmentActivity::class.java))
        }
        addbottomSheetCallBack()
        getTransactions(applicationContext)
    }

    private fun addbottomSheetCallBack() {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            @SuppressLint("SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheet.setBackgroundResource(R.drawable.custom_card_noround)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottom_sheet.setBackgroundResource(R.drawable.custom_card_round)
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        bottom_sheet.setBackgroundResource(R.drawable.custom_card_round)
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        bottom_sheet.setBackgroundResource(R.drawable.custom_card_round)
                    }
                }
            }

        })
    }

    fun getTransactions(context: Context) {
        val cursor =
            context.contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
        val messages = ArrayList<Message>()
        val regex = Regex("""((?:Rs.)\s*(\d+,*\d*(?:\.\d{2})?))""")
        val transactions = ArrayList<Transaction>()
        while (cursor != null && cursor.moveToNext()) {
            val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
            val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
            val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
            val message = Message(number, body, Date(smsDate.toLong()))
            messages.add(message)
        }
        messages.sortedWith(compareBy { it.date })
        messages.forEach { message ->
            val matches = regex.findAll(message.body)
            matches.forEach {

                if ( message.date.toString().contains("2020")
                ) {
                    if (message.body.contains("debit") || message.body.contains("debited")
                        || message.body.contains("Paid") ||
                        message.body.contains("charged") || message.body.contains(
                            "Debit"
                        ) || message.body.contains("DEBITED") || message.body.contains(
                            "DEBIT"
                        )
                    ) {

                        val transaction = Transaction(message.number, it.value, message.date, false)
                        transactions.add(transaction)

                    } else if (message.body.contains("credit") || message.body.contains("Credit") || message.body.contains(
                            "credited"
                        ) || message.body.contains("CREDITED") || message.body.contains(
                            "CREDIT"
                        )
                    ) {
                        val transaction = Transaction(message.number, it.value, message.date, true)
                        transactions.add(transaction)
                    }
                }
            }
        }
      // TODO add adapter:
        sheetRV.layoutManager = LinearLayoutManager(context)
        sheetRV.adapter = TransactionsRecyclerViewAdapter(transactions)
    }
}

