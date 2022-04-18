package com.tresole.tresole.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Itemutils
import com.tresole.tresole.repository.Repository

class SearchViewModel : ViewModel() {
    var searcheditems=MutableLiveData<MutableList<Item>>()
    private val repo=Repository()
    fun search(text: String) {
      searcheditems=  repo.search(text)
    }
    fun getsearcheditem(): MutableList<Item> {
        return searcheditems.value!!
    }

    fun onitemclicked(Item: Item) {
        Currentitem.currentitem=Item

    }

}