package com.alden.eggincubator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alden.eggincubator.Activity.*
import com.alden.eggincubator.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val fbdb = FirebaseDatabase.getInstance()
    val settingRef = fbdb.getReference("SettingData")
    lateinit var settingListener : ValueEventListener
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnChange.setOnClickListener {
         startActivity(Intent(this, WelcomeActivity::class.java))
        }
        binding.btnOff.setOnClickListener {

        }

        binding.btnShowTemperature.setOnClickListener {
            startActivity(Intent(this, ParentActivity::class.java))
        }

        binding.btnOnBoarding.setOnClickListener{
            startActivity(Intent(this, OnBoardingActivity::class.java))
        }

        isReady()
    }

    private fun isReady(){
        settingListener = settingRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val isReset = snapshot.child("isReset").value.toString()
                val isStart = snapshot.child("isStart").value.toString()
                val stateReset = isReset == "1"
                val stateStart = isStart == "1"
                actionStartUp(stateReset, stateStart)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun actionStartUp(stateReset: Boolean, stateStart: Boolean) {
        if (!stateReset && stateStart){
            Toast.makeText(this, "!reset, start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ParentActivity::class.java))
        }else if (!stateReset && !stateStart){
            Toast.makeText(this, "!reset, !start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }else{
            Toast.makeText(this, "reset, start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

}