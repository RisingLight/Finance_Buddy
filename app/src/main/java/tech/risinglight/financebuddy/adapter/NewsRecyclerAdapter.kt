package tech.risinglight.financebuddy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.risinglight.financebuddy.R
import tech.risinglight.financebuddy.model.Article
import tech.risinglight.financebuddy.view.NewsDetailsActivity

class NewsRecyclerAdapter(private val articleList: List<Article>) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val currentArticle = articleList[position]
        holder.content.text = currentArticle.description
        holder.heading.text = currentArticle.title
        Glide.with(holder.image.context)
            .load(currentArticle.urlToImage)
            .into(holder.image)
        holder.container.setOnClickListener {
            val intent = Intent(holder.container.context,NewsDetailsActivity::class.java)
            intent.putExtra("title",currentArticle.title)
            intent.putExtra("desc", currentArticle.content)
            holder.container.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById<ConstraintLayout>(R.id.containerNews)
        var image = itemView.findViewById<ImageView>(R.id.imageNews)
        var heading = itemView.findViewById<TextView>(R.id.headingNews)
        var content = itemView.findViewById<TextView>(R.id.contentNews)

    }

}