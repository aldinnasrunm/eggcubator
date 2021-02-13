package com.alden.eggincubator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alden.eggincubator.Activity.OnBoardingActivity
import com.alden.eggincubator.Activity.ShowTemperatureActivity
import com.alden.eggincubator.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val database = FirebaseDatabase.getInstance()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnChange.setOnClickListener {
            val refDb = database.getReference("Variable").child("Value")
                refDb.setValue(1)
        }
        binding.btnOff.setOnClickListener {
            val refDb = database.getReference("Variable").child("Value")
                refDb.setValue(0)
        }

        binding.btnShowTemperature.setOnClickListener {
            startActivity(Intent(this, ShowTemperatureActivity::class.java))
        }

        binding.btnOnBoarding.setOnClickListener{
            startActivity(Intent(this, OnBoardingActivity::class.java))
        }

    }
   private fun getStatus(){
       var value = ""
       val refDb = database.getReference("Variable").child("Value")
       refDb.addValueEventListener(object : ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
               value = snapshot.getValue(String::class.java).toString()
               binding.tvStatus.text = value
           }

           override fun onCancelled(error: DatabaseError) {
               Log.w(TAG, "Failed to read value.", error.toException())
           }

       })
   }

}