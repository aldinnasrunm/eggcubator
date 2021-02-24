package com.alden.eggincubator.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alden.eggincubator.databinding.ActivityManualSettingBinding

class ManualSettingActivity : AppCompatActivity() {
    lateinit var binding : ActivityManualSettingBinding
    var maxTempValue: Int = 30
    var dayValue: Int = 10
    var minTempValue: Int = 28
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //day
        binding.btnIncreaseDay.setOnClickListener {
            dayIncrease(true, 40)
        }
        binding.btnDecreaseDay.setOnClickListener {
            dayIncrease(false, 9)
        }
        //min Temperature
        binding.btnIncreaseMinTemp.setOnClickListener {
            minTempIncrease(true, 30)
        }
        binding.btnDecreaseMinTemp.setOnClickListener {
            minTempIncrease(false, 25)
        }
        //max Temperature
        binding.btnIncreaseMaxTemp.setOnClickListener {
            maxTempIncrease(true, 50)
        }
        binding.btnDecreaseMaxTemp.setOnClickListener {
            maxTempIncrease(false, 30)
        }
        binding.btnManualSettingNext.setOnClickListener {
            nextActivity()
        }
        setView()
    }

    private fun nextActivity() {
        //ke aktivity selanjutnya

        startActivity(Intent(this, PrepareActivity::class.java))
        finish()
    }

    fun setView() {
        binding.tvCustomDayValue.text = dayValue.toString()
        binding.tvMinTempValue.text = minTempValue.toString()
        binding.tvMaxTempValue.text = maxTempValue.toString()
    }

    fun dayIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (dayValue < anchor) {
                dayValue = dayValue + 1
            }
        } else {
            if (dayValue > anchor) {
                dayValue = dayValue - 1
            }
        }
        setView()
    }

    fun minTempIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (minTempValue < anchor) {
                minTempValue = minTempValue + 1
            }
        } else {
            if (minTempValue > anchor) {
                minTempValue = minTempValue - 1
            }
        }
        setView()

    }

    fun maxTempIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (maxTempValue < anchor) {
                maxTempValue = maxTempValue + 1
            }
        } else {
            if (maxTempValue > anchor) {
                maxTempValue = maxTempValue - 1
            }
        }
        setView()
    }
}