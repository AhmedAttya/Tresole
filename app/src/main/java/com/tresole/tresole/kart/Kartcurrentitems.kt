package com.tresole.tresole.kart

import com.tresole.tresole.util.Item

class Kartcurrentitems {
    companion object{
        var listofitems= mutableListOf<Item>()
        fun additem(Item: Item)
        {
            listofitems.add(Item)
        }
        fun getallitems(): MutableList<Item> {
            return listofitems
        }

        fun gettotal(): Int {
            var total=0
            val list = mutableListOf<Item>()
            list.addAll(listofitems)
            while (list.isNotEmpty())
            {
               val item= list.removeAt(0)
                total+=item.itemprice-(item.itemprice/100*item.itemdiscount)
            }
            return total

        }

        fun getitemids(): MutableList<Long> {
        val list = mutableListOf<Long>()
            while (listofitems.isNotEmpty())
            {
                val item= listofitems.removeAt(0)
                list.add(item.itemid)
            }
            return list
        }
    }
}