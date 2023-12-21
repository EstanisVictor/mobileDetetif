package com.datetif.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datetif.model.QuaterEntity

class ListQuaterVM : ViewModel() {
    private var quaters: MutableLiveData<MutableList<QuaterEntity>> = MutableLiveData()

    fun loadQuaters(listQuaters: MutableList<QuaterEntity>) {
        quaters.value = listQuaters
    }

    fun quatersList(): MutableList<QuaterEntity>? {
        return quaters.value
    }
}

