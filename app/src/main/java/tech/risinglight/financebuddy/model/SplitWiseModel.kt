package tech.risinglight.financebuddy.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor
import java.util.*

@Entity(tableName ="splitModel")
class SplitWiseModel {
    @PrimaryKey
    @NonNull
    var emailId: String? = null
    var name: String? = null
    var amount: String? = null
   // var transactionsList: ArrayList<Transaction>

    constructor(
        emailId: String,
        name: String,
        amount: String
      //  transactionsList : ArrayList<Transaction>
        ) {
        this.emailId = emailId
        this.name = name
        this.amount = amount
       // this.transactionsList = transactionsList
    }
}