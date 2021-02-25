package com.alden.eggincubator.objects

import com.alden.eggincubator.models.EggDataValue

public class EggValueCreator() {
    var eggDataValue = EggDataValue()
    fun generateValue(param : String) : EggDataValue{
        when(param){
                "Chicken" -> eggDataValue = EggDataValue(21, 28,35)
                "Duck" -> eggDataValue = EggDataValue(21, 28,35)
                "Goose" -> eggDataValue = EggDataValue(21, 28,35)
                "Quail" -> eggDataValue = EggDataValue(21, 28,35)
                else -> "null"
        }
        return  eggDataValue
    }


}