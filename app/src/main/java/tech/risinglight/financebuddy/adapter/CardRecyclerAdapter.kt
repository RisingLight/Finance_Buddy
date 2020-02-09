package tech.risinglight.financebuddy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.CardDetailsModel
import java.util.*

class CardRecyclerAdapter(private val cardDetailsModelArrayList: ArrayList<CardDetailsModel>) :
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
        holder.cardNumberTV.text = currentCard.cardNumber.toString()
        holder.cardAmountTV.text = currentCard.accountBalance
    }

    override fun getItemCount(): Int {
        return cardDetailsModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var cardNumberTV: TextView = itemView.findViewById(R.id.cardNumberTV)
        var cardAmountTV: TextView = itemView.findViewById(R.id.amountTV)

    }

}