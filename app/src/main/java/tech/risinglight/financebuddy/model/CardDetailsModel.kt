package tech.risinglight.financebuddy.model

import android.provider.ContactsContract
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
@Entity(tableName = "cardDetails")
class CardDetailsModel{
    @PrimaryKey
    @NonNull
    var upiId: String? = null
    var nickname: String?=null

    constructor(
        upiId : String,
        nickname: String
    ){
        this.upiId = upiId
        this.nickname = nickname
    }
}