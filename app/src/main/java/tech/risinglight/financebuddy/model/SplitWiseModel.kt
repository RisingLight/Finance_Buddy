package tech.risinglight.financebuddy.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
import java.util.*

@Entity(tableName ="splitmodel")
class SplitWiseModel {
    @PrimaryKey
    @NonNull
    var emailId: String? = null
    var name: String? = null
    var amount: String? = null
    var transactionsList: ArrayList<Transaction>

    constructor(
        emailId: String,
        name: String,
        amount: String,

        ) {
        this.emailId = emailId
        this.name = name
        this.amount = amount

    }
}