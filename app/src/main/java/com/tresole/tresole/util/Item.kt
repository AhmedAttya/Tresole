package com.tresole.tresole.util

import java.util.*
import kotlin.random.Random

class Item(
    val itemname:String,
    val itemprice:Int,
    var itemquantity:Int,

    val itemratings:MutableList<Int>,
    val itemdiscount:Int,
    val dateofaddition: Date,
    val itemimage:String,
    val itemseller:String,
    val itembrand:String,
    val itemdescreption:String,
    val itemcategory:String,

    ) {
    val itemid = getid()


    private fun getid(): Long {
        var checking = true
        var id:Long =Random.nextLong(100000000,999999999)
        while (checking){
        if(Itemutils.createdids.contains(id))
            id = Random.nextLong(100000000,999999999)
        else {
            checking = false
            Itemutils.createdids.add(id)
        }

        }
        return id
    }

    constructor() : this("gg",
        50,
        50,
        mutableListOf(1, 1, 1),
        1,
        Date(),
        "gg",
        "GG",
        "gg",
        "gg",
        "gg")
}