package com.alden.eggincubator.objects

import com.alden.eggincubator.models.EggDataValue

public class EggValueCreator() {
    var eggDataValue = EggDataValue()
    fun generateValue(param : String) : EggDataValue{
        when(param){
                "Ayam" -> eggDataValue = EggDataValue(21, 36,39)
                "Bebek" -> eggDataValue = EggDataValue(28, 36,39)
                "Entok" -> eggDataValue = EggDataValue(35, 33,39)
                "Puyuh" -> eggDataValue = EggDataValue(14, 33,39)
                else -> "null"
        }
        return  eggDataValue
    }


}