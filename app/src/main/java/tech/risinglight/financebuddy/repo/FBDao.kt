package tech.risinglight.financebuddy.repo

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.risinglight.financebuddy.model.CardDetailsModel
import tech.risinglight.financebuddy.model.SplitWiseModel

@Dao
interface FBDao{
    @Query("SELECT * from cardDetails")
    fun getAll(): LiveData<List<CardDetailsModel>>

    @Insert
    fun insert(cardDetails: CardDetailsModel) : Long

    @Query("DELETE from cardDetails")
    fun deleteAll()

    @Insert
    fun insert(splitWiseDetail: SplitWiseModel) : Long

    @Query("SELECT * from splitModel")
    fun getSplitwiseAll(): List<SplitWiseModel>

    @Insert
    fun insertSW(splitWiseDetail: SplitWiseModel) : Long

    @Query("DELETE from splitModel")
    fun deleteSWAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateSW(splitWiseModel: SplitWiseModel)

    @Query("SELECT * from splitModel WHERE emailId = :email")
    fun getSpecificSW(email: String) : List<SplitWiseModel>

}
