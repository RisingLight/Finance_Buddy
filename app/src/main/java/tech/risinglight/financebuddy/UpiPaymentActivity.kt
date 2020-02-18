package tech.risinglight.financebuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment
import kotlinx.android.synthetic.main.activity_upi_payment.*
import kotlin.random.Random


class UpiPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upi_payment)
        if (intent != null) {
            upi_idET.setText(intent.getStringExtra("UPI"))

        }
        payBtn.setOnClickListener {
            val amount =
                if (amountET.text.toString().contains('.')) amountET.text.toString() else amountET.text.toString() + ".00"
            val random = Random(1100000)
            val easyUpiPayment = EasyUpiPayment.Builder()
                .with(this)
                .setPayeeVpa(upi_idET.text.toString())
                .setPayeeName("")
                .setTransactionId(random.nextInt().toString())
                .setTransactionRefId(random.nextInt().toString())
                .setDescription(noteET.text.toString())
                .setAmount(amount)
                .build()
            easyUpiPayment.startPayment()
        }
    }
}
