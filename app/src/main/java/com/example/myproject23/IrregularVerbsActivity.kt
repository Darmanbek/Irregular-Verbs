package com.example.myproject23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.myproject22.MyAdapter
import com.example.myproject23.Database.AppDatabase
import com.example.myproject23.Database.Verbs
import com.example.myproject23.Database.VerbsDao
import kotlinx.android.synthetic.main.activity_dialog_info.view.*
import kotlinx.android.synthetic.main.activity_irregular_verbs.*

class IrregularVerbsActivity : AppCompatActivity() {
    var myadapter = MyAdapter(this)
    private lateinit var verbsDao: VerbsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_irregular_verbs)
        rv_irregular_verbs.adapter = myadapter
        verbsDao = AppDatabase.getInstance(this).userDao()
        myadapter.list = verbsDao.getAll().toMutableList()

        et_irregular_verbs.addTextChangedListener{
            if(it.toString().equals("exit")){
                finish()
            }
            myadapter.list = verbsDao.getData("${it}%").toMutableList()
        }
    }

    fun selectVerb(verbs: Verbs) {
        var view =LayoutInflater.from(this).inflate(R.layout.activity_dialog_info,null)
        view.tv_dialog_qaraqalpaq_text.text = verbs.qaraqalpaq
        view.tv_dialog_verb_text.text = verbs.verb1
        view.tv_dialog_past_tense_text.text = verbs.verb2
        view.tv_dialog_past_participle_text.text = verbs.verb3
        var dialog = AlertDialog.Builder(this).setView(view)
            .setPositiveButton("OK"){
                dialog,which->
                dialog.dismiss()
            }
            .show()
    }

}