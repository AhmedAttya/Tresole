package com.tresole.tresole.main

import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item

class StoreViewModel : ViewModel() {
    var repository=com.tresole.tresole.repository.Repository()
    fun getnewandhot(): MutableList<Item>{
        return repository.getnewnhot()
    }
    fun getdealoftheday() :MutableList<Item>{
        return repository.getdealoftheday()
    }

    fun getfrequentlybought(): MutableList<Item> {
        return repository.getfrequentlybought()
    }

    fun getrecommended(): MutableList<Item> {
        return repository.getrecommended()
    }
    fun onitemclicked(Item :Item){
        Currentitem.currentitem= Item
    }
}