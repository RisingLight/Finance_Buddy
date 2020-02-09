package tech.risinglight.financebuddy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.SplitWiseModel
import java.util.*

class SplitwiseRecyclerAdapter(private val splitwiseModelArrayList: ArrayList<SplitWiseModel>) :
    RecyclerView.Adapter<SplitwiseRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.splitwise_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentCard = splitwiseModelArrayList[position]
        holder.nameTV.text = currentCard.name
        holder.amountTV.text = currentCard.amount
    }

    override fun getItemCount(): Int {
        return splitwiseModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nameTV: TextView = itemView.findViewById(R.id.nameSWTV)
        var amountTV: TextView = itemView.findViewById(R.id.amountSWTV)

    }

}