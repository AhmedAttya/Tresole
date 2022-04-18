package com.tresole.tresole.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item

class StoreViewModel : ViewModel() {
    var repository=com.tresole.tresole.repository.Repository()
    fun getnewandhot(): MutableLiveData<MutableList<Item>> {
        return repository.getnewnhot()
    }
    fun getdealoftheday() : MutableLiveData<MutableList<Item>> {
        return repository.getdealoftheday()
    }

    fun getfrequentlybought(): MutableLiveData<MutableList<Item>> {
        return repository.getfrequentlybought()
    }

    fun getrecommended(): MutableLiveData<MutableList<Item>> {
        return repository.getrecommended()
    }
    fun onitemclicked(Item :Item){
        Currentitem.currentitem= Item
    }
}