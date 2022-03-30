package com.tresole.tresole.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tresole.tresole.util.Currentuser
import com.tresole.tresole.util.Datacreation
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Shipment
import java.time.Instant
import java.util.*

class Backend {
    companion object {
        val newandhotitems = mutableListOf<Item>()
         private var allitems =mutableListOf<Item>()
        private var allshipments= mutableListOf<Shipment>()
        private val database = Firebase.database
        private val databaseReference = database.reference
        fun createdata(){
            Datacreation.createdata()
        }
        fun initiate() {

            databaseReference.child("items").get().addOnSuccessListener {

             it.children.forEach { dataSnapshot ->
                 allitems.add(dataSnapshot.getValue(Item::class.java)!!)
             }
            }.addOnFailureListener {

            }
            databaseReference.child("shipments").get().addOnSuccessListener {
                it.children.forEach { dataSnapshot ->
                    allshipments.add(dataSnapshot.getValue(Shipment::class.java)!!)
                }
            }.addOnFailureListener {

            }
        }
        fun getnewandhot3() :MutableList<Item>{
             allitems.sortBy { it.dateofaddition }
            return allitems.takeLast(20) as MutableList<Item>
        }

        fun getdealoftheday(): MutableList<Item> {
             allitems.sortBy { it.itemdiscount }
            return allitems.takeLast(20) as MutableList<Item>
        }

        fun getfrequentlybought(): MutableList<Item> {
            allitems.sortBy { it.dateofaddition }
           return allitems.take(20) as MutableList<Item>
        }

        fun getrecommended(): MutableList<Item> {
            allitems.sortBy { it.itemname }
            return allitems.takeLast(20) as MutableList<Item>
        }

        fun addrating(Item: Item, rate: Int) {
            Item.itemratings.add(rate)
            databaseReference.child("items").child(Item.itemid.toString()).setValue(Item)

        }

        fun getcategory(categoriesname: String): MutableList<Item> {
            return allitems.filter { it.itemcategory ==categoriesname }.toMutableList()

        }

        fun search(text: String) :MutableList<Item>{
            val searcheditems = allitems.filter { it.itemname.contains(text) }
            return searcheditems as MutableList<Item>

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

            val cshipment=Shipment(Currentuser.currentuser,getid(),name,address,city,state,zipcode, phonenumber, type,total,itemids,Date.from(Instant.now()),"SHIPPING")
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
            val cshipment=Shipment(Currentuser.currentuser,getid(),name,address,city,state,zipcode, phonenumber, type,total,
                itemids,Date.from(Instant.now()),"SHIPPING")
            boughtitems(itemids)
            databaseReference.child("shipments").child(cshipment.id.toString()).setValue(cshipment)
        }

        private fun boughtitems(itemids: MutableList<Long>) {
            while (itemids.isNotEmpty()){
                val itemid=itemids.removeAt(0)
                val item= allitems.find { it.itemid==itemid }
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

        fun getshipments(): MutableList<Shipment> {
            return allshipments.filter { it.userid==Currentuser.currentuser } as MutableList<Shipment>
        }
        fun getimage(imageloc: String) : MutableLiveData<Bitmap> {
            val bitmaplivedata=MutableLiveData<Bitmap>()
            val islandRef = Datacreation.storageRef.child(imageloc)
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





