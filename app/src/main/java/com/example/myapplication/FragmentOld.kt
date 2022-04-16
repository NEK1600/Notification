package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.adapters.AdapterNotify
import com.example.myapplication.adapters.AdapterNotifyOld
import com.example.myapplication.databinding.FragmentOldBinding
import com.example.myapplication.db.model.ModelNotify


class FragmentOld : Fragment() {
    lateinit var binding: FragmentOldBinding
    lateinit var rcViewOld: RecyclerView
    lateinit var adapter: AdapterNotifyOld
    //var listOld = emptyList<ModelNotify>()
    var listG = mutableListOf<ModelNotify>()
    var listG2 = mutableListOf<ModelNotify>()
    lateinit var editActivity: EditActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOldBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        rcViewOld = binding.rvOld
        adapter = AdapterNotifyOld()
        editActivity = EditActivity()
        rcViewOld.adapter = adapter
        rcViewOld.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        viewModel.getAllNotify().observe(viewLifecycleOwner
        ) { listNotify2 ->
            listG = listNotify2.toMutableList()
            listG2.clear()
            for (i in listG) {
                if (i.timeMills < editActivity.getDateTime3()) {
                   listG2.add(i)
                    //Log.d("fsd",  listNotify2.toMutableList().add(i).toString())
                }
            }

            adapter.update(listG2)
        }


        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcViewOld)

    }

    private fun getSwapMg(): ItemTouchHelper {
        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        return ItemTouchHelper(object : ItemTouchHelper
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

                var inter = listG.get(adapter.posit())
                viewModel.deleteItem(inter)

            }
        })
    }


    companion object {
        fun newInstance() = FragmentOld()
    }

}