package tech.risinglight.financebuddy.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.risinglight.financebuddy.model.CardDetailsModel

@Database(entities = arrayOf(CardDetailsModel::class), version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun cardDataRepo(): CardDao

    companion object {
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB? {
            if (INSTANCE == null) {
                synchronized(RoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        RoomDB::class.java, "card.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}