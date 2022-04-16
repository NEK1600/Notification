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
    lateinit var rcViewNew: RecyclerView
    lateinit var adapter: AdapterNotify
    lateinit var currentNotify: ModelNotify
    var listL = mutableListOf<ModelNotify>()
    var listL2 = mutableListOf<ModelNotify>()
    lateinit var editActivityL: EditActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBinding.inflate(layoutInflater, container, false)
        init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun init(){
        rcViewNew = binding.rvNew
        adapter = AdapterNotify()
        editActivityL = EditActivity()
        rcViewNew.adapter = adapter
        rcViewNew.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val viewModel = ViewModelProvider(this).get(ViewModelNotify::class.java)

        viewModel.getAllNotify().observe(viewLifecycleOwner) { listNotify ->
            listL = listNotify.toMutableList()
            listL2.clear()
            for (i in listL) {
                if (i.timeMills > editActivityL.getDateTime3()) {
                    listL2.add(i)
                    //Log.d("fsd",  listNotify2.toMutableList().add(i).toString())
                }
            }

            adapter.update(listL2)

        }


        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(rcViewNew)

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


                var inter = listL2.get(adapter.posit())
                //var inter = viewModel.getAllNotify().value?.get(adapter.posit())!!
                viewModel.deleteItem(inter)

            }
        })
    }

    companion object {


        fun newInstance() = FragmentNew()
    }



}















