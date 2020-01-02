package tech.risinglight.financebuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
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
    }

    override fun getItemCount(): Int {
        return messagesModelArrayList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var purposeTV: TextView = itemView.findViewById(R.id.purposeTV)
        var amountTV: TextView = itemView.findViewById(R.id.amountTV)
        var timeTV: TextView = itemView.findViewById(R.id.timeTV)

    }

}