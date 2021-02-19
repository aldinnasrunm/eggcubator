package com.alden.eggincubator.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alden.eggincubator.R
import com.alden.eggincubator.databinding.ActivityChoseEggBinding

class ChoseEggActivity : AppCompatActivity() {
    lateinit var binding : ActivityChoseEggBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoseEggBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButton()
    }

    private fun initButton() {
        binding.rbChickenEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
            binding.rbCustomEgg.isChecked = false
        }
        binding.rbDuckEgg.setOnClickListener {
            binding.rbCustomEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
        }
        binding.rbCustomEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
        }
        binding.rbGooseEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbQuailEgg.isChecked = false
        }
        binding.rbQuailEgg.setOnClickListener {
            binding.rbDuckEgg.isChecked = false
            binding.rbChickenEgg.isChecked = false
            binding.rbGooseEgg.isChecked = false
            binding.rbCustomEgg.isChecked = false
        }
    }
}