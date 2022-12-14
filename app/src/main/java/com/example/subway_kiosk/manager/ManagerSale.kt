package com.example.subway_kiosk.manager

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.*
import com.example.subway_kiosk.util.Sale
import com.example.subway_kiosk.util.SaleAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ManagerSale : AppCompatActivity() {
    private lateinit var binding: ManagerSaleBinding
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ManagerSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DB connection
        database = Firebase.database.reference

        // DB data parsing
        var todaySetPrice: Int
        var todaySinglePrice: Int

        var monthSetPrice: Int
        var monthSinglePrice: Int

        var todayTotalPrice: Int
        var monthTotalPrice: Int

        // 금일, 월간 필터링
        lateinit var todaySetList: ArrayList<Sale>
        lateinit var todaySingleList: ArrayList<Sale>
        lateinit var monthSetList: ArrayList<Sale>
        lateinit var monthSingleList: ArrayList<Sale>


        // all data -> type에 따라 분류, 날짜에 따라 분류
        var saleList = arrayListOf<Sale>()

        // db 에서 가져오기
        database.child("sale_status").get().addOnSuccessListener {
            for (item in it.children) {
                val maps = item.value as HashMap<String, Any>
                val sale = Sale(maps.get("menu").toString(), maps.get("price").toString().toInt(), LocalDate.parse(maps.get("date").toString(), DateTimeFormatter.ISO_DATE),maps.get("type").toString())
                saleList.add(sale)
            }

            todaySetList = saleList.filter { it.date == LocalDate.now() && it.type.equals("SET", false)  } as ArrayList<Sale>
            todaySingleList = saleList.filter { it.date == LocalDate.now() && it.type.equals("SINGLE", false)  } as ArrayList<Sale>
            monthSetList = saleList.filter { it.date.month ==LocalDate.now().month && it.type.equals("SET", false) } as ArrayList<Sale>
            monthSingleList = saleList.filter { it.date.month ==LocalDate.now().month && it.type.equals("SINGLE", false) } as ArrayList<Sale>

            todaySetPrice = 0
            todaySetList.forEach{ todaySetPrice += it.price}

            todaySinglePrice = 0
            todaySingleList.forEach{ todaySinglePrice += it.price}

            monthSetPrice = 0
            monthSetList.forEach{ monthSetPrice += it.price}

            monthSinglePrice = 0
            monthSingleList.forEach{ monthSinglePrice += it.price}

            binding.todaySetBtn.setText("세트 메뉴: " + todaySetPrice.toString() + "원")
            binding.todaySingleBtn.setText("단품 메뉴: " + todaySinglePrice.toString() + "원")

            binding.monthSetBtn.setText("세트 메뉴: " + monthSetPrice.toString() + "원")
            binding.monthSingleBtn.setText("단품 메뉴: " + monthSinglePrice.toString() + "원")

            binding.todayTotalTv.setText("일간 총 매출: " + (todaySetPrice + todaySinglePrice).toString() + "원")
            binding.monthTotalTv.setText("월간 총 매출: " + (monthSetPrice + monthSinglePrice).toString() + "원")

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }


        // event
        binding.todaySetBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)

                val Adapter = SaleAdapter(this.context, todaySetList)
                dialogBinding.listView.adapter = Adapter

                show()
            }
        }
        binding.todaySingleBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)

                val Adapter = SaleAdapter(this.context, todaySingleList)
                dialogBinding.listView.adapter = Adapter

                show()
            }
        }

        binding.monthSetBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)

                val Adapter = SaleAdapter(this.context, monthSetList)
                dialogBinding.listView.adapter = Adapter

                show()
            }
        }

        binding.monthSingleBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)

                val Adapter = SaleAdapter(this.context, monthSingleList)
                dialogBinding.listView.adapter = Adapter

                show()
            }
        }
    }


}