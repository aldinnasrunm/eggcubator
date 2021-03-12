package com.alden.eggincubator.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alden.eggincubator.databinding.ActivityStartBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime

class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding
    val fbdb = FirebaseDatabase.getInstance()
    lateinit var eggEvenListener : ValueEventListener
    val eggRef = fbdb.getReference("EggData")
    val waterInterfal :Long = 4
    val ref = fbdb.getReference("FirebaseIOT")
    val refSetting = fbdb.getReference("SettingData")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isAnimationVisible(true)
        initDay()
        binding.btnStartNext.setOnClickListener {
            updateMightyDay()
            val intent = Intent(applicationContext, ParentActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateMightyDay() {
        val current = LocalDateTime.now()
        ref.child("mightyDay").setValue(current.toString())
        ref.child("waterDay").setValue(current.plusDays(waterInterfal).toString())
        refSetting.child("isStart").setValue(1)
        refSetting.child("isReset").setValue(0)
    }

    private fun initDay() {
        eggEvenListener = eggRef.child("day").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value.toString()
                initView(data)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun initView(param: String){
        binding.tvSubTitleStart.text = "Telur kamu akan menetas setelah $param hari waktu inkubasi :)"
        isAnimationVisible(false)
    }

    private fun isAnimationVisible(isVisible: Boolean){
        if (isVisible){
            binding.llAnimation.visibility = View.VISIBLE
            binding.animationLoading.playAnimation()
        }else{
            binding.llAnimation.visibility = View.GONE
            binding.animationLoading.cancelAnimation()
        }
    }
}