package com.alden.eggincubator.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alden.eggincubator.R
import com.alden.eggincubator.adapter.ParentAdapter
import com.alden.eggincubator.databinding.ActivityParentBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.alden.eggincubator.fragments.SettingFragment
import com.alden.eggincubator.models.CompleteTriggerData


class ParentActivity : AppCompatActivity() {
    lateinit var vLivedata : CompleteTriggerData
    lateinit var binding : ActivityParentBinding
    private val tabIcons = intArrayOf(
        R.drawable.ic_logo_power,
        R.drawable.ic_logo_reboot
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vLivedata= CompleteTriggerData()
    val mAdapter = ParentAdapter(supportFragmentManager)
        mAdapter.addFrag(DashboardFragment(), "Beranda")
        mAdapter.addFrag(SettingFragment(), "Pengaturan")
        binding.vpList.apply {
            adapter = mAdapter
            offscreenPageLimit = 1
        }
        binding.tabActionList.setupWithViewPager(binding.vpList)
        binding.tabActionList.getTabAt(0)?.setIcon(tabIcons[0])
        binding.tabActionList.getTabAt(1)?.setIcon(tabIcons[1])
//        vLivedata.status.observe()

    }

}

