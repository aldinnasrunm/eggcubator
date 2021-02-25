package com.alden.eggincubator.objects

public class EggType {
    var eggType: String? = ""

    public fun setEgg(data: String) {
        eggType = data
    }

    public fun getEgg(): String {
        return eggType!!
    }
}