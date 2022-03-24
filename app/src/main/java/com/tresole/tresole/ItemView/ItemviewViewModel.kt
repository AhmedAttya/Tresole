package com.tresole.tresole.ItemView

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.Kart.kartcurrentitems
import com.tresole.tresole.R
import com.tresole.tresole.Util.Categories
import com.tresole.tresole.Util.currentitem
import com.tresole.tresole.Util.item
import com.tresole.tresole.repository.repository

class ItemviewViewModel : ViewModel() {
    val repo=repository()
    val item : item = currentitem.getcurrentitem()
     fun getimage(): MutableLiveData<Bitmap> {
         return repo.getimage(currentitem.currentitem.itemimage)
    }

    fun countprice(): Int {
     var pricing=item.itemprice-(item.itemdiscount*item.itemprice/100)
     return pricing

    }

    fun rating(): Float {
         var result :Float
         val itemratings = mutableListOf<Int>()
         itemratings.addAll(item.itemratings)
        var allratings = 0
        var count=item.itemratings.size
        while (itemratings.isNotEmpty())
        {
            allratings+=itemratings.removeAt(0)
        }
        result=allratings.toFloat() / count.toFloat()
        return result

    }

    fun additemtokart(item: item) {
        kartcurrentitems.additem(item)
    }

    fun rateadded( rate: Int) {
        repo.addrating(currentitem.getcurrentitem(),rate)

    }


}