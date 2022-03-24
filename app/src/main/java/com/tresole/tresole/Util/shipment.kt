package com.tresole.tresole.Util

import java.time.Instant
import java.util.*

class shipment(val userid:String,val id:Long,val name:String,val address:String,val city:String,val state:String,val zipcode:String,val phonenumber:Int,val type:String,val total:Int,val itemids:MutableList<Long>,val date: Date,val status:String) {
constructor() : this("0",0,"0","0","0","0","0",0,"0",0, mutableListOf(0), Date.from(Instant.now()),"SHIPPING")
}