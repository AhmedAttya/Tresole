package com.tresole.tresole.Kart

import com.tresole.tresole.Util.item

class kartcurrentitems {
    companion object{
        var listofitems= mutableListOf<item>()
        fun additem(item: item)
        {
            listofitems.add(item)
        }
        fun getallitems(): MutableList<item> {
            return listofitems
        }

        fun gettotal(): Int {
            var total=0
            var list = mutableListOf<item>()
            list.addAll(listofitems)
            while (list.isNotEmpty())
            {
               var item= list.removeAt(0)
                total+=item.itemprice-(item.itemprice/100*item.itemdiscount)
            }
            return total

        }

        fun getitemids(): MutableList<Long> {
        var list = mutableListOf<Long>()
            while (listofitems.isNotEmpty())
            {
                var item= listofitems.removeAt(0)
                list.add(item.itemid)
            }
            return list
        }
    }
}