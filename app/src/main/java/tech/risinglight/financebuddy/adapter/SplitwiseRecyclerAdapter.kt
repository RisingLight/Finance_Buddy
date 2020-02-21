package tech.risinglight.financebuddy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.SplitWiseModel
import tech.risinglight.financebuddy.view.AddSplitwiseActivity
import java.util.*

class SplitwiseRecyclerAdapter(private val splitwiseModelArrayList: List<SplitWiseModel>) :
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
        holder.container.setOnClickListener {
            var intent = Intent(holder.container.context, AddSplitwiseActivity::class.java)
            intent.putExtra("email",currentCard.emailId)
            intent.putExtra("name",currentCard.name)
            intent.putExtra("amount",currentCard.amount)
            holder.container.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return splitwiseModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var container: ConstraintLayout = itemView.findViewById(R.id.splitwiseContainer)
        var nameTV: TextView = itemView.findViewById(R.id.nameSWTV)
        var amountTV: TextView = itemView.findViewById(R.id.amountSWTV)
    }

}