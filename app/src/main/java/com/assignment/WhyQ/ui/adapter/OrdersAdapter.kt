package com.assignment.WhyQ.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.WhyQ.databinding.ItemOrderViewBinding
import com.example.example.Orders

class OrdersAdapter(private val orders : List<Orders>) : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {




    inner class OrdersViewHolder(val binding : ItemOrderViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return OrdersViewHolder(ItemOrderViewBinding.inflate(layoutInflater,parent, false))
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.binding.tvOrderId.text = "Order #${orders[position].id}"
        holder.binding.tvAmount.text = "$${orders[position].orderTotal}"
    }
}