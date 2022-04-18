package com.tresole.tresole.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Itemutils
import com.tresole.tresole.repository.Repository

class CategoriesViewModel : ViewModel() {

     var categoryitemslist = MutableLiveData<MutableList<Item>>()
    lateinit var categoryname:String
    fun showcategory(categoriesname: String) {
        val repo=Repository()
        categoryname=categoriesname
        categoryitemslist=repo.getcategory(categoriesname)
    }
    fun getcategory(): MutableLiveData<MutableList<Item>> {
        return categoryitemslist
    }

    fun onitemclicked(Item: Item) {
        Currentitem.currentitem= Item

    }

}