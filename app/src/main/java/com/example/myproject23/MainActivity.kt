package com.example.myproject23

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import com.example.myproject23.Database.AppDatabase
import com.example.myproject22.MyAdapter
import com.example.myproject23.Database.VerbsDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var img = (0..4).random()
        when(img){
            0 -> Glavnoe.setBackgroundResource(R.drawable.fwalls0)
            1 -> Glavnoe.setBackgroundResource(R.drawable.fwalls1)
            2 -> Glavnoe.setBackgroundResource(R.drawable.fwalls2)
            3 -> Glavnoe.setBackgroundResource(R.drawable.fwalls3)
        }
        Menu1(menu1)
        Menu2(menu2)
        Menu3(menu3)
    }

    fun Menu1(btn:LinearLayout) {
        btn.setOnClickListener {
            var intent_main = Intent(this,IrregularVerbsActivity::class.java)
            startActivity(intent_main)
        }
    }

    fun Menu2(btn:LinearLayout) {
        btn.setOnClickListener {
            var intent_main = Intent(this,QuizActivity::class.java)
            startActivity(intent_main)
        }
    }

    fun Menu3(btn:LinearLayout) {
        btn.setOnClickListener {
            var intent_main = Intent(this,VerbsFormsActivity::class.java)
            startActivity(intent_main)
        }
    }

}