package tech.risinglight.financebuddy.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_message_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.adapter.TransactionsRecyclerViewAdapter
import tech.risinglight.financebuddy.model.Message
import tech.risinglight.financebuddy.model.Transaction
import java.util.*
import kotlin.collections.ArrayList

class MessageDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_details)
        val number = intent.getStringExtra("number")

        GlobalScope.launch {
            getTransactions(applicationContext, number)
        }
    }

    private suspend fun getTransactions(context: Context, number: String?) =
        withContext(Dispatchers.IO) {


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
                    if (message.number.equals(number!!)) {
                        if (message.date.toString().contains("2020")
                        ) {
                            if (message.body.contains("debit") || message.body.contains("debited")
                                || message.body.contains("Paid") ||
                                message.body.contains("sent") ||
                                message.body.contains("paid") ||
                                message.body.contains("Sent") ||
                                message.body.contains("charged") || message.body.contains(
                                    "Debit"
                                ) || message.body.contains("DEBITED") || message.body.contains(
                                    "DEBIT"
                                )
                            ) {

                                val transaction =
                                    Transaction(message.number, it.value, message.date, false)
                                transactions.add(transaction)

                            } else if (message.body.contains("credit") || message.body.contains("Credit") || message.body.contains(
                                    "credited"
                                ) ||
                                message.body.contains("received") || message.body.contains("Received") || message.body.contains(
                                    "CREDITED"
                                ) || message.body.contains(
                                    "CREDIT"
                                )
                            ) {
                                val transaction =
                                    Transaction(message.number, it.value, message.date, true)
                                transactions.add(transaction)
                            }
                        }
                    }
                }
                setAdapters(context, transactions)
            }
        }

    private suspend fun setAdapters(context: Context, transactions: ArrayList<Transaction>) =
        withContext(Dispatchers.Main) {
            messageDetailsRV.layoutManager = LinearLayoutManager(context)
            messageDetailsRV.adapter = TransactionsRecyclerViewAdapter(transactions)
        }
}

