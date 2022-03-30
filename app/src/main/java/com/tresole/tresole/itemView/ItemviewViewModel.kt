package com.tresole.tresole.itemView

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.kart.Kartcurrentitems
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item
import com.tresole.tresole.repository.Repository

class ItemviewViewModel : ViewModel() {
    private val repo=Repository()
    val Item : Item = Currentitem.getcurrentitem()
     fun getimage(): MutableLiveData<Bitmap> {
         return repo.getimage(Currentitem.currentitem.itemimage)
    }

    fun countprice(): Int {
        return Item.itemprice - (Item.itemdiscount * Item.itemprice / 100)

    }

    fun rating(): Float {
         val result :Float
         val itemratings = mutableListOf<Int>()
         itemratings.addAll(Item.itemratings)
        var allratings = 0
        val count=Item.itemratings.size
        while (itemratings.isNotEmpty())
        {
            allratings+=itemratings.removeAt(0)
        }
        result=allratings.toFloat() / count.toFloat()
        return result

    }

    fun additemtokart(Item: Item) {
        Kartcurrentitems.additem(Item)
    }

    fun rateadded( rate: Int) {
        repo.addrating(Currentitem.getcurrentitem(),rate)

    }


}