package com.example.subway_kiosk.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.subway_kiosk.R

class SaleAdapter(val context: Context, val OrderList: ArrayList<Sale>) : BaseAdapter() {
    override fun getCount(): Int {
        return OrderList.size
    }

    override fun getItem(position: Int): Any {
        return OrderList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_item_sale, null)

        // id 로 view 찾기
        val menu = view.findViewById<TextView>(R.id.tv_menu)
        val price = view.findViewById<TextView>(R.id.tv_price)
        val date = view.findViewById<TextView>(R.id.tv_date)

        val order = OrderList[position]

        menu.text = order.menu
        price.text = order.price.toString()
        date.text = order.date.toString()

        return view
    }

}