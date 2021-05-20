package com.kadek.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadek.core.databinding.ItemsListBinding
import com.kadek.core.domain.model.Tv
import com.kadek.core.utils.Helper

@Suppress("DEPRECATION")
class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private var listTv = ArrayList<Tv>()
    var onItemClick: ((Tv) -> Unit)? = null

    fun setTv(tv: List<Tv>?) {
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsListBinding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsListBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val movie = listTv[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listTv.size

    inner class TvViewHolder(private val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv : Tv) {
            with(binding) {
                tv.poster.let {
                    Helper.setImage(
                        itemView.context,
                        Helper.POSTER_ENDPOINT + Helper.POSTER_W185 + it,
                        imgPoster
                    )
                }
                tvItemTitle.text = tv.title
                tvItemDescription.text = tv.description
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTv[adapterPosition])
            }
        }
    }
}