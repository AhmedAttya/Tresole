package com.tresole.tresole.Categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.Util.currentitem
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.itemutils
import com.tresole.tresole.repository.repository

class CategoriesViewModel : ViewModel() {

    var categoryitemslist = mutableListOf<item>()
    lateinit var categoryname:String
    fun showcategory(categoriesname: String) {
        val repo=repository()
        categoryname=categoriesname
        categoryitemslist=repo.getcategory(categoriesname)
        itemutils.listofitems = categoryitemslist
    }
    fun getcategory(): MutableList<item> {
        return categoryitemslist
    }

    fun onitemclicked(item: item) {
        currentitem.currentitem= item

    }

}