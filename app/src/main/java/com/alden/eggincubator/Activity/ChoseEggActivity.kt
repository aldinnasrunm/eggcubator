package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivityChoseEggBinding
import com.alden.eggincubator.objects.EggType

private const val TAG = "ChoseEggActivity"
class ChoseEggActivity : AppCompatActivity() {
    val eggData = EggType()
    lateinit var binding : ActivityChoseEggBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseEggBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData(eggData.getEgg())
        initButton()
        binding.btnChoseEggNext.setOnClickListener {
           if (!eggData.getEgg().equals("")){
               startActivity(Intent(this, PrepareActivity::class.java))
           }else{
               Toast.makeText(this, "please chose egg", Toast.LENGTH_SHORT).show()
           }
        }
    }

    private fun initData(data :String) {
        when(data){
            "Chicken" -> binding.rbChickenEgg.isChecked = true
            "Duck" -> binding.rbDuckEgg.isChecked = true
            "Goose" -> binding.rbGooseEgg.isChecked = true
            "Quail" -> binding.rbQuailEgg.isChecked = true
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
            eggData.setEgg("Chicken")
        }
        binding.rbDuckEgg.setOnClickListener {
            binding.rbCustomEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            eggData.setEgg("Duck")
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
            eggData.setEgg("Goose")
        }
        binding.rbQuailEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbCustomEgg.isChecked = false
            eggData.setEgg("Quail")
        }

    }

}