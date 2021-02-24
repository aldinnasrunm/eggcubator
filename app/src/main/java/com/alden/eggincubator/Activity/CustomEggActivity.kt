package com.alden.eggincubator.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alden.eggincubator.databinding.ActivityCustomEggBinding

class CustomEggActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomEggBinding
    var maxTempValue: Int = 30
    var dayValue: Int = 10
    var minTempValue: Int = 28
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomEggBinding.inflate(layoutInflater)
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
        binding.btnDecreaseMaxTemp.setOnClickListener {
            maxTempIncrease(true, 50)
        }
        binding.btnDecreaseMaxTemp.setOnClickListener {
            maxTempIncrease(false, 30)
        }

        setView()
    }

    fun setView() {
        binding.tvCustomDayValue.text = dayValue.toString()
        binding.tvMinTempValue.text = minTempValue.toString()
        binding.tvMaxTempValue.text = maxTempValue.toString()
    }

    fun dayIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (dayValue <= anchor) {
                dayValue = dayValue + 1
            }
        } else {
            if (dayValue >= anchor) {
                dayValue = dayValue - 1
            }
        }
        setView()
    }

    fun minTempIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (minTempValue <= anchor) {
                minTempValue = minTempValue + 1
            }
        } else {
            if (minTempValue >= anchor) {
                minTempValue = minTempValue - 1
            }
        }
        setView()

    }

    fun maxTempIncrease(isIncrease: Boolean, anchor: Int) {
        if (isIncrease) {
            if (maxTempValue <= anchor) {
                maxTempValue = maxTempValue + 1
            }
        } else {
            if (maxTempValue >= anchor) {
                maxTempValue = maxTempValue - 1
            }
        }
        setView()
    }

}