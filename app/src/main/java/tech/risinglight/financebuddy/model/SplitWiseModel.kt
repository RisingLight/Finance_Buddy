package tech.risinglight.financebuddy.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.lang.reflect.Constructor
import java.util.*

@Entity(tableName ="splitModel")
class SplitWiseModel(
    emailId: String,
    name: String,
    amount: String,
    transactionsList: List<SplitwiseTransaction>
) {
    @PrimaryKey
    @NonNull
    var emailId: String? = emailId
    var name: String? = name
    var amount: String? = amount
    var transactionsList: List<SplitwiseTransaction>? = transactionsList
}