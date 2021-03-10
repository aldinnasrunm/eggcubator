package com.alden.eggincubator

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.alden.eggincubator.Activity.*
import com.alden.eggincubator.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val fbdb = FirebaseDatabase.getInstance()
    val settingRef = fbdb.getReference("SettingData")
    lateinit var settingListener : ValueEventListener
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(1)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT)
        }

        GlobalScope.launch {
            delay(2000)
            isReady()
            finish()
        }

//        binding.btnChange.setOnClickListener {
//         startActivity(Intent(this, WelcomeActivity::class.java))
//        }
//        binding.btnOff.setOnClickListener {
//
//        }
//
//        binding.btnShowTemperature.setOnClickListener {
//            startActivity(Intent(this, ParentActivity::class.java))
//        }
//
//        binding.btnOnBoarding.setOnClickListener{
//            startActivity(Intent(this, OnBoardingActivity::class.java))
//        }


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
        }else if (stateReset && stateStart){
            Toast.makeText(this, "!reset, !start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }else if (stateReset && !stateStart){
            Toast.makeText(this, "!reset, !start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }else{
            Toast.makeText(this, "reset, start", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

}