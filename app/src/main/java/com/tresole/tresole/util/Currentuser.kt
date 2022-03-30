package com.tresole.tresole.util

import com.google.firebase.auth.FirebaseAuth

class Currentuser {
    companion object{
        fun getcurrentusername(): String? {
            return currentuserfull!!.displayName
        }

        private var currentuserfull =FirebaseAuth.getInstance().currentUser
        var currentuser= currentuserfull!!.uid
    }
}