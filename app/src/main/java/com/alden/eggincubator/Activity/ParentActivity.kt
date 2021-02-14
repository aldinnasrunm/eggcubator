package com.alden.eggincubator.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alden.eggincubator.R
import com.alden.eggincubator.adapter.ParentAdapter
import com.alden.eggincubator.databinding.ActivityParentBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.alden.eggincubator.fragments.SettingFragment

class ParentActivity : AppCompatActivity() {

    lateinit var binding : ActivityParentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    val mAdapter = ParentAdapter(supportFragmentManager)
        mAdapter.addFrag(DashboardFragment(),"Beranda")
        mAdapter.addFrag(SettingFragment(),"Pengaturan")
        binding.vpList.apply {
            adapter = mAdapter
            offscreenPageLimit = 1
        }
        binding.tabActionList.setupWithViewPager(binding.vpList)

    }
}

