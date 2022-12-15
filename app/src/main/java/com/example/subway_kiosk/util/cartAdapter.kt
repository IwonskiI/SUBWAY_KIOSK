package com.example.subway_kiosk.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.subway_kiosk.R
import com.example.subway_kiosk.databinding.ShoppingCartBinding

class cartViewHolder(val binding: ShoppingCartBinding): RecyclerView.ViewHolder(binding.root)

class cartAdapter(val OrderList: ArrayList<Sandwich?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
            return cartViewHolder(ShoppingCartBinding.inflate(LayoutInflater.from(parent.context),parent, false))
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as cartViewHolder).binding
            binding.tvMenu.text = "메뉴 이름: " + OrderList[position]?.getSandwichName()
            binding.tvPrice.text = "가격: " + OrderList[position]?.getPrice()
        }

        override fun getItemCount(): Int {
            return OrderList.size
        }
}