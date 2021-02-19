package com.alden.eggincubator.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompleteTriggerData : ViewModel() {
    val status: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}