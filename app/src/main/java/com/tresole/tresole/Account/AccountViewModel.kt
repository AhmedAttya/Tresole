package com.tresole.tresole.Account

import androidx.lifecycle.ViewModel
import com.tresole.tresole.Util.currentuser
import com.tresole.tresole.Util.shipment
import com.tresole.tresole.repository.repository

class AccountViewModel : ViewModel() {
    val repo=repository()
    fun getuserwelcomemessage(): String {
      return currentuser.getcurrentusername().toString()

    }

    fun getshipments(): MutableList<shipment> {
        return repo.getshipments()

    }

}