package tech.risinglight.financebuddy.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_expense_manager.*
import kotlinx.coroutines.*
import tech.risinglight.financebuddy.adapter.ConversationRecyclerViewAdapter
import tech.risinglight.financebuddy.model.MessagesModel
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.getSmsConversation
import tech.risinglight.financebuddy.model.ConversationModel


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
                val conversationModel =
                    ConversationModel()
                var name: String? = ""
                conversation.message.forEach { message ->

                    val matches = regex.findAll(message.body)
                    matches.forEach {


                        if (message.body.contains("debit") || message.body.contains("debited")
                            || message.body.contains("Paid") ||
                            message.body.contains("charged") || message.body.contains(
                                "Debit"
                            ) || message.body.contains("DEBITED") || message.body.contains(
                                "DEBIT"
                            )
                        ) {
                            if ((message.date.toString().contains("Dec") || message.date.toString().contains(
                                    "Jan"
                                )) && message.date.toString().contains("2019")
                            ) {

                                val messagesModel =
                                    MessagesModel()
                                messagesModel.type = "debit"
                                System.out.println("DEBIT")
                                messagesModel.time = (message.date).toString()
                                System.out.println(message.date.toString())
                                messagesModel.purpose = message.number
                                conversationModel.name = message.number
                                messagesModel.amount = it.value
                                name = message.number
                                arrayList.add(messagesModel)
                            }
                        }
//                        else if (message.body.contains("credit") || message.body.contains("Credit") ||message.body.contains("credited")  ||message.body.contains("CREDITED")  || message.body.contains(
//                                "CREDIT"
//                            )
//                        ) {
//                            messagesModel.type = "credit"
//                            System.out.println("CREDIT")
//                        }
//
//                        System.out.println(it.value)
                        System.out.println("-----------------------------------------------------------------")

                    }

                }
                if (name!!.isNotEmpty()) {
                    var totalAmt = 0
                    for (message in arrayList) {
                        totalAmt += getAmount(message.amount!!)
                    }
                    conversationModel.messageList = arrayList
                    conversationModel.amount =
                        "Rs." + totalAmt.toString().subSequence(
                            0,
                            totalAmt.toString().length - 3
                        ) + "." + totalAmt.toString().subSequence(
                            totalAmt.toString().length - 2,
                            totalAmt.toString().length
                        )
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
            val adapter =
                ConversationRecyclerViewAdapter(
                    arrayList
                )
            rv.adapter = adapter
        }


}
