package com.alden.eggincubator.Activity

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.alden.eggincubator.databinding.ActivityShowTemperatureBinding
import com.alden.eggincubator.databinding.LayoutShowRemainDayBinding
import com.alden.eggincubator.models.RTDBDataClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val TAG = "ShowTemperatureActivity"
class ShowTemperatureActivity : AppCompatActivity() {
    lateinit var binding : ActivityShowTemperatureBinding
    val database = FirebaseDatabase.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.llAnimation.visibility = View.VISIBLE

        /**
         * animation link https://lottiefiles.com/29847-owl-breaking-egg
         * https://lottiefiles.com/27777-chiki-chik
         *
         * list ToDo : tambahkan shimmer -> done
         *
         * */

        theData()
        val refHum = database.getReference("FirebaseIOT").child("humidity")
        val refTemp = database.getReference("FirebaseIOT").child("temperature")

    }

    private fun initAnimation() {
        binding.animationEggCrack.cancelAnimation()
        binding.llAnimation.visibility = View.GONE
    }


    private fun theData(){
        val ref = database.getReference("FirebaseIOT")
        ref.addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.d(TAG, "onDataChange: " + snapshot.value)
                var dataH = snapshot.child("humidity").value.toString()
                var dataT = snapshot.child("temperature").value.toString()
                var dataL = snapshot.child("led").value.toString()

                var rtdbData = RTDBDataClass(
                    dataT,
                    dataH,
                    dataL,
                    "0","0"
                )
                Log.d(TAG, "onDataChange: $rtdbData")

                inidView(rtdbData)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "onCancelled: ", error.toException())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun inidView(rtdbDataClass: RTDBDataClass){
        initAnimation()
//        val formatter = DateTimeFormatter.ofPattern("H:m:ss")
//        val current : LocalDateTime = LocalDateTime.now()
//        val formatted = current.format(formatter)
//        binding.tvLastUpdate.text = "Last Update : $formatted"
//        binding.tvTemperature.text = rtdbDataClass.temperature + " \u2103"
//        binding.tvHumidity.text = rtdbDataClass.humidity + "%"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d(TAG, "onBackPressed: activity finished")
        finish()
    }
}