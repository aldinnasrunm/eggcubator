package com.alden.eggincubator.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alden.eggincubator.databinding.ActivityShowTemperatureBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private const val TAG = "ShowTemperatureActivity"
class ShowTemperatureActivity : AppCompatActivity() {
    lateinit var binding : ActivityShowTemperatureBinding
    val database = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val refHum = database.getReference("FirebaseIOT").child("humidity")
        val refTemp = database.getReference("FirebaseIOT").child("temperature")

        refHum.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var hum = snapshot.value.toString()
                binding.tvHumidity.text = "$hum %"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })

        refTemp.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var temp = snapshot.value.toString()
                binding.tvTemperature.text = "$temp deg"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())

            }

        })
    }


}