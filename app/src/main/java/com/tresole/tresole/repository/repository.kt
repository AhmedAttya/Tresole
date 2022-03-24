package com.tresole.tresole.repository

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.shipment

class repository() {
    fun getnewnhot(): MutableList<item> {

        return backend.getnewandhot3()
   }
    fun createdata(){
        backend.createdata()
    }
    fun initiate(){
        backend.initiate()
    }

    fun getdealoftheday(): MutableList<item> {
      return backend.getdealoftheday()
    }

    fun getfrequentlybought(): MutableList<item> {
        return backend.getfrequentlybought()
    }

    fun getrecommended(): MutableList<item> {
        return backend.getrecommended()
    }

    fun addrating(item: item, rate: Int) {
         backend.addrating(item,rate)
    }

    fun getcategory(categoriesname: String): MutableList<item> {
        return backend.getcategory(categoriesname)

    }

    fun search(text: String): MutableList<item> {
        return backend.search(text)

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
       backend.addCCshipment(name,address,city,state,zipcode,phonenumber,type,total,itemids)
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
        backend.addCODshipment(name,address,city,state,zipcode,phonenumber,type,total,itemids)
    }

    fun getshipments(): MutableList<shipment> {
        return backend.getshipments()

    }
    fun getimage(imageloc:String): MutableLiveData<Bitmap> {
        return backend.getimage(imageloc)
    }

}