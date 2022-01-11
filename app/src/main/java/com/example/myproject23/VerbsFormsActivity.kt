package com.example.myproject23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.myproject23.Database.AppDatabase
import com.example.myproject23.Database.Verbs
import com.example.myproject23.Database.VerbsDao
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_verbs_forms.*

class VerbsFormsActivity : AppCompatActivity() {
    private lateinit var verbsDao: VerbsDao
    var count:Int = 1

    var True:Int = 0
    var False:Int = 0


    lateinit var list:MutableList<Verbs>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verbs_forms)
        verbsDao = AppDatabase.getInstance(this).userDao()
        list = verbsDao.getAll().toMutableList()
        start()
    }

    fun start() {
        lateinit var forms:MutableList<Verbs>
        forms = setForms()
        val index = setVariantsForm(forms)
        setVerbs(forms,index)
    }

    fun setForms():MutableList<Verbs> {
        val set = mutableSetOf<Int>()
        while(set.size<3){
            val random = (0 until list.size).random()
            set.add(random)
        }
        val forms = mutableListOf<Verbs>()
        if(!forms.isEmpty()) {
            forms.clear()
        }
        for (i in set){
            forms.add(list[i])
        }
        return forms
    }

    fun setVariantsForm(forms: MutableList<Verbs>): Int {
        val d = (0..2).random()
        tv_form_answer.text = forms[d].qaraqalpaq

        val setvar = mutableSetOf<Int>()
        setvar.add(d)
        while (setvar.size<3){
            val random = (0..2).random()
            setvar.add(random)
        }

        val setof = mutableSetOf<Int>()
        while (setof.size<6){
            val random = (0..5).random()
            setof.add(random)
        }
        val form = mutableListOf<TextView>()
        form.add(tv_form_A)
        form.add(tv_form_B)
        form.add(tv_form_C)
        form.add(tv_form_D)
        form.add(tv_form_E)
        form.add(tv_form_F)

        form[setof.elementAt(0)].text = forms[setvar.elementAt(0)].verb1
        form[setof.elementAt(1)].text = forms[setvar.elementAt(0)].verb2
        form[setof.elementAt(2)].text = forms[setvar.elementAt(0)].verb3
        form[setof.elementAt(3)].text = forms[setvar.elementAt(1)].verb1
        form[setof.elementAt(4)].text = forms[setvar.elementAt(1)].verb2
        form[setof.elementAt(5)].text = forms[setvar.elementAt(2)].verb3

        return d
    }

    fun checkWin(forms: MutableList<Verbs>,index: Int) {
        if(tv_form_1.text.toString().contains(forms[index].verb1)&&
            tv_form_2.text.toString().contains(forms[index].verb2)&&
            tv_form_3.text.toString().contains(forms[index].verb3)){
            img_form.setImageResource(R.drawable.ic_baseline_true_24)
            True++
            img_form_true.setImageResource(0)
            tv_form_true_count.text = True.toString()
        }else{
            img_form.setImageResource(R.drawable.ic_baseline_false_24)
            False++
            img_form_false.setImageResource(0)
            tv_form_false_count.text = False.toString()
        }
        Handler().postDelayed({
            img_form.setImageResource(0)
            img_form_true.setImageResource(R.drawable.ic_baseline_true_24)
            img_form_false.setImageResource(R.drawable.ic_baseline_false_24)
            start()
            Restart()
        },1000)
        if(False >= 10){
            var view = LayoutInflater.from(this).inflate(R.layout.activity_dialog_over,null)
            var dialog = AlertDialog.Builder(this).setView(view)
                .setPositiveButton("Awa"){
                    dialog, which->
                    Restart()
                    start()

                    True = 0
                    False = 0
                    img_form.setImageResource(0)
                    tv_form_false_count.text = False.toString()
                    tv_form_true_count.text = True.toString()
                }
                .setNegativeButton("Yaq"){
                    dialog, which ->
                    finish()
                }
                .show()
        }
    }

    fun setVerbs(forms: MutableList<Verbs>,index: Int) {
        tv_form_A.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_A, R.anim.anim_translate_a_verb1)
                2 -> Switch(tv_form_A, R.anim.anim_translate_a_verb2)
                3 -> {
                    Switch(tv_form_A, R.anim.anim_translate_a_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
        tv_form_B.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_B, R.anim.anim_translate_b_verb1)
                2 -> Switch(tv_form_B, R.anim.anim_translate_b_verb2)
                3 -> {
                    Switch(tv_form_B, R.anim.anim_translate_b_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
        tv_form_C.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_C, R.anim.anim_translate_c_verb1)
                2 -> Switch(tv_form_C, R.anim.anim_translate_c_verb2)
                3 -> {
                    Switch(tv_form_C, R.anim.anim_translate_c_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
        tv_form_D.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_D, R.anim.anim_translate_d_verb1)
                2 -> Switch(tv_form_D, R.anim.anim_translate_d_verb2)
                3 -> {
                    Switch(tv_form_D, R.anim.anim_translate_d_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
        tv_form_E.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_E, R.anim.anim_translate_e_verb1)
                2 -> Switch(tv_form_E, R.anim.anim_translate_e_verb2)
                3 -> {
                    Switch(tv_form_E, R.anim.anim_translate_e_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
        tv_form_F.setOnClickListener {
            img_form.setImageResource(0)
            when(count) {
                1 -> Switch(tv_form_F, R.anim.anim_translate_f_verb1)
                2 -> Switch(tv_form_F, R.anim.anim_translate_f_verb2)
                3 -> {
                    Switch(tv_form_F, R.anim.anim_translate_f_verb3)
                    Handler().postDelayed({
                        checkWin(forms,index)
                    },500)
                }
            }
        }
    }

    fun Restart() {
        tv_form_A.isVisible = true
        tv_form_B.isVisible = true
        tv_form_C.isVisible = true
        tv_form_D.isVisible = true
        tv_form_E.isVisible = true
        tv_form_F.isVisible = true

        tv_form_1.text = "1 verb"
        tv_form_2.text = "2 verb"
        tv_form_3.text = "3 verb"

        count = 1
    }

    fun Switch(variant: TextView, id: Int){
        when(count){
            1 -> {
                Anima(tv_form_1, variant, id)
            }
            2 -> {
                Anima(tv_form_2, variant, id)
            }
            3 -> {
                Anima(tv_form_3, variant, id)
            }
        }
    }

    fun Anima(verb: TextView,variant: TextView, id: Int) {
        val translate: Animation = AnimationUtils.loadAnimation(this, id)
        variant.startAnimation(translate)
        count++
        variant.isVisible = false
        Handler().postDelayed({
            verb.text = variant.text
        },500)
    }

}