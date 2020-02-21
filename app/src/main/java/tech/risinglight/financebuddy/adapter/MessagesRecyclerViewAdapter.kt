package tech.risinglight.financebuddy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.MessagesModel
import java.util.*

class MessagesRecyclerViewAdapter(private val messagesModelArrayList: ArrayList<MessagesModel>) :
    RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.messages_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.timeTV.text = messagesModelArrayList[position].time
        holder.amountTV.text = messagesModelArrayList[position].amount
        holder.purposeTV.text = messagesModelArrayList[position].purpose
        holder.container.setOnClickListener {
            val intent = Intent()
            intent.putExtra("array",messagesModelArrayList)
            intent.putExtra("name",messagesModelArrayList[position].purpose)
        }
    }

    override fun getItemCount(): Int {
        return messagesModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var container:ConstraintLayout = itemView.findViewById(R.id.messageContainer)
        var purposeTV: TextView = itemView.findViewById(R.id.purposeTV)
        var amountTV: TextView = itemView.findViewById(R.id.amountTV)
        var timeTV: TextView = itemView.findViewById(R.id.timeTV)

    }

}