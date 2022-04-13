package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentList = listOf(FragmentNew.newInstance(), FragmentOld.newInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragListener()
        initFragNew()
    }

    fun fragListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragmentList[tab?.position!!])
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    fun initFragNew() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragmentList[0])
            .commit()
    }


    fun onClickAdd(view: View) {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }

}