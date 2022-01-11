package com.example.myproject23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.myproject22.MyAdapter
import com.example.myproject23.Database.AppDatabase
import com.example.myproject23.Database.Verbs
import com.example.myproject23.Database.VerbsDao
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private lateinit var list:MutableList<Verbs>
    private lateinit var verbsdao: VerbsDao
    var count:Int = 1

    var True:Int = 0
    var False:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        verbsdao = AppDatabase.getInstance(this).userDao()
        list = verbsdao.getAll().toMutableList()
        start()
    }

    fun start() {
        lateinit var Variants:MutableList<Verbs>

        Variants = setAnswer()
        var index = setVariants(Variants)
        select(Variants,index)
    }

    fun setAnswer(): MutableList<Verbs> {
        count=1
        var set = mutableSetOf<Int>()
        while (set.size<4){
            var random = (0 until list.size).random()
            set.add(random)
        }
        var Variants = mutableListOf<Verbs>()
        for (i in set){
            Variants.add(list[i])
        }
        return Variants
    }

    fun setVariants(Variants: MutableList<Verbs>): Int {
        var d = (0..3).random()

        var index = (0..2).random()
        when(index) {
            0 -> tv_quiz_answer.text = Variants[d].verb1
            1 -> tv_quiz_answer.text = Variants[d].verb2
            2 -> tv_quiz_answer.text = Variants[d].verb3
        }
        return d
    }

    fun select(Variants: MutableList<Verbs>,index: Int){
        var set = mutableSetOf<Int>()
        while (set.size<4){
            var random = (0..3).random()
            set.add(random)
        }
        var variantform = mutableListOf<TextView>()
        variantform.add(tv_quiz_otvetA)
        variantform.add(tv_quiz_otvetB)
        variantform.add(tv_quiz_otvetC)
        variantform.add(tv_quiz_otvetD)
        for (i in 0 until variantform.size){
            variantform[i].text = Variants[set.elementAt(i)].qaraqalpaq
        }
        selectVariant(variantform[0],Variants,index)
        selectVariant(variantform[1],Variants,index)
        selectVariant(variantform[2],Variants,index)
        selectVariant(variantform[3],Variants,index)
    }

    fun selectVariant(button: TextView,Variants: MutableList<Verbs>,index: Int) {
        button.setOnClickListener {
            if(count==1) {
                if (button.text == Variants[index].qaraqalpaq) {
                    img_quiz.setImageResource(R.drawable.ic_baseline_true_24)
                    True++
                    img_quiz_true.setImageResource(0)
                    tv_quiz_true_count.text = True.toString()
                } else {
                    img_quiz.setImageResource(R.drawable.ic_baseline_false_24)
                    False++
                    img_quiz_false.setImageResource(0)
                    tv_quiz_false_count.text = False.toString()
                }
                Handler().postDelayed({
                    img_quiz.setImageResource(0)
                    img_quiz_true.setImageResource(R.drawable.ic_baseline_true_24)
                    img_quiz_false.setImageResource(R.drawable.ic_baseline_false_24)
                    start()
                }, 1000)
                if (False >= 10) {
                    var view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_over,null)
                    var dialog = AlertDialog.Builder(this).setView(view)
                        .setPositiveButton("Awa"){
                                dialog, which->
                                count = 1

                                True = 0
                                False = 0
                                tv_quiz_true_count.text = True.toString()
                                tv_quiz_false_count.text = False.toString()
                                start()
                        }
                        .setNegativeButton("Yaq"){
                                dialog, which ->
                                finish()
                        }
                        .show()

                }
                count++

            }

        }

    }

}