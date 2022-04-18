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
            //livedata objects for store items and shipments
            var newandhotitems = MutableLiveData<MutableList<Item>>()
            var recommendeditems = MutableLiveData<MutableList<Item>>()
            var frequentlyboughtitems =MutableLiveData<MutableList<Item>>()
            var dealofthedayitem =MutableLiveData<MutableList<Item>>()
            var searcheditems= MutableLiveData<MutableList<Item>>()
            var currentcategoryitems =MutableLiveData<MutableList<Item>>()
            var allitems =MutableLiveData<MutableList<Item>>()
            var allshipments= MutableLiveData<MutableList<Shipment>>()
            var currentusershipemtns=MutableLiveData<MutableList<Shipment>>()
            val database = Firebase.database
            val databaseReference = database.reference
            fun createdata(){
                Datacreation.createdata()
            }
            //retrieving the data from rtdb and putting it in livedata object
            fun initiate() {

                databaseReference.child("items").get().addOnSuccessListener {

                    it.children.forEach {
                        allitems.value!!.add(it.getValue(Item::class.java)!!)
                    }
                }.addOnFailureListener {

                }
                databaseReference.child("shipments").get().addOnSuccessListener {
                    it.children.forEach {
                        allshipments.value!!.add(it.getValue(Shipment::class.java)!!)
                    }
                }.addOnFailureListener {

                }
            }
            //return the 20 newest items
            fun getnewandhot3() : MutableLiveData<MutableList<Item>> {
                allitems.value!!.sortBy { it.dateofaddition }
                newandhotitems.value= allitems.value!!.takeLast(20) as MutableList<Item>
                return newandhotitems
            }

            //return most discounted 20 items
            fun getdealoftheday(): MutableLiveData<MutableList<Item>> {
                allitems.value!!.sortBy { it.itemdiscount }
                dealofthedayitem.value= allitems.value!!.takeLast(20)  as MutableList<Item>
                return dealofthedayitem
            }
            //returns random 20 items
            fun getfrequentlybought(): MutableLiveData<MutableList<Item>> {
                allitems.value!!.sortBy { it.dateofaddition }
                frequentlyboughtitems.value= allitems.value!!.take(20) as MutableList<Item>
                return frequentlyboughtitems
            }

            //returns random 20 items
            fun getrecommended(): MutableLiveData<MutableList<Item>> {
                allitems.value!!.sortBy { it.itemname }
                recommendeditems.value= allitems.value!!.takeLast(20) as MutableList<Item>
                return recommendeditems
            }
            //adds a rating to an item in the rtdb
            fun addrating(item: Item, rate: Int) {
                item.itemratings.add(rate)
                databaseReference.child("items").child(item.itemid.toString()).setValue(item)

            }
            //returns all items in the chosen category
            fun getcategory(categoriesname: String): MutableLiveData<MutableList<Item>> {
                currentcategoryitems.value= allitems.value!!.filter { it.itemcategory ==categoriesname }.toMutableList()
                return currentcategoryitems
            }
            //returns all items that contain the searched word in it's name
            fun search(text: String) : MutableLiveData<MutableList<Item>> {
                searcheditems.value = allitems.value!!.filter { it.itemname.contains(text) } as MutableList<Item>
                return searcheditems
            }
            //adding a user shipment to the rtdb when a credit card is used
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

                var cshipment=Shipment(Currentuser.currentuser.uid,getid(),name,address,city,state,zipcode, phonenumber, type,total,itemids,Date.from(Instant.now()),"SHIPPING")
                boughtitems(itemids)
                databaseReference.child("shipments").child(cshipment.id.toString()).setValue(cshipment)

            }


            //adding a user shipment to the rtdb when cash on delivery is used
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
                var cshipment=Shipment(Currentuser.currentuser.uid,getid(),name,address,city,state,zipcode, phonenumber, type,total,
                    itemids,Date.from(Instant.now()),"SHIPPING")
                boughtitems(itemids)
                databaseReference.child("shipments").child(cshipment.id.toString()).setValue(cshipment)
            }
            //adjust the value of item quantity from items when they are bough
            private fun boughtitems(itemids: MutableList<Long>) {
                while (itemids.isNotEmpty()){
                    var itemid=itemids.removeAt(0)
                    var item= allitems.value!!.find { it.itemid==itemid }
                    item!!.itemquantity--
                    databaseReference.child("items").child(itemid.toString()).setValue(item)
                }

            }
            //generate a unique id for the shipment
            private fun getid(): Long {
                var id=1000000000
                while(allshipments.value!!.filter { it.id.equals(id) }.isNotEmpty())
                {
                    id++
                }
                return id.toLong()
            }
            //gets all shipments of the current user
            fun getshipments(): MutableLiveData<MutableList<Shipment>> {
                currentusershipemtns.value= allshipments.value!!.filter { it.userid==Currentuser.currentuser.uid } as MutableList<Shipment>
                return currentusershipemtns
            }
            //retrieve item image from the remote storage
            fun getimage(imageloc: String) : MutableLiveData<Bitmap> {
                var bitmaplivedata=MutableLiveData<Bitmap>()
                var islandRef = Datacreation.storageRef.child(imageloc)
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





