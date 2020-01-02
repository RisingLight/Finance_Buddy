package tech.risinglight.financebuddy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import java.util.*

class ConversationRecyclerViewAdapter(private val conversationModelList: ArrayList<ConversationModel>) :
    RecyclerView.Adapter<ConversationRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.conversation_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.amountTV.text = conversationModelList[position].amount
        holder.purposeTV.text = conversationModelList[position].name
        holder.conversationLayout.setOnClickListener {
            val ctx = holder.conversationLayout.context
            val intent = Intent(ctx, MessageActivity::class.java )
          //  intent.putExtra("messages", conversationModelList[position].messageList)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return conversationModelList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var purposeTV: TextView = itemView.findViewById(R.id.conName)
        var amountTV: TextView = itemView.findViewById(R.id.conAmount)
        var conversationLayout: CardView = itemView.findViewById(R.id.conversationLayout)
    }

}