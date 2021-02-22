package com.alden.eggincubator.objects

import com.alden.eggincubator.models.CustomValueData

class CustomValueCallback() {
    var data = CustomValueData()
    fun DayPlus(dayData : Int){
        data.day = dayData
    }

}