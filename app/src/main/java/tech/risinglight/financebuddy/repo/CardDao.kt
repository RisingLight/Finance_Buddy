package tech.risinglight.financebuddy.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import tech.risinglight.financebuddy.model.CardDetailsModel
import tech.risinglight.financebuddy.model.SplitWiseModel

@Dao
interface CardDao{
    @Query("SELECT * from cardDetails")
    fun getAll(): LiveData<List<CardDetailsModel>>

    @Insert
    fun insert(cardDetails: CardDetailsModel) : Long

    @Query("DELETE from cardDetails")
    fun deleteAll()

    @Insert
    fun insert(splitWiseDetail: SplitWiseModel) : Long

}
