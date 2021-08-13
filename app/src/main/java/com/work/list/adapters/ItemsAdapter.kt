package com.work.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.work.list.databinding.ListItemTypeGridBinding
import com.work.list.databinding.ListItemTypeListBinding
import com.work.list.models.Items

const val TYPE_LIST = 0
const val TYPE_GRID = 1

class ItemsAdapter(private val type: Int = TYPE_LIST ) : ListAdapter<Items, ItemsAdapter.MyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return when(type){

            TYPE_LIST -> MyViewHolder(ListItemTypeListBinding.inflate(LayoutInflater.from(parent.context)))
            else -> MyViewHolder(ListItemTypeGridBinding.inflate(LayoutInflater.from(parent.context)))
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class MyViewHolder(private var binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: Items) {

            when(binding){
                is ListItemTypeGridBinding -> {
                    (binding as ListItemTypeGridBinding).apply {
                        item = items
                        executePendingBindings()
                    }
                }
                is ListItemTypeListBinding -> {
                    (binding as ListItemTypeListBinding).apply {
                        item = items
                        executePendingBindings()
                    }
                }
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.name == newItem.name
        }
    }

}