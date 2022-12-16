package com.example.subway_kiosk.manager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.subway_kiosk.ManagerActivity
import com.example.subway_kiosk.databinding.ManagerStockBreadBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.example.subway_kiosk.util.Stock


class ManagerStockBread : AppCompatActivity()
{
    var RootRef = Firebase.database.reference
    var stockRef = RootRef.child("stock")
    //get firebase
    var bread_num = Array<Int?>(7,{0});
    var cheese_num = Array<Int?>(4,{0});
    var white_num: Int? = 0;
    var honey_num: Int? = 0;
    var wheet_num: Int? = 0;
    var pamasan_num: Int? = 0;
    var hati_num: Int? = 0;
    var flat_num: Int? = 0;
    var american_num: Int? = 0;
    var mozzarella_num: Int? = 0;
    var shred_num: Int? = 0;
    var cnt: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ManagerStockBreadBinding.inflate(layoutInflater)

        setContentView(binding.root)

        stockRef.child("bread").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var bread : Stock? = item.getValue(Stock::class.java)
                    bread_num[cnt++] = bread?.num;
                }
                cnt = 0;
                flat_num = bread_num[0];
                hati_num = bread_num[1];
                honey_num = bread_num[2];
                pamasan_num = bread_num[3];
                wheet_num = bread_num[4];
                white_num = bread_num[5];

                binding.whiteStock.text = white_num.toString().plus("개");
                binding.honeyStock.text = honey_num.toString().plus("개");
                binding.wheetStock.text = wheet_num.toString().plus("개");
                binding.pamasanStock.text = pamasan_num.toString().plus("개");
                binding.hatiStock.text = hati_num.toString().plus("개");
                binding.flatStock.text = flat_num.toString().plus("개");
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )

        stockRef.child("cheese").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    var cheese : Stock? = item.getValue(Stock::class.java)
                    cheese_num[cnt++] = cheese?.num;
                }
                cnt = 0;
                american_num = cheese_num[0];
                mozzarella_num = cheese_num[1];
                shred_num = cheese_num[2];

                binding.americanStock.text = american_num.toString().plus("개");
                binding.mozzarellaStock.text = mozzarella_num.toString().plus("개");
                binding.shredStock.text = shred_num.toString().plus("개");
            }
            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        } )
        binding.home.setOnClickListener{
            finish()
        }

        binding.manageMeat.setOnClickListener{
            finish()
            val nextIntent = Intent(this@ManagerStockBread, ManagerStockMeat::class.java)
            startActivity(nextIntent)
        }
        binding.manageVege.setOnClickListener{
            finish()
            val nextIntent = Intent(this@ManagerStockBread, ManagerStockVege::class.java)
            startActivity(nextIntent)
        }
        binding.manageSauce.setOnClickListener{
            finish()
            val nextIntent = Intent(this@ManagerStockBread, ManagerStockSauce::class.java)
            startActivity(nextIntent)
        }

        binding.whiteMinus.setOnClickListener{
            white_num = white_num?.minus(1)
            var key = "white"
            stockRef.child("bread").child(key).child("num").setValue(white_num)
        }
        binding.whitePlus.setOnClickListener{
            white_num = white_num?.plus(1);
            var key = "white"
            stockRef.child("bread").child(key).child("num").setValue(white_num)
        }

        binding.honeyMinus.setOnClickListener{
            honey_num = honey_num?.minus(1);
            var key = "honey"
            stockRef.child("bread").child(key).child("num").setValue(honey_num)
        }
        binding.honeyPlus.setOnClickListener{
            honey_num = honey_num?.plus(1);
            var key = "honey"
            stockRef.child("bread").child(key).child("num").setValue(honey_num)
        }

        binding.wheetMinus.setOnClickListener{
            wheet_num = wheet_num?.minus(1);
            var key = "wheet"
            stockRef.child("bread").child(key).child("num").setValue(wheet_num)
        }
        binding.wheetPlus.setOnClickListener{
            wheet_num = wheet_num?.plus(1);
            var key = "wheet"
            stockRef.child("bread").child(key).child("num").setValue(wheet_num)
        }

        binding.pamasanMinus.setOnClickListener{
            pamasan_num = pamasan_num?.minus(1);
            var key = "pamasan"
            stockRef.child("bread").child(key).child("num").setValue(pamasan_num)
        }
        binding.pamasanPlus.setOnClickListener{
            pamasan_num = pamasan_num?.plus(1);
            var key = "pamasan"
            stockRef.child("bread").child(key).child("num").setValue(pamasan_num)
        }

        binding.hatiMinus.setOnClickListener{
            hati_num = hati_num?.minus(1);
            var key = "hati"
            stockRef.child("bread").child(key).child("num").setValue(hati_num)
        }
        binding.hatiPlus.setOnClickListener{
            hati_num = hati_num?.plus(1);
            var key = "hati"
            stockRef.child("bread").child(key).child("num").setValue(hati_num)
        }

        binding.flatMinus.setOnClickListener{
            flat_num = flat_num?.minus(1);
            var key = "flat"
            stockRef.child("bread").child(key).child("num").setValue(flat_num)
        }
        binding.flatPlus.setOnClickListener{
            flat_num = flat_num?.plus(1);
            var key = "flat"
            stockRef.child("bread").child(key).child("num").setValue(flat_num)
        }

        binding.americanMinus.setOnClickListener{
            american_num = american_num?.minus(1);
            var key = "american"
            stockRef.child("cheese").child(key).child("num").setValue(american_num)
        }
        binding.americanPlus.setOnClickListener{
            american_num = american_num?.plus(1);
            var key = "american"
            stockRef.child("cheese").child(key).child("num").setValue(american_num)
        }

        binding.mozzarellaMinus.setOnClickListener{
            mozzarella_num = mozzarella_num?.minus(1);
            var key = "mozzarella"
            stockRef.child("cheese").child(key).child("num").setValue(mozzarella_num)
        }
        binding.mozzarellaPlus.setOnClickListener{
            mozzarella_num = mozzarella_num?.plus(1);
            var key = "mozzarella"
            stockRef.child("cheese").child(key).child("num").setValue(mozzarella_num)
        }

        binding.shredMinus.setOnClickListener{
            shred_num = shred_num?.minus(1);
            var key = "shred"
            stockRef.child("cheese").child(key).child("num").setValue(shred_num)
        }
        binding.shredPlus.setOnClickListener{
            shred_num = shred_num?.plus(1);
            var key = "shred"
            stockRef.child("cheese").child(key).child("num").setValue(shred_num)

        }

    }
}