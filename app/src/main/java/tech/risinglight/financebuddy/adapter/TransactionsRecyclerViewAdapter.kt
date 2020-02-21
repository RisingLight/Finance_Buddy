package tech.risinglight.financebuddy.adapter

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.Transaction
import tech.risinglight.financebuddy.view.MessageDetails

class TransactionsRecyclerViewAdapter(private val transacationsModelArrayList: ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.bottom_sheet_adapter_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return transacationsModelArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transacationsModelArrayList[position]
        val amt = (if (transaction.type) '+' else '-') + transaction.amount
        holder.amount.text = amt
        holder.date.text = transaction.time.toString()
        holder.name.text = transaction.number
        holder.container.setOnClickListener {
            val intent = Intent(holder.container.context, MessageDetails::class.java)
            intent.putExtra("number", transaction.number)
            holder.container.context.startActivity(intent)
        }

    }

    inner class ViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {
        var container = binding.findViewById<ConstraintLayout>(R.id.transactionContainer)
        var name = binding.findViewById<TextView>(R.id.transactionName)
        var date = binding.findViewById<TextView>(R.id.transactionDate)
        var amount = binding.findViewById<TextView>(R.id.transactionAmount)
    }
}