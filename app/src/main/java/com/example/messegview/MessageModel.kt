package com.example.messegview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageModel: ViewModel() {
    fun makeDataRequest(list: Map<String, List<String>>) {
        _liveData.postValue(list)
    }

    private val _liveData = MutableLiveData<Map<String, List<String>>>()
    val liveData: MutableLiveData<Map<String, List<String>>>
        get() = _liveData
}