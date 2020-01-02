package tech.risinglight.financebuddy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_expense_manager.*
import kotlinx.android.synthetic.main.conversation_row.*
import kotlinx.coroutines.*


class ExpenseManagerActivity : AppCompatActivity() {
    private val regex: Regex = Regex("""((?:Rs.)\s*(\d+,*\d*(?:\.\d{2})?))""")
    private lateinit var gridLayoutManager: GridLayoutManager
    private var arrayList: ArrayList<MessagesModel> = ArrayList()
    private var convArrList: ArrayList<ConversationModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_manager)
        gridLayoutManager = GridLayoutManager(this, 2)

        GlobalScope.launch {
            getConversations()
        }
        rv.layoutManager = gridLayoutManager
        Toast.makeText(applicationContext, arrayList.size.toString(), Toast.LENGTH_SHORT).show()
    }

    private suspend fun getConversations() = withContext(Dispatchers.IO) {
        getSmsConversation(applicationContext) { conversations ->
            conversations?.forEach { conversation ->
                val conversationModel = ConversationModel()
                var name: String? = ""
                conversation.message.forEach { message ->

                    val matches = regex.findAll(message.body)
                    matches.forEach {
                        val messagesModel = MessagesModel()
                        messagesModel.amount = it.value
                        messagesModel.time = (message.date).toString()
                        messagesModel.purpose = message.number
                        conversationModel.name = message.number
                        name = message.number
                        if (message.body.contains("debit") || message.body.contains("Debit") || message.body.contains(
                                "DEBIT"
                            )
                        ) {
                            messagesModel.type = "debit"
                        } else if (message.body.contains("credit") || message.body.contains("Credit") || message.body.contains(
                                "CREDIT"
                            )
                        ) {
                            messagesModel.type = "credit"
                        }
                        arrayList.add(messagesModel)
                    }

                }
                if (name!!.isNotEmpty()) {
                    var totalAmt = 0
                    for (message in arrayList) {
                        totalAmt += getAmount(message.amount!!)
                    }
                    conversationModel.messageList = arrayList
                    conversationModel.amount = totalAmt.toString()
                    convArrList.add(conversationModel)
                }
            }
        }
        setAdapter(convArrList)
    }

    private fun getAmount(amount: String): Int {
        var finalAmount = ""
        for (c: Char in amount) {
            if (c.isDigit()) {
                finalAmount += c
            }
        }
        return Integer.parseInt(finalAmount)
    }

    private suspend fun setAdapter(arrayList: ArrayList<ConversationModel>) =
        withContext(Dispatchers.Main) {
            val adapter = ConversationRecyclerViewAdapter(arrayList)
            rv.adapter = adapter
        }


}
