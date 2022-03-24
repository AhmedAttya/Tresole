package com.tresole.tresole.Util

class currentitem {
    companion object{
       lateinit var currentitem :item
        public fun setcurrentitem(item :item)
        {
            currentitem=item
        }
        public fun getcurrentitem() :item{
            return currentitem
        }
    }
}