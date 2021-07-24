package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.model.Article
import com.example.newsapp.databinding.ItemArticleBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    class MyViewHolder( val binding:ItemArticleBinding):RecyclerView.ViewHolder(binding.root) {

    }
    private val differCallBack = object : DiffUtil.ItemCallback<Article> (){

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
        holder.itemView.apply {
            Glide.with(this)
                .load(article.urlToImage)
                .into(holder.binding.ivArticleImage)
            holder.binding.tvSource.text = article.source.name
            holder.binding.tvTitle.text = article.title
            holder.binding.tvDescription.text = article.description
            holder.binding.tvPublishedAt.text =
                LocalDate.parse(article.publishedAt,formatter).toString()

            holder.binding.rowLayout.setOnClickListener { onItemClickListener?.let { it(article) } }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }
}