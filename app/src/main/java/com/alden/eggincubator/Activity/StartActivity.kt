package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivityStartBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StartActivity : AppCompatActivity() {
    lateinit var binding: ActivityStartBinding
    val fbdo = FirebaseDatabase.getInstance()
    lateinit var eggEvenListener : ValueEventListener
    val eggRef = fbdo.getReference("EggData")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isAnimationVisible(true)
        initDay()
        binding.btnStartNext.setOnClickListener {
            startActivity(Intent(this, ParentActivity::class.java))
            finish()
        }

    }

    private fun initDay() {
        eggEvenListener = eggRef.child("day").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.value.toString()
                initView(data)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun initView(param : String){
        binding.tvSubTitleStart.text = "Telur kamu akan menetas dalam $param hari lagi :)"
        isAnimationVisible(false)
    }

    private fun isAnimationVisible(isVisible : Boolean){
        if (isVisible){
            binding.llAnimation.visibility = View.VISIBLE
            binding.animationLoading.playAnimation()
        }else{
            binding.llAnimation.visibility = View.GONE
            binding.animationLoading.cancelAnimation()
        }
    }
}