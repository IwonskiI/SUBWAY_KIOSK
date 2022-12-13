package com.example.subway_kiosk.manager

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.databinding.ManagerSellBinding
import com.example.subway_kiosk.databinding.SetDialogBinding
import com.example.subway_kiosk.databinding.SideDialogBinding
import com.example.subway_kiosk.databinding.SingleDialogBinding

class ManagerSell : AppCompatActivity() {
    private lateinit var binding: ManagerSellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ManagerSellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var setMenus : HashMap<String, Any> = hashMapOf("One" to 1, "Two" to 2, "Three" to 3, "Test" to 4)
        var sideMenus: HashMap<String, Any>
        var singleMenus: HashMap<String, Any>


        // DB connection
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
            val dialogBinding = SetDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                for (setMenu in setMenus) {
                    dialogBinding.setTv.append(setMenu.toString())
                }
                show()
            }
        }
        binding.todaySingleBtn.setOnClickListener {
            val dialogBinding = SingleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.todaySideBtn.setOnClickListener {
            val dialogBinding = SideDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSetBtn.setOnClickListener {
            val dialogBinding = SetDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSideBtn.setOnClickListener {
            val dialogBinding = SingleDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }
        binding.monthSingleBtn.setOnClickListener {
            val dialogBinding = SideDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(this).run {
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }


    }
}