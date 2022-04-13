package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ViewModel.ViewModelNotify
import com.example.myapplication.adapters.AdapterNotify
import com.example.myapplication.databinding.FragmentNewBinding


class FragmentNew : Fragment() {
    lateinit var binding: FragmentNewBinding
    lateinit var rcView: RecyclerView
    lateinit var adapter: AdapterNotify

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
       //viewModel.initDatabase()

        viewModel.getAllNotify().observe(viewLifecycleOwner, {listNotify->
            adapter.update(listNotify.asReversed())}
        )


    }

    companion object {

        fun newInstance() = FragmentNew()
    }


}
