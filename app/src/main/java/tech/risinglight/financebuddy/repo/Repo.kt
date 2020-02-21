package tech.risinglight.financebuddy.repo

import android.app.Application
import androidx.lifecycle.LiveData
import tech.risinglight.financebuddy.model.CardDetailsModel
import tech.risinglight.financebuddy.model.SplitWiseModel

class Repo(application: Application) {
    private val dao: FBDao
    private val cardLD: LiveData<List<CardDetailsModel>>
    private val splitwiseLD: List<SplitWiseModel>

    init {
        val db = RoomDB.getInstance(application)
        dao = db?.cardDataRepo()!!
        cardLD = dao.getAll()
        splitwiseLD = dao.getSplitwiseAll()
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
    fun getAllSW(): List<SplitWiseModel>{
        return splitwiseLD
    }

    fun insertSW(splitWiseModel: SplitWiseModel):Long{
        return dao.insertSW(splitWiseModel)
    }
    fun deleteSWAll(){
        return dao.deleteSWAll()
    }

    fun updateSW(splitWiseModel: SplitWiseModel){
        return dao.updateSW(splitWiseModel)
    }

    fun getSpSW(email: String): List<SplitWiseModel>{
        return dao.getSpecificSW(email)
    }
}

