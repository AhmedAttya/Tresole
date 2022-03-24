package com.tresole.tresole.Util

import com.google.firebase.auth.FirebaseAuth

class currentuser {
    companion object{
        fun getcurrentusername(): String? {
            return currentuserfull!!.displayName
        }

        var currentuserfull =FirebaseAuth.getInstance().currentUser
        var currentuser= currentuserfull!!.uid
    }
}