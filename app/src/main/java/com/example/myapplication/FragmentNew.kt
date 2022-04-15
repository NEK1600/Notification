package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.adapters.AdapterNotify
import com.example.myapplication.databinding.FragmentNewBinding
import com.example.myapplication.db.dao.DaoNotify
import com.example.myapplication.db.model.ModelNotify


class FragmentNew : Fragment() {
    lateinit var binding: FragmentNewBinding
    lateinit var rcView: RecyclerView
    lateinit var adapter: AdapterNotify
    lateinit var currentNotify: ModelNotify


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(layoutInflater, container, false)
        init()

        return binding.root
    }

    private fun init(){
        rcView = binding.rvNew
        adapter = AdapterNotify()
        rcView.adapter = adapter
        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        viewModel.getAllNotify().observe(viewLifecycleOwner, {listNotify->
            adapter.update(listNotify)}
        )
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcView)

    }
    private fun getSwapMg(): ItemTouchHelper{
        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        return ItemTouchHelper(object :ItemTouchHelper
        .SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                adapter.removeItem(viewHolder.adapterPosition)


                var inter = viewModel.getAllNotify().value?.get(adapter.posit())!!
                viewModel.deleteItem(inter)

            }
        })
    }

    companion object {


        fun newInstance() = FragmentNew()
    }



}















