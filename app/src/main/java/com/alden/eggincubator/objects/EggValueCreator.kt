package com.alden.eggincubator.objects

import com.alden.eggincubator.models.EggDataValue

public class EggValueCreator() {
    var eggDataValue = EggDataValue()
    fun generateValue(param : String) : EggDataValue{
        when(param){
                "Chicken" -> eggDataValue = EggDataValue(21, 36,39)
                "Duck" -> eggDataValue = EggDataValue(28, 36,39)
                "Goose" -> eggDataValue = EggDataValue(35, 33,39)
                "Quail" -> eggDataValue = EggDataValue(14, 33,39)
                else -> "null"
        }
        return  eggDataValue
    }


}