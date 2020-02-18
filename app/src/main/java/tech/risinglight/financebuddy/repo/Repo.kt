package tech.risinglight.financebuddy.repo

import android.app.Application
import androidx.lifecycle.LiveData
import tech.risinglight.financebuddy.model.CardDetailsModel

class Repo(application: Application) {
    private val dao: CardDao
    private val cardLD: LiveData<List<CardDetailsModel>>

    init {
        val db = RoomDB.getInstance(application)
        dao = db?.cardDataRepo()!!
        cardLD = dao.getAll()
    }

    fun getAll(): LiveData<List<CardDetailsModel>> {
        return cardLD
    }

    fun insert(card: CardDetailsModel): Long {
        return dao.insert(card)
    }

    fun deleteAll() {
        return dao.deleteAll()
    }
}

