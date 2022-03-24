package com.tresole.tresole.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tresole.tresole.Util.currentuser
import com.tresole.tresole.Util.datacreation
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.shipment
import java.time.Instant
import java.util.*

class backend {
    companion object {
        val newandhotitems = mutableListOf<item>()
         var allitems =mutableListOf<item>()
        var allshipments= mutableListOf<shipment>()
        val database = Firebase.database
        val databaseReference = database.reference
        fun createdata(){
            datacreation.createdata()
        }
        fun initiate() {

            databaseReference.child("items").get().addOnSuccessListener {

             it.children.forEach {
                 allitems.add(it.getValue(item::class.java)!!)
             }
            }.addOnFailureListener {

            }
            databaseReference.child("shipments").get().addOnSuccessListener {
                it.children.forEach {
                    allshipments.add(it.getValue(shipment::class.java)!!)
                }
            }.addOnFailureListener {

            }
        }
        fun getnewandhot3() :MutableList<item>{
             allitems.sortBy { it.dateofaddition }
            return allitems.takeLast(20) as MutableList<item>
        }

        fun getdealoftheday(): MutableList<item> {
             allitems.sortBy { it.itemdiscount }
            return allitems.takeLast(20) as MutableList<item>
        }

        fun getfrequentlybought(): MutableList<item> {
            allitems.sortBy { it.dateofaddition }
           return allitems.take(20) as MutableList<item>
        }

        fun getrecommended(): MutableList<item> {
            allitems.sortBy { it.itemname }
            return allitems.takeLast(20) as MutableList<item>
        }

        fun addrating(item: item, rate: Int) {
            item.itemratings.add(rate)
            databaseReference.child("items").child(item.itemid.toString()).setValue(item)

        }

        fun getcategory(categoriesname: String): MutableList<item> {
            return allitems.filter { it.itemcategory ==categoriesname }.toMutableList()

        }

        fun search(text: String) :MutableList<item>{
            val searcheditems = allitems.filter { it.itemname.contains(text) }
            return searcheditems as MutableList<item>

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

            var cshipment=shipment(currentuser.currentuser,getid(),name,address,city,state,zipcode, phonenumber, type,total,itemids,Date.from(Instant.now()),"SHIPPING")
            boughtitems(itemids)
            databaseReference.child("shipments").child(cshipment.id.toString()).setValue(cshipment)

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
            var cshipment=shipment(currentuser.currentuser,getid(),name,address,city,state,zipcode, phonenumber, type,total,
                itemids,Date.from(Instant.now()),"SHIPPING")
            boughtitems(itemids)
            databaseReference.child("shipments").child(cshipment.id.toString()).setValue(cshipment)
        }

        private fun boughtitems(itemids: MutableList<Long>) {
            while (itemids.isNotEmpty()){
                var itemid=itemids.removeAt(0)
                var item= allitems.find { it.itemid==itemid }
                item!!.itemquantity--
                databaseReference.child("items").child(itemid.toString()).setValue(item)
            }

        }

        private fun getid(): Long {
            var id=1000000000
            while(allshipments.filter { it.id.equals(id) }.isNotEmpty())
            {
                id++
            }
            return id.toLong()
        }

        fun getshipments(): MutableList<shipment> {
            return allshipments.filter { it.userid==currentuser.currentuser } as MutableList<shipment>
        }
        fun getimage(imageloc: String) : MutableLiveData<Bitmap> {
            var bitmaplivedata=MutableLiveData<Bitmap>()
            var islandRef = datacreation.storageRef.child(imageloc)
            val ONE_MEGABYTE: Long = 1024 * 1024
            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                // Data for "images/island.jpg" is returned, use this as needed
                bitmaplivedata.value= BitmapFactory.decodeByteArray(it,0,it.size)
            }.addOnFailureListener {
                // Handle any errors

            }
            return bitmaplivedata
        }
    }
    }





