package com.gramzin.gifless.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.gramzin.gifless.R
import com.gramzin.gifless.databinding.ActivityMainBinding
import com.gramzin.gifless.presentation.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initializeAdapter()
    }

    fun initializeAdapter(){
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, pos ->
            when(pos){
                0 -> tab.text = applicationContext.getString(R.string.top)
                1 -> tab.text = applicationContext.getString(R.string.programming)
            }
        }.attach()
    }
}