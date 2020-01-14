package tech.risinglight.financebuddy.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import tech.risinglight.financebuddy.model.CardDetailsModel

@Dao
interface CardDao{
    @Query("SELECT * from cardDetails")
    fun getAll(): List<CardDetailsModel>

    @Insert(onConflict = REPLACE)
    fun insert(cardDetails: CardDetailsModel)

    @Query("DELETE from cardDetails")
    fun deleteAll()
}
