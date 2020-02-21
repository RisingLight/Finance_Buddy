package tech.risinglight.financebuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_card.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.CardDetailsModel
import tech.risinglight.financebuddy.repo.Repo
import tech.risinglight.financebuddy.repo.RoomDB

class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        addBtn.setOnClickListener {
            GlobalScope.launch {
                addToDB()
                finish()
            }

            //   finish()


        }


    }

    private suspend fun addToDB() = withContext(IO) {
        val cardDetailsModel = CardDetailsModel(upi_idET.text.toString(),nickNameET.text.toString())
        val db = RoomDB.getInstance(application)
        val dao = db?.cardDataRepo()
        // var cardLD = dao.getAll()
        val repo = Repo(application)

        print("Id =" + repo.insert(cardDetailsModel))
    }
}
