package tech.risinglight.financebuddy.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.databinding.BottomSheetAdapterRowBinding
import tech.risinglight.financebuddy.model.Transaction

class TransactionsRecyclerViewAdapter(private val transacationsModelArrayList: ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionsRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val transactionRowBinding = DataBindingUtil.inflate<BottomSheetAdapterRowBinding>(layoutInflater,R.layout.bottom_sheet_adapter_row,parent,false)
        return ViewHolder(transactionRowBinding)
    }

    override fun getItemCount(): Int {
        return transacationsModelArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transacationsModelArrayList[position]
        holder.bind(transaction)
    }

    inner class ViewHolder(binding: BottomSheetAdapterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var binding: BottomSheetAdapterRowBinding? = binding

        fun bind(obj: Transaction){
            binding!!.setVariable(tech.risinglight.financebuddy.BR.transaction,obj)
        }
    }
}