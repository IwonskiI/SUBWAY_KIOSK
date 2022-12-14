package com.example.subway_kiosk.manager

import android.os.Build
import android.os.Bundle
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

        var todaySet: String = "30,000"
        var todaySingle: String = "30,000"
        var todaySide: String = "30,000"

        var monthSet: String = "30,000"
        var monthSingle: String = "30,000"
        var monthSide: String = "30,000"

        var todayTotal: String = "30,000"
        var monthTotal: String = "30,000"

        binding.todaySetBtn.setText("세트 메뉴: " + todaySet + "원")
        binding.todaySingleBtn.setText("단품 메뉴: " + todaySingle + "원")
        binding.todaySideBtn.setText("사이드 메뉴: " + todaySide + "원")

        binding.monthSetBtn.setText("세트 메뉴: " + monthSet + "원")
        binding.monthSingleBtn.setText("단품 메뉴: " + monthSingle + "원")
        binding.monthSideBtn.setText("사이드 메뉴: " + monthSide + "원")

        binding.todayTotalTv.setText("일간 총 매출: " + todayTotal + "원")
        binding.monthTotalTv.setText("월간 총 매출: " + monthTotal + "원")


        // event
        binding.todaySetBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                var OrderList = arrayListOf<Sale>(
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("맛있따", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("야미야미", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("헤헤", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                    Sale("이름", 5000, LocalDate.now()),
                )

                val Adapter = SaleAdapter(this.context, OrderList)
                dialogBinding.listView.adapter = Adapter

                show()
            }
        }
        binding.todaySingleBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.todaySideBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSetBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSideBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSingleBtn.setOnClickListener {
            val dialogBinding = SaleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }


    }
}