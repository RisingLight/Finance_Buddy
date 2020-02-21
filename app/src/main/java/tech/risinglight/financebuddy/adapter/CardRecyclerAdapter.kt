package tech.risinglight.financebuddy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.view.UpiPaymentActivity
import tech.risinglight.financebuddy.model.CardDetailsModel

class CardRecyclerAdapter(private val cardDetailsModelArrayList: List<CardDetailsModel>) :
    RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.main_card_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentCard = cardDetailsModelArrayList[position]
        holder.cardNumberTV.text = currentCard.nickname
        holder.cardAmountTV.text = currentCard.upiId
        holder.card.setOnClickListener{
            val intent = Intent(holder.card.context , UpiPaymentActivity::class.java)
            intent.putExtra("UPI", currentCard.upiId)
            it.context.startActivity(intent )
        }
    }

    override fun getItemCount(): Int {
        return cardDetailsModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cardNumberTV: TextView = itemView.findViewById(R.id.cardNumberTV)
        var cardAmountTV: TextView = itemView.findViewById(R.id.amountTV)
        var card : ConstraintLayout = itemView.findViewById(R.id.main_card_row_view)

    }

}