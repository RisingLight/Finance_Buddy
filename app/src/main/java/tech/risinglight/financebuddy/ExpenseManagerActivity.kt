package tech.risinglight.financebuddy

import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ExpenseManagerActivity : AppCompatActivity() {
    val map: HashMap<String,String> = HashMap()
    val regex: Regex = Regex("""((?:Rs.)\s*(\d+,*\d*(?:\.\d{2})?))""")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_manager)
//
        AsyncTask.execute {
           // readSMS()
            getSmsConversation(this){ conversations ->
                conversations?.forEach { conversation ->
                   // println("Number: ${conversation.number}")
                    conversation.message.forEach { message ->
                        var matches = regex.findAll(message.body)
                       // print((matches!!.value))
                       matches.forEach {

                            Log.d(conversation.number,it.value)
                        }
                        //println("Message : ${it.body}")
                    }

                }
            }
        }

    }
/*
    private fun readSMS() {
        val cursor: Cursor? =
            contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    var msgData = ""
                    for (idx in 0 until cursor.columnCount) {
                        msgData += " " + cursor.getColumnName(idx).toString() + ":" + cursor.getString(idx)

                        var sender: String = msgData.substring()
                        map.put()
                    }
                    // use msgData
                    Log.d("MSG",msgData)
                } while (cursor.moveToNext())
            } else { // empty box, no SMS
                Toast.makeText(applicationContext, "No SMS Found!",Toast.LENGTH_SHORT).show()
            }
        }
    }*/

}
