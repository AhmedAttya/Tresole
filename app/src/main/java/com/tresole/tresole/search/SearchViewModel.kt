package com.tresole.tresole.search

import androidx.lifecycle.ViewModel
import com.tresole.tresole.Util.currentitem
import com.tresole.tresole.Util.item
import com.tresole.tresole.Util.itemutils
import com.tresole.tresole.repository.repository

class SearchViewModel : ViewModel() {
    val repo=repository()
    fun search(text: String) {
      itemutils.searcheditems=  repo.search(text)
    }
    fun getsearcheditem(): MutableList<item> {
        return itemutils.searcheditems
    }

    fun onitemclicked(item: item) {
        currentitem.currentitem=item

    }

}