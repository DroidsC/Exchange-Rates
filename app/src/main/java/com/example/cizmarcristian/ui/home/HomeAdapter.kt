package com.example.cizmarcristian.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cizmarcristian.databinding.HomeRateItemBinding
import com.example.cizmarcristian.model.Rate

class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Rate>? = null

    fun submitList(newItems: List<Rate>?) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeRateItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        items?.let { (viewHolder as ViewHolder).bind(it[position]) }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    inner class ViewHolder(private val binding: HomeRateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) {
            binding.nameTv.text = rate.name
            binding.rateTv.text = rate.rate.toString()
        }
    }
}