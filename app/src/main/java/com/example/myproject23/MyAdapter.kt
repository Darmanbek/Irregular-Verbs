package com.example.myproject22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject23.Database.Verbs
import com.example.myproject23.IrregularVerbsActivity
import com.example.myproject23.R
import kotlinx.android.synthetic.main.activity_view.view.*

class MyAdapter(var activity: IrregularVerbsActivity):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun populateModel(verbs: Verbs, activity: IrregularVerbsActivity) {
            itemView.tv_view_qaraqalpaq.text = verbs.qaraqalpaq
            itemView.tv_view_verb1.text = verbs.verb1
            itemView.tv_view_verb2.text = verbs.verb2
            itemView.tv_view_verb3.text = verbs.verb3

            itemView.setOnClickListener {
                activity.selectVerb(verbs)
            }
        }

    }

    var list:MutableList<Verbs> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.populateModel(list[position],activity)
    }

    override fun getItemCount(): Int = list.size

}