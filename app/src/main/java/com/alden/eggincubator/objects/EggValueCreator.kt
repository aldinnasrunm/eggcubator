package com.alden.eggincubator.objects

import com.alden.eggincubator.models.EggDataValue

public class EggValueCreator() {
    var eggDataValue = EggDataValue()
    fun generateValue(param : String) : EggDataValue{
        when(param){
                "Chicken" -> eggDataValue = EggDataValue(21, 36,39)
                "Duck" -> eggDataValue = EggDataValue(28, 36,39)
                "Goose" -> eggDataValue = EggDataValue(35, 33,36)
                "Quail" -> eggDataValue = EggDataValue(14, 33,36)
                else -> "null"
        }
        return  eggDataValue
    }


}