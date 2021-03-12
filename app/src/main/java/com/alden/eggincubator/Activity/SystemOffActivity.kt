package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivitySystemOffBinding
import com.alden.eggincubator.fragments.DashboardFragment
import com.google.firebase.database.FirebaseDatabase

class SystemOffActivity : AppCompatActivity() {
    lateinit var binding: ActivitySystemOffBinding
    val fbdb = FirebaseDatabase.getInstance()
    val ref = fbdb.getReference("SettingData")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySystemOffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOnSystem.setOnClickListener {
            ref.child("isShutdown").setValue(0).addOnSuccessListener {
//                startActivity(Intent(this, ParentActivity::class.java))
            }
            getFinish()
        }

    }

    private fun getFinish(){
        this.finish()
    }

    override fun onBackPressed() {

    }
}