package com.tresole.tresole.Main

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.tresole.tresole.MainActivity
import com.tresole.tresole.R
import com.tresole.tresole.Util.currentitem
import com.tresole.tresole.Util.datacreation
import com.tresole.tresole.Util.item
import com.tresole.tresole.repository.backend

class StoreViewModel : ViewModel() {
    var repository=com.tresole.tresole.repository.repository()
    fun getnewandhot(): MutableList<item>{
        return repository.getnewnhot()
    }
    fun getdealoftheday() :MutableList<item>{
        return repository.getdealoftheday()
    }

    fun getfrequentlybought(): MutableList<item> {
        return repository.getfrequentlybought()
    }

    fun getrecommended(): MutableList<item> {
        return repository.getrecommended()
    }
    fun onitemclicked(item :item){
        currentitem.currentitem= item
    }
}