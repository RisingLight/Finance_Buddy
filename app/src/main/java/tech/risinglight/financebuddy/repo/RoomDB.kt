package tech.risinglight.financebuddy.repo

import android.content.Context
import androidx.room.*
import tech.risinglight.financebuddy.model.CardDetailsModel
import tech.risinglight.financebuddy.model.SplitWiseModel
import tech.risinglight.financebuddy.model.TransactionTypeConverter

@Database(entities = [CardDetailsModel::class, SplitWiseModel::class], version = 1, exportSchema = false)
@TypeConverters(TransactionTypeConverter::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun cardDataRepo(): FBDao

    companion object {
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB? {
            if (INSTANCE == null) {
                synchronized(RoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDB::class.java, "financebuddy"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}