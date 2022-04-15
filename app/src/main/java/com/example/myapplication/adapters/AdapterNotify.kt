package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FragmentNew
import com.example.myapplication.R
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.db.model.ModelNotify

class AdapterNotify : RecyclerView.Adapter<AdapterNotify.AdapterViewHolder>() {
    var listNotify = emptyList<ModelNotify>()
    var posit = 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notify_element,parent, false)
        return AdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.textView.text = listNotify[position].title
        holder.textViewTimePerson.text = listNotify[position].timePerson

        /*holder.deleteButton.setOnClickListener{
            viewModel.delete.listNotify.get(position)
        }*/
        posit=position

    }
    fun posit():Int{
        return posit
    }

    override fun getItemCount(): Int {
        return listNotify.size
    }



    class AdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = itemView.findViewById(R.id.tx)
        val textViewTimePerson: TextView = itemView.findViewById(R.id.txTimePerson)
    }


    fun update(list:List<ModelNotify>){
        listNotify=list
        notifyDataSetChanged()
    }

    fun removeItem(pos:Int){


        listNotify.toMutableList().removeAt(pos)
        notifyItemRangeChanged(0,listNotify.size)
        notifyItemRemoved(pos)
    }


}



































