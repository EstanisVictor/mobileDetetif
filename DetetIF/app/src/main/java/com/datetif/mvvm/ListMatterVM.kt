package com.datetif.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datetif.model.Matter

class ListMatterVM : ViewModel() {
    private var matters: MutableLiveData<MutableList<Matter>> = MutableLiveData()

    fun loadMatters(listMatters: MutableList<Matter>) {
        matters.value = listMatters
    }

    fun mattersList(): MutableList<Matter>? {
        return matters.value
    }
}