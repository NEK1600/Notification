package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.db.model.ModelNotify

class AdapterNotify : RecyclerView.Adapter<AdapterNotify.AdapterViewHolder>() {
    var listNotify = emptyList<ModelNotify>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notify_element,parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.textView.text = listNotify[position].title
    }

    override fun getItemCount(): Int {
        return listNotify.size
    }



    class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = itemView.findViewById(R.id.tx)
    }


    fun update(list:List<ModelNotify>){
        listNotify=list
        notifyDataSetChanged()
    }
}