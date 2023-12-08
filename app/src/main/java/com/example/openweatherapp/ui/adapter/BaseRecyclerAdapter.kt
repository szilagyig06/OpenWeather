package com.example.openweatherapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<T : Any, VB : ViewBinding>(
    private val mItems: MutableList<T>,
    private val listener: Listener<T>
) : RecyclerView.Adapter<BaseRecyclerAdapter<T, VB>.ViewHolder>() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun getViewBinding(parent: ViewGroup): VB

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = getViewBinding(parent)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun setItems(items: List<T>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { listener.onItemClick(mItems[adapterPosition]) }
        }
    }

    interface Listener<T> {
        fun onItemClick(item: T)
        fun onItemLongClick(item: T) {}
    }
}