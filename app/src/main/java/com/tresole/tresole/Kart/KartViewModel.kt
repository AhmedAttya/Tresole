package com.tresole.tresole.Kart

import androidx.lifecycle.ViewModel
import com.tresole.tresole.Util.item
import com.tresole.tresole.repository.repository

class KartViewModel : ViewModel() {
    var repo=repository()
    fun calcprice(): String {
        var list= mutableListOf<item>()
        var total =0
        list.addAll(kartcurrentitems.listofitems)
        while (list.isNotEmpty())
        {
         val item =list.removeAt(0)
            total+= (item.itemprice-(item.itemdiscount*(item.itemprice/100)))
        }

return total.toString()
    }

    fun addCCshipment(
        name: String,
        address: String,
        city: String,
        state: String,
        zipcode: String,
        phonenumber: Int,
        type: String,
        total: Int,
        itemids: MutableList<Long>
    ) {
       repo.addCCshipment(name,address,city,state,zipcode,phonenumber,type,total,itemids)
    }

    fun addCODshipment(
        name: String,
        address: String,
        city: String,
        state: String,
        zipcode: String,
        phonenumber: Int,
        type: String,
        total: Int,
        itemids: MutableList<Long>
    ) {
         repo.addCODshipmend(name,address,city,state,zipcode,phonenumber,type,total,itemids)
    }
    // TODO: Implement the ViewModel
}