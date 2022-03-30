package com.tresole.tresole.search

import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentitem
import com.tresole.tresole.util.Item
import com.tresole.tresole.util.Itemutils
import com.tresole.tresole.repository.Repository

class SearchViewModel : ViewModel() {
    private val repo=Repository()
    fun search(text: String) {
      Itemutils.searcheditems=  repo.search(text)
    }
    fun getsearcheditem(): MutableList<Item> {
        return Itemutils.searcheditems
    }

    fun onitemclicked(Item: Item) {
        Currentitem.currentitem=Item

    }

}