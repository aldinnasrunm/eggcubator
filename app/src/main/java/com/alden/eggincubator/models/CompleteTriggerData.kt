package com.alden.eggincubator.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class CompleteTriggerData : ViewModel() {
    var isUpdate = MutableLiveData<Boolean>()

    fun setIsUpdate(proccess: Boolean?) {
        isUpdate.value = proccess!!
    }

}