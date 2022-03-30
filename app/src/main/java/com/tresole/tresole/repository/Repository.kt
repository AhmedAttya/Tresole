package com.tresole.tresole.repository

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Shipment

class Repository() {
    fun getnewnhot(): MutableList<Item> {

        return Backend.getnewandhot3()
   }
    fun createdata(){
        Backend.createdata()
    }
    fun initiate(){
        Backend.initiate()
    }

    fun getdealoftheday(): MutableList<Item> {
      return Backend.getdealoftheday()
    }

    fun getfrequentlybought(): MutableList<Item> {
        return Backend.getfrequentlybought()
    }

    fun getrecommended(): MutableList<Item> {
        return Backend.getrecommended()
    }

    fun addrating(Item: Item, rate: Int) {
         Backend.addrating(Item,rate)
    }

    fun getcategory(categoriesname: String): MutableList<Item> {
        return Backend.getcategory(categoriesname)

    }

    fun search(text: String): MutableList<Item> {
        return Backend.search(text)

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
       Backend.addCCshipment(name,address,city,state,zipcode,phonenumber,type,total,itemids)
    }

    fun addCODshipmend(
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
        Backend.addCODshipment(name,address,city,state,zipcode,phonenumber,type,total,itemids)
    }

    fun getshipments(): MutableList<Shipment> {
        return Backend.getshipments()

    }
    fun getimage(imageloc:String): MutableLiveData<Bitmap> {
        return Backend.getimage(imageloc)
    }

}