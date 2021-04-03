package com.alden.eggincubator.Activity
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alden.eggincubator.R
import com.alden.eggincubator.adapter.ParentAdapter
import com.alden.eggincubator.databinding.ActivityParentBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.alden.eggincubator.fragments.SettingFragment
import com.alden.eggincubator.models.CompleteTriggerData
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Suppress("DEPRECATION")

class ParentActivity : AppCompatActivity() {
    lateinit var completeTriggerData: CompleteTriggerData
    lateinit var binding : ActivityParentBinding
    val fbdb = FirebaseDatabase.getInstance()
    val eggRef = fbdb.getReference("SettingData")
    lateinit var eggListener : ValueEventListener
    private val tabIcons = intArrayOf(
        R.drawable.ic_gray_home,
        R.drawable.ic_gray_setting
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chekSystemOff()


    }

    private fun chekSystemOff() {
        eggListener = eggRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var data = snapshot.child("isShutdown").value.toString()
                if (data.equals("1")){

//                    finishActivity(0)
                    startActivity(Intent(this@ParentActivity, SystemOffActivity::class.java))
                    finish()
                }else{
                    initResource()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

//        eggRef.removeEventListener(eggListener)
    }

    private fun initResource(){
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
                    val tabIconColor = ContextCompat.getColor(
                        this@ParentActivity,
                        R.color.greenMain
                    )
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        Toast.makeText(this, "Back pressed", Toast.LENGTH_SHORT).show()

    }

    override fun onRestart() {
        super.onRestart()
        chekSystemOff()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.vpList.adapter = null
    }
}



