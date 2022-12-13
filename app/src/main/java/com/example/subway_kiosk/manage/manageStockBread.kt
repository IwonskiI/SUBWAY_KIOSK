package com.example.subway_kiosk.manage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subway_kiosk.R
import com.example.subway_kiosk.order.MenuSelect
import com.example.subway_kiosk.order.VegeSelect
import com.example.subway_kiosk.util.Sandwich
import kotlinx.coroutines.selects.select
import kotlin.system.exitProcess
import com.example.subway_kiosk.databinding.ManagerStockBreadBinding

class manageStockBread : AppCompatActivity()
{
    //get firebase
    var white_num: Int = 3;
    var honey_num: Int = 4;
    var wheet_num: Int = 5;
    var pamasan_num: Int = 6;
    var hati_num: Int = 7;
    var flat_num: Int = 8;
    var american_num: Int = 9;
    var mozzarella_num: Int = 2;
    var shred_num: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ManagerStockBreadBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.whiteStock.text = white_num.toString().plus("개");
        binding.honeyStock.text = honey_num.toString().plus("개");
        binding.wheetStock.text = wheet_num.toString().plus("개");
        binding.pamasanStock.text = pamasan_num.toString().plus("개");
        binding.hatiStock.text = hati_num.toString().plus("개");
        binding.flatStock.text = flat_num.toString().plus("개");
        binding.americanStock.text = american_num.toString().plus("개");
        binding.mozzarellaStock.text = mozzarella_num.toString().plus("개");
        binding.shredStock.text = shred_num.toString().plus("개");


        binding.manageMeat.setOnClickListener{
            val nextIntent = Intent(this@manageStockBread, manageStockMeat::class.java)
            startActivity(nextIntent)
        }

        binding.whiteMinus.setOnClickListener{
            white_num -= 1;
            //post firebase
            binding.whiteStock.text = white_num.toString().plus("개");
        }
        binding.whitePlus.setOnClickListener{
            white_num += 1;
            //post firebase
            binding.whiteStock.text = white_num.toString().plus("개");
        }

        binding.honeyMinus.setOnClickListener{
            honey_num -= 1;
            //post firebase
            binding.honeyStock.text = honey_num.toString().plus("개");
        }
        binding.honeyPlus.setOnClickListener{
            honey_num += 1;
            //post firebase
            binding.honeyStock.text = honey_num.toString().plus("개");
        }

        binding.wheetMinus.setOnClickListener{
            wheet_num -= 1;
            //post firebase
            binding.wheetStock.text = wheet_num.toString().plus("개");
        }
        binding.wheetPlus.setOnClickListener{
            wheet_num += 1;
            //post firebase
            binding.wheetStock.text = wheet_num.toString().plus("개");
        }

        binding.pamasanMinus.setOnClickListener{
            pamasan_num -= 1;
            //post firebase
            binding.pamasanStock.text = pamasan_num.toString().plus("개");
        }
        binding.pamasanPlus.setOnClickListener{
            pamasan_num += 1;
            //post firebase
            binding.pamasanStock.text = pamasan_num.toString().plus("개");
        }

        binding.hatiMinus.setOnClickListener{
            hati_num -= 1;
            //post firebase
            binding.hatiStock.text = hati_num.toString().plus("개");
        }
        binding.hatiPlus.setOnClickListener{
            hati_num += 1;
            //post firebase
            binding.hatiStock.text = hati_num.toString().plus("개");
        }

        binding.flatMinus.setOnClickListener{
            flat_num -= 1;
            //post firebase
            binding.flatStock.text = flat_num.toString().plus("개");
        }
        binding.flatPlus.setOnClickListener{
            flat_num += 1;
            //post firebase
            binding.flatStock.text = flat_num.toString().plus("개");
        }

        binding.americanMinus.setOnClickListener{
            american_num -= 1;
            //post firebase
            binding.americanStock.text = american_num.toString().plus("개");
        }
        binding.americanPlus.setOnClickListener{
            american_num += 1;
            //post firebase
            binding.americanStock.text = american_num.toString().plus("개");
        }

        binding.mozzarellaMinus.setOnClickListener{
            mozzarella_num -= 1;
            //post firebase
            binding.mozzarellaStock.text = mozzarella_num.toString().plus("개");
        }
        binding.mozzarellaPlus.setOnClickListener{
            mozzarella_num += 1;
            //post firebase
            binding.mozzarellaStock.text = mozzarella_num.toString().plus("개");
        }

        binding.shredMinus.setOnClickListener{
            shred_num -= 1;
            //post firebase
            binding.shredStock.text = shred_num.toString().plus("개");
        }
        binding.shredPlus.setOnClickListener{
            shred_num += 1;
            //post firebase
            binding.shredStock.text = shred_num.toString().plus("개");
        }

    }




}