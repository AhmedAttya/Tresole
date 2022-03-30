package com.tresole.tresole.util

class Currentitem {
    companion object{
       lateinit var currentitem :Item
         fun setcurrentitem(Item :Item)
        {
            currentitem=Item
        }
         fun getcurrentitem() :Item{
            return currentitem
        }
    }
}