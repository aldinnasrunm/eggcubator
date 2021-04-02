package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivityChoseEggBinding
import com.alden.eggincubator.databinding.LayoutCustomEggSettingBinding
import com.alden.eggincubator.objects.EggType
import com.alden.eggincubator.objects.EggValueCreator
import com.google.firebase.database.FirebaseDatabase

private const val TAG = "ChoseEggActivity"
class ChoseEggActivity : AppCompatActivity() {
    val eggData = EggType()
    val fbdb = FirebaseDatabase.getInstance()
    lateinit var binding : ActivityChoseEggBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseEggBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData(eggData.getEgg())
        initButton()

        binding.btnChoseEggNext.setOnClickListener {
           if (!eggData.getEgg().equals("")){
               if (eggData.getEgg().equals("Custom")){
                   startActivity(Intent(this, ManualSettingActivity::class.java))
               }else{
                   isAnimationVisible(true)
                   updateEggType(eggData.getEgg())
//                   startActivity(Intent(this, PrepareActivity::class.java))
               }
           }else{
               Toast.makeText(this, "please chose egg", Toast.LENGTH_SHORT).show()
           }
        }

    }

    //check last data
    private fun initData(data :String) {
        when(data){
            "Ayam" -> binding.rbChickenEgg.isChecked = true
            "Bebek" -> binding.rbDuckEgg.isChecked = true
            "Entok" -> binding.rbGooseEgg.isChecked = true
            "Puyuh" -> binding.rbQuailEgg.isChecked = true
            "Custom" -> binding.rbCustomEgg.isChecked = true
            else -> "a"
        }
    }

    private fun initButton() {
        binding.rbChickenEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            binding.rbCustomEgg.isChecked = false
            eggData.setEgg("Ayam")
        }
        binding.rbDuckEgg.setOnClickListener {
            binding.rbCustomEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            eggData.setEgg("Bebek")
        }
        binding.rbCustomEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            eggData.setEgg("Custom")
        }
        binding.rbGooseEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            eggData.setEgg("Entok")
        }
        binding.rbQuailEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbCustomEgg.isChecked = false
            eggData.setEgg("Puyuh")
        }

    }


    private fun updateEggType(param : String){
        val eggValueCreator = EggValueCreator()

        val data = eggValueCreator.generateValue(param)
        val revEgg = fbdb.getReference("EggData").setValue(data)
        val rev = fbdb.getReference("FirebaseIOT").child("eggName").setValue(param)
        revEgg.addOnSuccessListener {
            isAnimationVisible(false)
            startActivity(Intent(this, PrepareActivity::class.java))
            finish()
        }

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