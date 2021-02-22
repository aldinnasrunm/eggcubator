package com.alden.eggincubator.objects

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alden.eggincubator.models.CustomValueData

private const val TAG = "CustomValueCallback"

class CustomValueCallback() : ViewModel() {
    private val _day = MutableLiveData(0)
    private val _tempMin = MutableLiveData(29)
    private val _tempMax = MutableLiveData(40)
    private val _humMin = MutableLiveData(30)
    private val _humMax = MutableLiveData(90)
    val day: LiveData<Int> = _day
    val tempMin: LiveData<Int> = _tempMin
    val tempMax: LiveData<Int> = _tempMax
    val humMin: LiveData<Int> = _humMin
    val humMax: LiveData<Int> = _humMax
    val dayes = "9"
    lateinit var anchor: LiveData<String>

    fun onIncrease(_anchor: String) {
        anchor = MutableLiveData(_anchor)
        increase()
    }

    fun onIncreaseDay() {
        anchor = MutableLiveData("day")
        increase()
    }

    fun increase() = Transformations.map(anchor) {
        when {
            it.equals("day") -> (_day.value ?: 0) + 1
            it.equals("tempMin") -> (_tempMin.value ?: 0) + 1
            it.equals("tempMax") -> (_tempMax.value ?: 0) + 1
            it.equals("humMin") -> (_humMin.value ?: 0) + 1
            it.equals("humMax") -> (_humMax.value ?: 0) + 1
            else -> Log.d(TAG, "onIncrease: failure")
        }
    }


}