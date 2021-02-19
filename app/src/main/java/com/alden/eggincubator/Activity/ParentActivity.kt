package com.alden.eggincubator.Activity

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alden.eggincubator.R
import com.alden.eggincubator.adapter.ParentAdapter
import com.alden.eggincubator.databinding.ActivityParentBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.alden.eggincubator.fragments.SettingFragment
import com.alden.eggincubator.models.CompleteTriggerData
import com.google.android.material.tabs.TabLayout


@Suppress("DEPRECATION")
class ParentActivity : AppCompatActivity() {
    lateinit var vLivedata : CompleteTriggerData
    lateinit var binding : ActivityParentBinding
    private val tabIcons = intArrayOf(
        R.drawable.ic_gray_home,
        R.drawable.ic_gray_setting
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
        binding.tabActionList.tabRippleColor = null
        binding.tabActionList.getTabAt(0)?.setIcon(tabIcons[0])
        binding.tabActionList.getTabAt(1)?.setIcon(tabIcons[1])
        binding.tabActionList.setOnTabSelectedListener(
            object : TabLayout.ViewPagerOnTabSelectedListener(binding.vpList) {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    super.onTabSelected(tab)
                    val tabIconColor = ContextCompat.getColor(this@ParentActivity, R.color.greenMain)
                    tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    super.onTabUnselected(tab)
                    val tabIconColor =
                        ContextCompat.getColor(this@ParentActivity, R.color.colorGrey)
                    tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    super.onTabReselected(tab)
                }
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        binding.vpList.adapter = null
    }
}

