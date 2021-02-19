package com.alden.eggincubator.objects

public class EggType {
    var eggType: String? = null

    public fun setEgg(data: String) {
        eggType = data
    }

    public fun getEgg(): String {
        return eggType!!
    }
}