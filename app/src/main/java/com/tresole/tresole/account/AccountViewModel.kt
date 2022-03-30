package com.tresole.tresole.account

import androidx.lifecycle.ViewModel
import com.tresole.tresole.util.Currentuser
import com.tresole.tresole.util.Shipment
import com.tresole.tresole.repository.Repository

class AccountViewModel : ViewModel() {
    private val repo=Repository()
    fun getuserwelcomemessage(): String {
      return Currentuser.getcurrentusername().toString()

    }

    fun getshipments(): MutableList<Shipment> {
        return repo.getshipments()

    }

}