package com.gmail.aitama90p.ds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataModel : ViewModel(){
    val idMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val nameMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val phoneMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}