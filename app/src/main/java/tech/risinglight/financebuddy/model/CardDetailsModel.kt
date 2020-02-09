package tech.risinglight.financebuddy.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cardDetails")
class CardDetailsModel(
    id: Long,
    cardNumber: Long,
    accountBalance: String,
    bankName: String
) {
    @PrimaryKey(autoGenerate = true)
    @NonNull
     var id: Long = id
     var cardNumber: Long= cardNumber
     var accountBalance: String= accountBalance
     var bankName: String = bankName

}